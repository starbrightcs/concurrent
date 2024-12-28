package com.starbright.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/12/2 16:57
 */
@Slf4j(topic = "c.test4")
public class Test4 {

	static int count = 0;

	static final Object lock = new Object();

	@SneakyThrows
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				synchronized (lock) {
					count++;
				}
			}
		}, "t1");

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				// synchronized (lock) {
					count--;
				// }
			}
		}, "t2");

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		log.info("count:{}", count);
	}

}
