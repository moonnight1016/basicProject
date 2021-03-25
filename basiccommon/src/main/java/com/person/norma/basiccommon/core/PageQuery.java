package com.person.norma.basiccommon.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @Author： norma
 * @Description：分页查询
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
@ApiModel("分页查询对象")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery<T> implements Serializable {

    @ApiModelProperty(value = "当前页码")
    @Range(min = 0, max = Integer.MAX_VALUE)
    private int page = 1;

    @ApiModelProperty(value = "每页数量")
    @Range(min = 1, max = Integer.MAX_VALUE)
    private int limit = 10;

    @ApiModelProperty(value = "排序", notes = "例：create_time desc,update_time desc")
    private String orderBy;

    @ApiModelProperty(value = "查询条件")
    private T condition;

    public PageQuery(int page, int limit) {
        super();
        this.page = page;
        this.limit = limit;
    }

    public int getOffset() {
        return (this.page - 1) * this.limit;
    }

}
