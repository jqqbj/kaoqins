package com.rrx.kaoqins;

import java.io.Serializable;
import java.util.List;


public class ReportGroupDto implements Serializable {

    private String id; //ID
    private String low;  //关联ID
    private String descr; //报表组名称
    private String groupType; //报表组类型
    private List<ReportGroupDto> children;
    private Long sort = 0L ;
    private int level = 0;

    public ReportGroupDto() {
    }

    public ReportGroupDto(String id, String low, String descr) {
        this.id = id;
        this.low = low;
        this.descr = descr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public List<ReportGroupDto> getChildren() {
        return children;
    }

    public void setChildren(List<ReportGroupDto> children) {
        this.children = children;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

