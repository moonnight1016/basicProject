package com.person.norma.basiccommon.core;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author： norma
 * @Description：查询参数
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
@Data
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	/**
     * 当前页码
     * **/
    private int page;
    /**
     * 每页条数
     * **/
    private int limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
//        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = (String)params.get("sidx");
        String order = (String)params.get("order");
    }

    public Query(Integer pageNum, Integer pageSize) {
        this.put("page", pageNum);
        this.put("limit", pageSize);
    }
}
