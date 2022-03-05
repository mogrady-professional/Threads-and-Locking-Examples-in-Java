package ie.gmit.dip;

public class VolatileExample implements Runnable{
	private volatile boolean keepRunning = true;
	
	public void go() throws Exception {
		Thread t = new Thread(this); //We are a Runnable
		t.start(); //Hollywood Principle
		Thread.sleep(100);
		
		this.keepRunning = false;
		System.out.println("Variable keepRunning set to false...");
	}
	
	public void run() {
		long count = 0;
		while (keepRunning) {
			count++;
		}
		System.out.println("Thread about to die after " + count + " iterations.");
	}

	public static void main(String[] args) throws Exception {
		new VolatileExample().go();
	}
}
