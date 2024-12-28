package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/30 15:02
 */
@Slf4j(topic = "c.yieldTest")
public class YieldTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			int count = 0;
			for (; ; ) {
				System.out.println("---> t1:" + count++);
			}
		}, "t1");
		Thread t2 = new Thread(() -> {
			int count = 0;
			for (; ; ) {
				// Thread.yield();
				System.out.println("       ---> t2:" + count++);
			}
		}, "t2");

		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
	}
}
