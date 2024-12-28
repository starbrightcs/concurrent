package com.starbright.basic;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

/**
 * @description: 多线程利用CPU
 * @author: Star Bright
 * @date: 2024/11/26 10:50
 */
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations=3)
@Measurement(iterations=5)
public class MyBenchmark {

	static int[] ARRAY = new int[1000_000_00];
	static {
		Arrays.fill(ARRAY, 1);
	}

	@Benchmark
	public int c() throws Exception {
		int[] array = ARRAY;
		FutureTask<Integer> t1 = new FutureTask<>(()->{
			int sum = 0;
			for(int i = 0; i < 250_000_00;i++) {
				sum += array[0+i];
			}
			return sum;
		});
		FutureTask<Integer> t2 = new FutureTask<>(()->{
			int sum = 0;
			for(int i = 0; i < 250_000_00;i++) {
				sum += array[250_000_00+i];
			}
			return sum;
		});
		FutureTask<Integer> t3 = new FutureTask<>(()->{
			int sum = 0;
			for(int i = 0; i < 250_000_00;i++) {
				sum += array[500_000_00+i];
			}
			return sum;
		});
		FutureTask<Integer> t4 = new FutureTask<>(()->{
			int sum = 0;
			for(int i = 0; i < 250_000_00;i++) {
				sum += array[750_000_00+i];
			}
			return sum;
		});
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();
		new Thread(t4).start();
		return t1.get() + t2.get() + t3.get()+ t4.get();
	}

	@Benchmark
	public int d() throws Exception {
		int[] array = ARRAY;
		FutureTask<Integer> t1 = new FutureTask<>(()->{
			int sum = 0;
			for(int i = 0; i < 1000_000_00;i++) {
				sum += array[0+i];
			}
			return sum;
		});
		new Thread(t1).start();
		return t1.get();
	}

}
