package com.starbright.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/12/26 22:36
 */
@Slf4j(topic = "c.test5")
public class Test5 {

	@SneakyThrows
	public static void main(String[] args) {
		Room room = new Room();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				room.increment();
			}
		}, "t1");

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				room.decrement();
			}
		}, "t2");

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		log.info("count:{}", room.getCount());
	}
}

class Room {

	static int count = 0;

	public void increment() {
		synchronized (this) {
			count++;
		}
	}

	public void decrement() {
		synchronized (this) {
			count--;
		}
	}

	public int getCount() {
		// 由于上面的加法和减法都是加了synchronize，所以这里需要加锁进行获取
		synchronized (this) {
			return count;
		}
	}

}
