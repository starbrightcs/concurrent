package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/30 15:39
 */
@Slf4j(topic = "c.joinTest")
public class JoinTest {

	static int r = 0;
	static int r1 = 0;
	static int r2 = 0;
	public static void main(String[] args) throws InterruptedException {
		test3();
	}

	private static void test1() throws InterruptedException {
		log.debug("start");
		Thread t1 = new Thread(() -> {
			log.debug("start");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			r = 10;
			log.debug("end");
		}, "t1");
		t1.start();
		t1.join();

		log.debug("r = {}", r);
		log.debug("end");
	}

	private static void test2() throws InterruptedException {
		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			r1 = 10;
		}, "t1");
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			r2 = 20;
		}, "t2");
		long start = System.currentTimeMillis();
		t1.start();
		t2.start();
		log.debug("join begin");
		t1.join();
		log.debug("t1 join end");
		t2.join();
		log.debug("t2 join end");
		log.debug("join end");
		long end = System.currentTimeMillis();
		log.debug("r1 = {}, r2 = {}, cost: {} ms", r1, r2, end - start);
	}


	private static void test3() throws InterruptedException {
		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			r1 = 10;
		}, "t1");

		t1.start();
		long start = System.currentTimeMillis();
		log.debug("join begin");
		// 线程结束会导致 join 结束
		t1.join(3500);
		log.debug("t1 join end, r1: {}, cost: {} ms", r1, System.currentTimeMillis() - start);
	}
}
