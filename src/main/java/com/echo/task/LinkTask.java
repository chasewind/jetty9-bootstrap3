package com.echo.task;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkTask {
	private static Logger logger = LoggerFactory.getLogger(LinkTask.class);

	@Scheduled(cron = "0 0/1 * * * ?")
	public void test() {
		logger.error("LinkTask: " + new Date());
		for (int i = 0; i < 50; i++) {
			if (i == 30) {
				try {
					throw new Exception(RandomStringUtils.randomAlphanumeric(50));
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}
			}
		}
	}
}
