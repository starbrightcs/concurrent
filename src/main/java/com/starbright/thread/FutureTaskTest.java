package com.starbright.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/11/28 13:50
 */
@Slf4j(topic = "c.futureTaskTest")
public class FutureTaskTest {

	@SneakyThrows
	public static void main(String[] args) {
		// 创建任务对象
		FutureTask<Integer> task3 = new FutureTask<>(() -> {
			log.debug("hello");
			return 100;
		});
		// 参数1 是任务对象; 参数2 是线程名字，推荐
		new Thread(task3, "t3").start();
		// 主线程阻塞，同步等待 task 执行完毕的结果
		Integer result = task3.get();
		log.debug("结果是:{}", result);
	}

}
