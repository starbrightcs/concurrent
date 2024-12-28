package com.starbright.basic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 同步调用
 * @author: Star Bright
 * @date: 2024/11/26 10:32
 */
@Slf4j(topic = "c.sync")
public class SyncThread {

	public static void main(String[] args) {
		read();
		log.info("main doSomething");
	}

	@SneakyThrows
	public static void read() {
		long start = System.currentTimeMillis();
		log.info("read data start");
		Thread.sleep(1000);
		log.info("read data end, cost: {} ms", System.currentTimeMillis() - start);
	}

}
