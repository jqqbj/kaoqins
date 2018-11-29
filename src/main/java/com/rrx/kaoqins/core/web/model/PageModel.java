package com.rrx.kaoqins.core.web.model;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页实体类
 */
@Data
public class PageModel<T>{
    private int current;
    private int size;
    private String asc;
    private String desc;

    /**
     * 封装Page类
     */
    public Page<T> getPage(){
        Page page = new Page<>(this.getCurrent(),this.getSize());
        //page.setAsc(this.getAsc());
        //page.setDesc(this.getDesc());
        return page;
    }

}
