package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/12/2 16:37
 */
@Slf4j(topic = "c.test3")
public class Test3 {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			log.debug("洗水壶");
			sleep(1);
			log.debug("烧开水");
			sleep(15);
		}, "老王");
		Thread t2 = new Thread(() -> {
			log.debug("洗茶壶");
			sleep(1);
			log.debug("洗茶杯");
			sleep(2);
			log.debug("拿茶叶");
			sleep(1);
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.debug("泡茶");
		}, "小王");
		t1.start();
		t2.start();
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
