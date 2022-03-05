package ie.gmit.dip;

public class SyncDeadlockExample implements Runnable{
	private SyncObject one = new SyncObject("SyncObject 1");
	private SyncObject two = new SyncObject("SyncObject 2");
	
	public void go() {
		Thread t = new Thread(this);
		t.start();
		
		one.execute(two);
	}
	
	public void run() {
		two.execute(one);
	}
	
	private class SyncObject{
		private String name;
		
		private SyncObject(String name) {
			this.name = name;
		}
		
		private synchronized void execute(SyncObject other) {
			System.out.println("Processing execute() on " + name);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Calling finish() on " + other.getName());
			other.finish();
		}

		private synchronized void finish() {
			System.out.println("Executing finish() on " + name);
		}

		protected String getName() {
			return name;
		}
	}
	
	public static void main(String[] args) {
		new SyncDeadlockExample().go();
	}

}
