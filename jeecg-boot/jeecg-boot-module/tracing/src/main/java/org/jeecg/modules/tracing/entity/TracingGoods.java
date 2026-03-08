package org.jeecg.modules.tracing.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 寻迹-物品表
 * @Author: jeecg-boot
 * @Date:   2026-02-16
 * @Version: V1.0
 */
@Data
@TableName("tb_tracing_goods")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="寻迹-物品表")
public class TracingGoods implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;
	/**创建人*/
    @Schema(description = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private String sysOrgCode;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @Schema(description = "租户id")
    private Integer tenantId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @Schema(description = "状态")
    private Integer status;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @Schema(description = "删除状态")
    @TableLogic
    private Integer delFlag;
    /**用户*/
    @Excel(name = "用户", width = 15)
    @Schema(description = "用户")
    @Dict(dictTable = "sys_user", dicCode = "id", dicText = "realname")
    private String userId;
	/**物品名称*/
	@Excel(name = "物品名称", width = 15)
    @Schema(description = "物品名称")
    private String name;
	/**存放位置*/
	@Excel(name = "存放位置", width = 15)
    @Schema(description = "存放位置")
    private String location;
	/**存放详细位置*/
	@Excel(name = "存放详细位置", width = 15)
    @Schema(description = "存放详细位置")
    private String locationDetail;
	/**标签分类*/
	@Excel(name = "标签分类", width = 15)
    @Schema(description = "标签分类")
    @Dict(dictTable = "tb_tracing_tag", dicCode = "id", dicText = "name")
    private String tags;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @Schema(description = "备注")
    private String remark;
	/**图片路径*/
	@Excel(name = "图片路径", width = 15)
    @Schema(description = "图片路径")
    private String imgPath;
}
