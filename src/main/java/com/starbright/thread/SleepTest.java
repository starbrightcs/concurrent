package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/30 14:59
 */
@Slf4j(topic = "c.sleepTest")
public class SleepTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			log.debug("{} enter sleep", Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("{} wake up", Thread.currentThread().getName());
				e.printStackTrace();
			}
		}, "t1");

		t1.start();
		log.debug("t1 state: {}", t1.getState());

		Thread.sleep(500);
		log.debug("t1 state: {}", t1.getState());

		// 打断睡眠的线程
		t1.interrupt();
	}

}
