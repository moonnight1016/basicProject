package com.person.norma.basiccommon.gererator.common;

import com.person.norma.basiccommon.core.Query;
import com.person.norma.basiccommon.core.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author： norma
 * @Description：controller 父类
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
@RestController
@Slf4j
public abstract class AbstractCrudController<T> {

    /**
     * <p>获取 CRUD 用的Handler</p>
     *
     * @return
     * @author norma
     * @date 2019/1/22
     **/
    protected abstract AbstractCrudService<T> getHandler();

    @ApiOperation(value = "可传入查询条件查询所有数据", notes = "可传入查询条件查询所有数据")
    @GetMapping("/listByParams")
    public Result listByParams(@RequestParam @ApiParam(hidden = true) Map<String, Object> pageParams) {
        return Result.ok(getHandler().listByMap(pageParams));
    }

    /**
     * 分页查询列表
     *
     * @param map
     */
    @ApiOperation(value = "分页查询列表", notes = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", dataType = "String", value = "当前页码", required = true),
            @ApiImplicitParam(name = "limit", paramType = "query", dataType = "String", value = "每页的记录数", required = true)
    })
    @GetMapping
    public Result list(@ApiParam(hidden = true) @RequestParam Map<String, Object> map) throws Exception {
        Query query = new Query(map);
        return Result.ok(getHandler().listPageByQuery(query));
    }

    /**
     * 查询实体信息
     *
     * @param id
     */
    @ApiOperation(value = "按ID查询单条记录", notes = "按ID查询单条记录")
    @GetMapping("/{id}")
    public Result<T> info(@PathVariable Long id) {
        T t = getHandler().getById(id);
        return Result.ok(t);
    }

    @ApiOperation(value = "保存实体数据", notes = "保存实体数据")
    @PostMapping
    public Result<T> save(T t) {
        getHandler().save(t);
        return Result.ok(t);
    }

    @ApiOperation(value = "更新实体数据", notes = "更新实体数据")
    @PutMapping
    public Result<T> update(T t) {
        getHandler().updateById(t);
        return Result.ok(t);
    }

    @ApiOperation(value = "删除实体数据", notes = "删除实体数据")
    @DeleteMapping("/{id}")
    public Result<T> delete(@PathVariable Long id) {
        getHandler().removeById(id);
        return Result.ok();
    }
}
