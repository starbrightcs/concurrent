package com.starbright.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/12/28 14:45
 */
@Slf4j(topic = "c.Number")
public class Number {

	public static synchronized void a() {
		log.debug("1");
	}

	public static synchronized void b() {
		log.debug("2");
	}

	public static void c() {
		log.debug("3");
	}

	public static void main(String[] args) {
		Number number = new Number();
		new Thread(() -> number.a()).start();
		new Thread(() -> number.b()).start();
		new Thread(() -> number.c()).start();
	}

}
