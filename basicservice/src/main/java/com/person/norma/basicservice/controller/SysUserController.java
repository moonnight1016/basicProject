package com.person.norma.basicservice.controller;

import com.person.norma.basiccommon.core.Query;
import com.person.norma.basiccommon.core.Result;
import com.person.norma.basicservice.entity.SysUserEntity;
import com.person.norma.basicservice.service.SysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author： norma
 * @Description： 用户信息表 前端控制器
 * @Date： 2021-04-22
 * @Modified By：
 */
@Slf4j
@Api(value = "用户信息表 控制层", tags = {"用户信息表 操作接口"})
@RestController
@RequestMapping("/sysuser/sysUserEntity")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;


    protected SysUserService getHandler() {
        return sysUserService;
    }

    /**
     * 分页查询
     *
     * @param map
     */
    @ApiOperation(value = "分页查询数据列表", notes = "分页查询数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", dataType = "String", value = "当前页码", required = true),
            @ApiImplicitParam(name = "limit", paramType = "query", dataType = "String", value = "每页的记录数", required = true)
    })
    @GetMapping
    @RequiresPermissions("SysUserController:list")
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
    @RequiresPermissions("SysUserController:info")
    public Result info(@PathVariable Long id) {
        SysUserEntity t = getHandler().getById(id);
        return Result.ok(t);
    }

    @ApiOperation(value = "保存实体数据", notes = "保存实体数据")
    @PostMapping
    @RequiresPermissions("SysUserController:save")
    public Result save(@RequestBody SysUserEntity t) {
        getHandler().save(t);
        return Result.ok(t);
    }

    @ApiOperation(value = "更新实体数据", notes = "更新实体数据")
    @PutMapping
    @RequiresPermissions("SysUserController:update")
    public Result update(@RequestBody SysUserEntity t) {
        getHandler().updateById(t);
        return Result.ok(t);
    }

    @ApiOperation(value = "删除实体数据", notes = "删除实体数据")
    @DeleteMapping("/{id}")
    @RequiresPermissions("SysUserController:delete")
    public Result delete(@PathVariable Long id) {
        getHandler().removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除实体数据", notes = "批量删除实体数据")
    @DeleteMapping
    @RequiresPermissions("SysUserController:delete_list")
    public Result delete(@ApiParam(value = "ID数组如：[41,42,43, ...]", required = true) @RequestBody Long[] ids) {
        getHandler().removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}

