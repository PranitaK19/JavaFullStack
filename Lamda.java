package pgm;

public class Lamda {
	public static void main(String args[]) {
	Runnable thread= ()-> System.out.println("Thread 1 is running.....");
	Runnable thread1= ()-> System.out.println("Thread 2 is running.....");
	
	
	Thread t=new Thread(thread);
	t.start();
Thread t1=new Thread(new A());
Thread t2=new Thread(new B());
t1.start();
try {
	t1.join();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Thread t3=new Thread(thread1);
t3.start();
t2.start();

	}

}
class A implements Runnable
{

	@Override
	public void run() {
		for(int i=1;i<=5;i++)
		{
			System.out.println("Im from Thread 1 : "+i);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
}
class B implements Runnable
{

	@Override
	public void run() {
		for(int i=1;i<=5;i++)
		{
			System.out.println("Im from Thread 2 : "+i);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
}