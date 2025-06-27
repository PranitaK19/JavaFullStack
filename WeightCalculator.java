package pgm;
//A person weight on earth is 70KG , lets calculate weight on different solar planets
//Mercury  : 3.7
//Venus    : 8.87
//Earth    : 9.8
//Mars     : 3.71
//Jupiter  : 24.79
//Saturn   : 10.44
//Uranus   : 8.69
//Neptune  : 11.15

public class WeightCalculator {

	public static void main(String[] args) throws InterruptedException {
	double earthWeight=70;
	double earthGravity=9.8;
	double mass=earthWeight/earthGravity;
	
	Runnable Mercury=()->
	{
		double gravity=3.7;
		double weight=mass*gravity;
		System.out.println("Mercury :"+weight);
	};

	
	Runnable Venus=()->
	{
		double gravity=8.87;
		double weight=mass*gravity;
		System.out.println("Venus :"+weight+" kg");
	};
	
	
	Runnable Earth=()->
	{
		double gravity=9.8;
		double weight=mass*gravity;
		System.out.println("Earth :"+weight+" kg");
	};
	
	
	Runnable Mars=()->
	{
		double gravity=3.71;
		double weight=mass*gravity;
		System.out.println("Mars :"+weight+" kg");
	};
	
	
	Runnable Jupiter=()->
	{
		double gravity=24.79;
		double weight=mass*gravity;
		System.out.println("Jupiter :"+weight+" kg");
	};
	
	
	Runnable Saturn =()->
	{
		double gravity=10.44;
		double weight=mass*gravity;
		System.out.println("Saturn :"+weight+" kg");
	};
	
	
	Runnable Uranus =()->
	{
		double gravity=8.69;
		double weight=mass*gravity;
		System.out.println("Uranus :"+weight+" kg");
	};
	
	
	Runnable Neptune=()->
	{
		double gravity=11.15;
		double weight=mass*gravity;
		System.out.println("Neptune :"+weight+" kg");
	};
	
	Thread t1=new Thread(Mercury);
	Thread t2=new Thread(Venus);
	Thread t3=new Thread(Saturn);
	Thread t4=new Thread(Mars);
	Thread t5=new Thread(Jupiter);
	Thread t6=new Thread(Uranus);
	Thread t7=new Thread(Earth);
	Thread t8=new Thread(Neptune);
	t1.start();
	t1.join();
	t2.start();
	t2.join();
	t3.start();
	t3.join();
	t4.start();
	t4.join();
	t5.start();
	t5.join();
	t6.start();
	t6.join();
	t7.start();
	t7.join();
	t8.start();
	t8.join();

	
	}

}
