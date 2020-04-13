package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterVo {

    @NotEmpty(message = "短信验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String code;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
