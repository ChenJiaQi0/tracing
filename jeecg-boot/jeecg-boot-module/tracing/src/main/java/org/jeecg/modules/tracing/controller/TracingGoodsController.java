package org.jeecg.modules.tracing.controller;

import java.util.Arrays;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.tracing.entity.TracingGoods;
import org.jeecg.modules.tracing.service.ITracingGoodsService;
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
 * @Description: 寻迹-物品表
 * @Author: jeecg-boot
 * @Date:   2026-02-16
 * @Version: V1.0
 */
@Tag(name="寻迹-物品表")
@RestController
@RequestMapping("/tracing/tracingGoods")
@Slf4j
public class TracingGoodsController extends JeecgController<TracingGoods, ITracingGoodsService> {
	@Autowired
	private ITracingGoodsService tracingGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tracingGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "寻迹-物品表-分页列表查询")
	@Operation(summary="寻迹-物品表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TracingGoods>> queryPageList(TracingGoods tracingGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<TracingGoods> queryWrapper = QueryGenerator.initQueryWrapper(tracingGoods, req.getParameterMap());
		Page<TracingGoods> page = new Page<TracingGoods>(pageNo, pageSize);
		IPage<TracingGoods> pageList = tracingGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tracingGoods
	 * @return
	 */
	@AutoLog(value = "寻迹-物品表-添加")
	@Operation(summary="寻迹-物品表-添加")
	@RequiresPermissions("tracing:tb_tracing_goods:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TracingGoods tracingGoods) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tracingGoods.setUserId(loginUser.getId());
		tracingGoodsService.save(tracingGoods);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tracingGoods
	 * @return
	 */
	@AutoLog(value = "寻迹-物品表-编辑")
	@Operation(summary="寻迹-物品表-编辑")
	@RequiresPermissions("tracing:tb_tracing_goods:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TracingGoods tracingGoods) {
		tracingGoodsService.updateById(tracingGoods);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "寻迹-物品表-通过id删除")
	@Operation(summary="寻迹-物品表-通过id删除")
	@RequiresPermissions("tracing:tb_tracing_goods:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tracingGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "寻迹-物品表-批量删除")
	@Operation(summary="寻迹-物品表-批量删除")
	@RequiresPermissions("tracing:tb_tracing_goods:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tracingGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "寻迹-物品表-通过id查询")
	@Operation(summary="寻迹-物品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TracingGoods> queryById(@RequestParam(name="id",required=true) String id) {
		TracingGoods tracingGoods = tracingGoodsService.getById(id);
		if(tracingGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tracingGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tracingGoods
    */
    @RequiresPermissions("tracing:tb_tracing_goods:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TracingGoods tracingGoods) {
        return super.exportXls(request, tracingGoods, TracingGoods.class, "寻迹-物品表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("tracing:tb_tracing_goods:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TracingGoods.class);
    }

}
