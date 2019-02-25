package com.rrx.kaoqins.admin.param;


import com.rrx.kaoqins.core.web.model.PageModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class DictParam extends PageModel { //<SysDict>

    @NotNull(message = "code不可以为空")
    private String code;

    private String codeText;

}
