package com.rrx.kaoqins.core.web.model;


import com.baomidou.mybatisplus.core.metadata.IPage;
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

    public IPage<T> getPage(){
        IPage page = new Page<>(this.getCurrent(),this.getSize());
        ((Page) page).setAsc(this.getAsc());
        ((Page) page).setDesc(this.getDesc());
        return page;
    }
}
