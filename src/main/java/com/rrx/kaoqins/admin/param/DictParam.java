package com.rrx.kaoqins.admin.param;


import com.rrx.kaoqins.core.web.model.PageModel;
import lombok.Data;

@Data
public class DictParam extends PageModel { //<SysDict>

    private String code;

    private String codeText;

}
