package com.rrx.kaoqins.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rrx.kaoqins.admin.model.SysDict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author JQQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictDto implements Serializable {

    private String type;

    private String code;

    private String codeText;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonIgnore
    private String flag;


    /*
     * 转换实体到DTO
     */
    public static SysDictDto model2dto(SysDict sysDict){
        SysDictDto sysDictDto =  new SysDictDto();
        sysDictDto.setType(sysDict.getType());
        sysDictDto.setCode(sysDict.getCode());
        sysDictDto.setCodeText(sysDict.getCodeText());
        sysDictDto.setCreateTime(sysDict.getCreateTime());
        return sysDictDto;
    }

    /*
     * 转换集合到DTO
     */
    public static List<SysDictDto> model2dto(List<SysDict> sysDicts){
        List<SysDictDto> sysDictDtos = new ArrayList<>();
        for (SysDict sysDict : sysDicts) {
            sysDictDtos.add(model2dto(sysDict));
        }
        return sysDictDtos;
    }

}
