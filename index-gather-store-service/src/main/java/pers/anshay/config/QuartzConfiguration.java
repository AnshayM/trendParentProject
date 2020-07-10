package pers.anshay.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.anshay.job.IndexDataSyncJob;

/**
 * 定时器配置
 *
 * @author anshay
 * @date 2020/6/22
 */
@Configuration
public class QuartzConfiguration {
    private static final int INTERVAL = 1;

    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(INTERVAL).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("indexDataSyncJob").withSchedule(scheduleBuilder).build();
    }
}
