package com.rrx.kaoqins.core.web.model;


import cn.hutool.core.util.StrUtil;
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
        if(StrUtil.isNotBlank(this.getAsc())){
            page.setAsc(this.getAsc());
        }
        if(StrUtil.isNotBlank(this.getDesc())){
            page.setDesc(this.getDesc());
        }
        return page;
    }

}
