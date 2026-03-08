package org.jeecg.modules.tracing.controller;

import java.util.Arrays;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.tracing.entity.TracingTag;
import org.jeecg.modules.tracing.service.ITracingTagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
 /**
 * @Description: 寻迹-标签分类表
 * @Author: jeecg-boot
 * @Date:   2026-02-16
 * @Version: V1.0
 */
@Tag(name="寻迹-标签分类表")
@RestController
@RequestMapping("/tracing/tracingTag")
@Slf4j
public class TracingTagController extends JeecgController<TracingTag, ITracingTagService> {
	@Autowired
	private ITracingTagService tracingTagService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tracingTag
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "寻迹-标签分类表-分页列表查询")
	@Operation(summary="寻迹-标签分类表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TracingTag>> queryPageList(TracingTag tracingTag,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<TracingTag> queryWrapper = QueryGenerator.initQueryWrapper(tracingTag, req.getParameterMap());
		Page<TracingTag> page = new Page<TracingTag>(pageNo, pageSize);
		IPage<TracingTag> pageList = tracingTagService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tracingTag
	 * @return
	 */
	@AutoLog(value = "寻迹-标签分类表-添加")
	@Operation(summary="寻迹-标签分类表-添加")
	@RequiresPermissions("tracing:tb_tracing_tag:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TracingTag tracingTag) {
		tracingTagService.save(tracingTag);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tracingTag
	 * @return
	 */
	@AutoLog(value = "寻迹-标签分类表-编辑")
	@Operation(summary="寻迹-标签分类表-编辑")
	@RequiresPermissions("tracing:tb_tracing_tag:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TracingTag tracingTag) {
		tracingTagService.updateById(tracingTag);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "寻迹-标签分类表-通过id删除")
	@Operation(summary="寻迹-标签分类表-通过id删除")
	@RequiresPermissions("tracing:tb_tracing_tag:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tracingTagService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "寻迹-标签分类表-批量删除")
	@Operation(summary="寻迹-标签分类表-批量删除")
	@RequiresPermissions("tracing:tb_tracing_tag:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tracingTagService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "寻迹-标签分类表-通过id查询")
	@Operation(summary="寻迹-标签分类表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TracingTag> queryById(@RequestParam(name="id",required=true) String id) {
		TracingTag tracingTag = tracingTagService.getById(id);
		if(tracingTag==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tracingTag);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tracingTag
    */
    @RequiresPermissions("tracing:tb_tracing_tag:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TracingTag tracingTag) {
        return super.exportXls(request, tracingTag, TracingTag.class, "寻迹-标签分类表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("tracing:tb_tracing_tag:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TracingTag.class);
    }

}
