package com.rrx.kaoqins.admin.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rrx.kaoqins.core.base.BaseModel;
import lombok.Data;

/**
 * 字典表
 */
@Data
@TableName("sys_dict")
public class  SysDict extends BaseModel<SysDict> {

    public SysDict() {
    }

    public SysDict(String code, String codeText) {
        this.code = code;
        this.codeText = codeText;
    }

    /**
     * 类型
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private String type;
    /**
     * 字典值
     */
    @TableField
    private String code;
    /**
     * 字典名称
     */
    @TableField
    private String codeText;

    /**
     * 排序
     */
    @TableField
    private Integer sortNo;

    /**
     * 是否可编辑
     */
    @TableField
    private Integer editable;

    /**
     * 备注
     */
    @TableField("remark_")
    private String remark;

}
