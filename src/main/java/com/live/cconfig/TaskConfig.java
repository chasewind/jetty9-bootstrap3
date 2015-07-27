package com.live.cconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.belief.task.TaskPkg;

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = TaskPkg.class)
public class TaskConfig {

}
