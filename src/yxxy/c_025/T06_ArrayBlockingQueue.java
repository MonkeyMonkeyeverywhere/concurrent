package yxxy.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {

	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		
		strs.put("aaa"); //满了就会等待，程序阻塞
		//strs.add("aaa");
		//strs.offer("aaa");	//将指定元素插入此队列中（如果立即可行且不会违反容量限制），成功时返回 true，如果当前没有可用的空间，则返回 false，不会抛异常
		//strs.offer("aaa", 1, TimeUnit.SECONDS);
		
		System.out.println(strs);
	}
}
