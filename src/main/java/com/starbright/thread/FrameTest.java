package com.starbright.thread;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/28 14:30
 */
public class FrameTest {

	public static void main(String[] args) {
		new Thread(() -> {
			method1(20);
		}, "t1").start();

		method1(10);
	}

	public static void method1(int x) {
		int y = x + 1;
		Object m = method2();
		System.out.println(m);
	}

	public static Object method2() {
		Object m = new Object();
		return m;
	}

}
