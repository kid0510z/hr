package com.kid510.vhr.service.system.basic;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.mapper.JobLevelMapper;
import com.kid510.vhr.pojo.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname JobLevelService
 * @Description 职称管理service
 * @Date 2020/1/6 17:19
 * @Author kid
 */
@Service
public class JobLevelService {
    @Autowired
    private JobLevelMapper jobLevelMapper;

    /**
     * 得到职称信息所有列表
     *
     * @return
     */
    public List<JobLevel> getAllJobLevel() {
        return jobLevelMapper.getAllJobLevel();
    }

    /**
     * 增加职称--> 校验职称是否存在
     *
     * @param position
     * @return
     */
    public ResultResp addJobLevel(JobLevel position) {
        String name = position.getName();
        List<JobLevel> allJobLevel = jobLevelMapper.getAllJobLevel();
        AtomicBoolean flag = new AtomicBoolean(true);
        allJobLevel.forEach(job -> {
            if (job.getName().equals(name)) {
                flag.set(false);
            }
        });
        // 职称名称不重复
        if (flag.get()) {
            position.setCreateDate(new Date());
            position.setEnabled(true);
            int result = jobLevelMapper.insertSelective(position);
            if (result == 1) {
                return ResultResp.ok("添加成功！");
            } else {
                return ResultResp.error("添加失败！");
            }
        } else {
            return ResultResp.error("不能重复添加职称！");
        }
    }

    /**
     * 根据id删除职称
     *
     * @param id
     * @return
     */
    public ResultResp delJobLevel(Integer id) {
        int result = jobLevelMapper.deleteByPrimaryKey(id);
        if (result == 1) {
            return ResultResp.ok("删除成功！");
        } else {
            return ResultResp.error("删除失败！");
        }
    }

    /**
     * 根据ids批量删除职称
     *
     * @param id
     * @return
     */
    public ResultResp delJobLevelMany(Integer[] ids) {
        int result = jobLevelMapper.deleteByPrimaryKeyMany(ids);
        if (result == ids.length) {
            return ResultResp.ok("批量删除成功！");
        } else {
            return ResultResp.error("批量删除失败！");
        }
    }

    /**
     * 修改职称
     *
     * @param id
     * @param position
     * @return
     */
    public ResultResp updateJobLevel(Integer id, JobLevel jobLevel) {
        if (!jobLevel.getId().equals(id)) {
            return ResultResp.error("请求参数有误！");
        }
        int result = jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
        if (result == 1) {
            return ResultResp.ok("修改成功！");
        } else {
            return ResultResp.error("修改失败！");
        }

    }
}
