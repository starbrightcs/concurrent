package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/29 15:04
 */
@Slf4j(topic = "c.Test2")
public class Test2 {

	public static void main(String[] args) {
		Thread t1 = new Thread("t1") {
			public void run() {
				log.debug("running");
			}
		};

		// 能不能直接调用 run 方法呢？
		log.debug("第一次调用 run 方法");
		t1.run();

		t1.start();

		// 能不能多次调用 run 方法呢
		log.debug("第二次调用 run 方法");
		t1.run();

		// 能不能多次调用 start 方法呢？
		t1.start();
	}

}
