package org.jeecg.modules.system.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录表单
 *
 * @Author scott
 * @since  2019-01-18
 */
@Schema(description="登录对象")
@Data
public class SysLoginModel {
	@Schema(description = "账号")
    private String username;
	@Schema(description = "密码")
    private String password;
	@Schema(description = "登录部门")
    private String loginOrgCode;
	@Schema(description = "验证码")
    private String captcha;
	@Schema(description = "验证码key")
    private String checkKey;
    @Schema(description = "头像")
    private String avatar;
    /********************第三方登录********************/
    @Schema(description = "登录来源")
    private String thirdType;
    @Schema(description = "真实姓名")
    private String realname;
    @Schema(description = "第三方用户账号")
    private String thirdUserId;
    @Schema(description = "身份证号")
    private String idNumber;
    @Schema(description = "绑定第三方关联类型：0或空-自动绑定+自动生成用户 1-需要先绑定 2-自动绑定+需存在用户")
    private Integer createThirdRelateType;

    /********************手机号、第三方登录********************/
    @Schema(description = "手机号")
    private String mobile;
}