package com.kid510.vhr.controller.system.basic;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.pojo.Role;
import com.kid510.vhr.service.system.basic.AuthorityService;
import com.kid510.vhr.service.system.basic.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Classname AuthorityController
 * @Description 权限
 * @Date 2020/1/9 11:39
 * @Author kid
 */
@RestController
@RequestMapping("/system/basic/auth")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenuService menuService;

    // 角色相关

    /**
     * 所有角色信息
     *
     * @return
     */
    @GetMapping("/getAllRoles")
    public ResultResp getAllRoles() {
        return ResultResp.ok("ok", authorityService.getAllRoles());
    }

    @PostMapping("/addRole")
    public ResultResp addRole(@Valid @RequestBody Role role) {
        authorityService.addRole(role);
        return ResultResp.ok("新增角色成功！");
    }

    @DeleteMapping(value = "/delRole/{rid}")
    public ResultResp delRole(@PathVariable Integer rid) {
        authorityService.delRole(rid);
        return ResultResp.ok("删除角色成功！");
    }

    // 权限相关

    /**
     * 所有菜单数据以及所选角色对应选中菜单
     *
     * @param rid
     * @return
     */
    @GetMapping("/getMenusAndSelectd/{rid}")
    public ResultResp getMenusAndSelectd(@PathVariable Integer rid) {
        return ResultResp.ok("ok", menuService.getMenusAndSelectd(rid));
    }

    /**
     * 修改角色对应的权限
     *
     * @param rid  角色id
     * @param mids 所选中权限ids
     * @return
     */
    @PutMapping(value = "/{rid}/{mids}")
    public ResultResp updateRole(@PathVariable Integer rid, @PathVariable Integer[] mids) {
        authorityService.updateRole(rid, mids);
        return ResultResp.ok("修改角色权限成功！");
    }


}