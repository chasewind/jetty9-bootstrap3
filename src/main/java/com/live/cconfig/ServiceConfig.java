package com.live.cconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.belief.Scanned;

@Configuration
@ComponentScan(basePackageClasses = Scanned.class)
public class ServiceConfig {


}
