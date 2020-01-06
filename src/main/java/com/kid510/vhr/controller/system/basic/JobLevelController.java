package com.kid510.vhr.controller.system.basic;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.pojo.JobLevel;
import com.kid510.vhr.service.system.basic.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname JobLevelController
 * @Description 职称管理
 * @Date 2020/1/6 17:18
 * @Author kid
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    private JobLevelService jobLevelService;

    /**
     * 查询所有职称
     *
     * @return
     */
    @GetMapping("/")
    public ResultResp getAllJobLevel() {
        List<JobLevel> list = jobLevelService.getAllJobLevel();
        return ResultResp.ok("ok", list);
    }

    /**
     * 增加职称
     *
     * @return
     */
    @PostMapping("/")
    public ResultResp addJobLevel(@Valid @RequestBody JobLevel jobLevel) {
        return jobLevelService.addJobLevel(jobLevel);
    }

    /**
     * 删除职称根据id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultResp delJobLevel(@PathVariable Integer id) {
        return jobLevelService.delJobLevel(id);
    }

    /**
     * 批量删除 职称
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public ResultResp delJobLevelMany(Integer[] ids) {
        return jobLevelService.delJobLevelMany(ids);
    }

    /**
     * 修改职称
     *
     * @param id
     * @param jobLevel
     * @return
     */
    @PutMapping("/{id}")
    public ResultResp updateJobLevel(@PathVariable Integer id, @Valid @RequestBody JobLevel jobLevel) {
        return jobLevelService.updateJobLevel(id, jobLevel);
    }
}
