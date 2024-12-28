package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/26 11:22
 */
@Slf4j(topic = "c.test1")
public class Test1 {

	public static void main(String[] args) {
		Thread t1 = new Thread("t1") {
			@Override
			// run 方法内实现了要执行的任务
			public void run() {
				log.debug("hello");
			}
		};
		t1.start();
	}

}
