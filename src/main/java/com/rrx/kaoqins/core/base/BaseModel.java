package com.rrx.kaoqins.core.base;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据模型基类
 * 如果不使用AR模式，无须继承Model接口
 */
@Data
public class BaseModel<T extends Model> extends Model<T> implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date createTime;
    /**
     * 创建人
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Integer createBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Integer updateBy;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField
    private Integer isDel;


    /**
     * 实现AR模式
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
