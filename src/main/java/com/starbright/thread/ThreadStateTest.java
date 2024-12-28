package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 线程 6 种状态演示
 * @author: Star Bright
 * @date: 2024/12/2 15:26
 */
@Slf4j(topic = "c.threadStateTest")
public class ThreadStateTest {

	public static void main(String[] args) {
		// 此处没有调用 start 方法，对应的线程状态为 NEW
		Thread t1 = new Thread(() -> log.debug("running"), "t1");

		// 线程 2 对应的是 while(true) 并且启动了线程，对应的状态为 RUNNABLE
		Thread t2 = new Thread(() -> {
			while (true) {//这里可能分到时间片、也可能没有分到时间片，但是在 Java 中都是 RUNNABLE
			}
		}, "t2");
		t2.start();

		// 线程 3 执行结束后，线程状态为 TERMINATED
		Thread t3 = new Thread(() -> log.debug("running"), "t3");
		t3.start();

		// 线程 4 调用了sleep(long) 方法，对应的状态为 TIMED_WAITING
		Thread t4 = new Thread(() -> {
			synchronized (ThreadStateTest.class) {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "t4");
		t4.start();

		// 线程 5 调用了 join 方法，这里没有设置时限，那对应的状态为 WAITING
		Thread t5 = new Thread(() -> {
			try {
				t2.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, "t5");
		t5.start();

		// 线程 6，t4 和 t6 都获取同一个锁，t4 获取到锁，t6 获取不到锁，从而这里线程状态为 BLOCKED
		Thread t6 = new Thread(() -> {
			synchronized (ThreadStateTest.class) {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "t6");
		t6.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		log.debug("t1 state: {}", t1.getState());
		log.debug("t2 state: {}", t2.getState());
		log.debug("t3 state: {}", t3.getState());
		log.debug("t4 state: {}", t4.getState());
		log.debug("t5 state: {}", t5.getState());
		log.debug("t6 state: {}", t6.getState());
	}

}
