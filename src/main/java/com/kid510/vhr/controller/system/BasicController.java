package com.kid510.vhr.controller.system;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.pojo.Position;
import com.kid510.vhr.service.system.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Classname BasicController
 * @Description 基础信息设置controller
 * @Date 2020/1/6 10:15
 * @Author kid
 */
@RestController
@RequestMapping("/system/basic")
public class BasicController {
    @Autowired
    private BasicService basicService;

    /**
     * 查询所有职位
     *
     * @return
     */
    @GetMapping("/")
    public ResultResp getAllPos() {
        List<Position> list = basicService.getAllPos();
        return ResultResp.ok("ok", list);
    }

    /**
     * 增加职位
     *
     * @return
     */
    @PostMapping("/")
    public ResultResp addPos(@Valid @RequestBody Position position) {
        return basicService.addPos(position);
    }

    /**
     * 删除职位根据id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultResp delPos(@PathVariable Integer id) {
        return basicService.delPos(id);
    }

    /**
     * 批量删除 职位
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public ResultResp delPosMany(Integer[] ids) {
        return basicService.delPosMany(ids);
    }

    /**
     * 修改职位
     *
     * @param id
     * @param position
     * @return
     */
    @PutMapping("/{id}")
    public ResultResp updatePos(@PathVariable Integer id, @Valid @RequestBody Position position) {
        return basicService.updatePos(id, position);
    }
}
