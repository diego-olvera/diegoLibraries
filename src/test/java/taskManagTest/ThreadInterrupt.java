package taskManagTest;

public class ThreadInterrupt extends Thread{
	public void run(){  
		try{  
			Thread.sleep(1000);  
			System.out.println("task");  
			for(int i=0;i<10;) {
				
			}
		}catch(InterruptedException e){  
			throw new RuntimeException("Thread interrupted..."+e);  
		}  
		  
	} 
	public static void main(String[] args) {
		ThreadInterrupt thread=new ThreadInterrupt();
		thread.start();
		try {
			Thread.sleep(2000);  
			thread.interrupt();
		}catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
		}
	}
}
