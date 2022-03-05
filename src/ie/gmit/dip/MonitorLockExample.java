package ie.gmit.dip;

public class MonitorLockExample {
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void go() {
		Thread t1 = new Thread(new LockOneTask());
		Thread t2 = new Thread(new LockTwoTask());
		t1.start();
		t2.start();
	}
	
	private class LockOneTask implements Runnable{
		public void run() {
			synchronized (lock1) {
				System.out.println("LockOneTask is holding lock1");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("LockOneTask is waiting for lock2");
				synchronized (lock2) {
					System.out.println("LockOneTask is holding lock1 and lock2");
				}
			}
		}
	}
	
	private class LockTwoTask implements Runnable{
		public void run() {
			synchronized (lock2) {
				System.out.println("LockTwoTask is holding lock2");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("LockTwoTask is waiting for lock1");
				synchronized (lock1) {
					System.out.println("LockTwoTask is holding lock1 and lock2");
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new MonitorLockExample().go();
	}
}
