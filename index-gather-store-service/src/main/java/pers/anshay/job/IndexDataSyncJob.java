package pers.anshay.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import pers.anshay.pojo.Index;
import pers.anshay.service.IndexDataService;
import pers.anshay.service.IndexService;

import java.util.List;

/**
 * 更新指数定时器
 *
 * @author anshay
 * @date 2020/6/22
 */
@Slf4j
public class IndexDataSyncJob extends QuartzJobBean {
    @Autowired
    private IndexService indexService;
    @Autowired
    private IndexDataService indexDataService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("定时任务IndexDataSyncJob启动:{}", DateUtil.now());
        //TODO 这里调用remove方法时会报错，但是在外部controller调用则没有问题
        List<Index> indices = indexService.fresh();
        indices.forEach(item -> indexDataService.fresh(item.getCode()));
        log.info("定时任务IndexDataSyncJob结束:{}", DateUtil.now());
    }
}
