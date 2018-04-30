package yxxy.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		strs.transfer("aaa");  //若使用transfer则应该先有消费者
		
		//strs.put("aaa");
		

		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
