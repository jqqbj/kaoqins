package com.rrx.kaoqins.core.global;

import java.io.Serializable;

/**
 * @Author JQQ
 * Redis，key以分号命名,如 库名:表名：主键， kaoqin:user:1
 */
public class GeneRedisKey {

    public static String getSysDictKey(Serializable id){
        return Const.CACHE_NAMESPACE_PREFIX+"kaoqin:sysdict:"+id; //rrx:kaoqin:sysdict:1
    }

}

