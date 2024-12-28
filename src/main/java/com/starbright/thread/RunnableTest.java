package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/28 13:15
 */
@Slf4j(topic = "c.runnableTest")
public class RunnableTest {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				log.debug("{} doSomething", Thread.currentThread().getName());
			}
		};
		// 创建线程对象
		Thread thread = new Thread(runnable, "t1");
		thread.start();

		// java8 可以使用 lambda 创建
		new Thread(() -> log.debug("{} doSomething", Thread.currentThread().getName()), "t2").start();
	}

}
