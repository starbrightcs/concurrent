package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/28 14:01
 */
@Slf4j(topic = "c.multiThreadTest")
public class MultiThreadTest {

	public static void main(String[] args) {
		new Thread(() -> {
			while (true) {
				log.debug("running");
			}
		}, "t1").start();

		new Thread(() -> {
			while (true) {
				log.debug("running");
			}
		}, "t2").start();
	}

}
