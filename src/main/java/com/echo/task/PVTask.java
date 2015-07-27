package com.echo.task;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PVTask {
	private static Logger logger = LoggerFactory.getLogger(PVTask.class);

	@Scheduled(cron = "0 0/2 * * * ?")
	public void test() {
		for (int i = 0; i < 50; i++) {
			if (i == 30) {
				try {
					throw new Exception(RandomStringUtils.randomAlphanumeric(30));
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}
			}
		}
	}
}
