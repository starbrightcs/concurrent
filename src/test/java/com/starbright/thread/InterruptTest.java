package com.starbright.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: Star Bright
 * @date: 2024/12/2 10:40
 */
@Slf4j(topic = "c.interruptTest")
public class InterruptTest {

	/**
	 * 用于测试：sleep 打断测试
	 */
	@Test
	@SneakyThrows
	public void test_sleep() {
		Thread t1 = new Thread(() -> {
			log.debug("sleep...");
			try {
				// join、wait、sleep 打断标志都为 false
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		Thread.sleep(500);
		t1.interrupt();
		log.debug("t1 打断标志为: {}", t1.isInterrupted());
	}

	/**
	 * 用于测试：打断正常运行线程
	 */
	@Test
	@SneakyThrows
	public void test_normal() {
		Thread t2 = new Thread(() -> {
			while (true) {
				Thread current = Thread.currentThread();
				boolean interrupted = current.isInterrupted();
				if (interrupted) {
					log.debug(" 打断状态: {}", interrupted);
					break;
				}
			}
		}, "t2");
		t2.start();
		Thread.sleep(500);
		t2.interrupt();
	}

	/**
	 * 用于测试：两阶段打断线程测试
	 */
	@Test
	@SneakyThrows
	public void test_twoPhaseTerminal() {
		TwoPhaseTerminal twoPhaseTerminal = new TwoPhaseTerminal();
		twoPhaseTerminal.start();
		log.debug("线程启动");
		Thread.sleep(3500);
		twoPhaseTerminal.stop();
	}

	class TwoPhaseTerminal {
		private Thread monitor;

		/**
		 * 启动监控线程
		 */
		public void start() {
			monitor = new Thread(() -> {
				while (true) {
					Thread current = Thread.currentThread();
					if (current.isInterrupted()) {
						log.debug("料理后事");
						break;
					}
					try {
						// 睡眠 2s
						Thread.sleep(2000);
						log.debug("执行监控记录");
					} catch (InterruptedException e) {
						e.printStackTrace();
						// 假如在 sleep 过程中进行打断，那么标志位为 false，需要重新设置为 ture
						current.interrupt();
					}
				}
			});
			monitor.start();
		}

		/**
		 * 停止监控线程
		 */
		public void stop() {
			monitor.interrupt();
		}
	}


	/**
	 * 用于测试：park
	 */
	@Test
	@SneakyThrows
	public void test_park() {
		Thread t1 = new Thread(() -> {
			log.debug("park");
			LockSupport.park();
			log.debug("unpark");
			log.debug("打断状态为: {}", Thread.currentThread().isInterrupted());
			log.debug("unpark");
			LockSupport.park();
			log.debug("打断状态为: {}", Thread.currentThread().isInterrupted());
		});
		t1.start();

		Thread.sleep(1000);
		t1.interrupt();
		new CountDownLatch(1).await();
	}


	/**
	 * 用于测试：守护线程
	 */
	@Test
	@SneakyThrows
	public void test_Demon() {
		Thread t1 = new Thread(() -> {
			while (true) {
				Thread current = Thread.currentThread();
				if (current.isInterrupted()) {
					break;
				}
			}
			log.debug("{} end", Thread.currentThread().getName());
		});
		t1.setDaemon(true);
		t1.start();

		Thread.sleep(1000);
		log.debug("结束");
	}

}
