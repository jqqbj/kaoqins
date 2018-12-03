package com.rrx.kaoqins.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rrx.kaoqins.core.base.BaseModel;
import lombok.Data;

/**
 * @Author JQQ
 * @Date 2018/12/3 15:13
 */
@Data
@TableName("sys_log")
public class SysLog extends BaseModel<SysLog> {

    @TableField
    private String userName;

    @TableField
    private String operation;

}
