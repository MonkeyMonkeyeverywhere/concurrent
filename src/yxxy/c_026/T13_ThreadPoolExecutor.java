/**
 * 自定义线程池
 */
package yxxy.c_026;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义 支持阻塞的固定大小线程池
 */
public class T13_ThreadPoolExecutor extends ThreadPoolExecutor{

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = this.lock.newCondition();

	public T13_ThreadPoolExecutor(int corePoolSize,
								  int maximumPoolSize,
								  long keepAliveTime,
								  TimeUnit unit,
								  LinkedBlockingQueue<Runnable> workQueue) {
		super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
	}

	@Override
	public void execute(Runnable command) {
		try {
			//加锁
			this.lock.lock();
			//若线程池数量已达最大数量，等待
			while (getPoolSize() == getMaximumPoolSize()){
				System.out.println("i am awaiting!");
					this.condition.await();
			}
			super.execute(command);
			this.condition.signalAll();
			System.out.println("i am signalAlling!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			this.lock.unlock();
		}
	}

	/*@Override
	protected void afterExecute(Runnable r,Throwable t) {
		super.afterExecute(r,t);
	}*/

	public static void main(String[] args) {
		T13_ThreadPoolExecutor executor = new T13_ThreadPoolExecutor(4,10,2,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
		for(int i = 0;i <12;i++){
			executor.execute(new TaskTest(i));
		}
	}

	static class TaskTest implements Runnable{

		private int i ;

		public TaskTest (int i){
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("i am "+i+",i will do whatever you like!");
		}
	}
}
