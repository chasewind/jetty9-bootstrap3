package com.belief.springtest;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 模拟线程执行环境
 * 
 * @author yudongwei
 *
 */
public class SpringThreadTest {
	@Test
	public void testThread() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		for (int i = 0; i < 200; i++) {
			Runnable task = new Runnable() {
				@Override
				public void run() {
					System.out.println("1111");
				}
			};
			executor.execute(task);

		}
		System.out.println("core size: " + executor.getCorePoolSize());
		System.out.println("max pool size: " + executor.getMaxPoolSize());
		System.out.println("default pool size: " + executor.getPoolSize());
	}
}
