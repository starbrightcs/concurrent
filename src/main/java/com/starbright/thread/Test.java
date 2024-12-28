package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/28 13:30
 */
@Slf4j
public class Test {
	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				log.debug("{} doSomething", Thread.currentThread().getName());
			}
		}, "t1").start();

		new Thread("t2") {
			@Override
			public void run() {
				log.debug("{} doSomething", Thread.currentThread().getName());
			}
		}.start();

	}
}
