package CoreJava1;

public class AbstractClass {

	public static void main(String[] args) {

		AtmToKPa atmtokpa=new AtmToKPa(1);
atmtokpa.displayConversion("kPa");
	}

}
 
abstract class PressureConverter{//abstract class
	
	protected double value;
	
	public PressureConverter(double value) {
		this.value=value;
		
				
	}
	public abstract double convert();//abstract method
	
	public void displayConversion(String unit) {
		System.out.println(this.value+" converted to "+unit+" = "+ convert());
	}
}//end pressure converter  

class AtmToKPa extends PressureConverter
{
	private static final double CONVERSION_FACTOR=101.325;
	public AtmToKPa(double value)
	{
		super(value);
	}
@Override
public double convert() {
	return value*CONVERSION_FACTOR;
}
	
}//AtmToKPa end

/*
 * Friday-13-06
 * constructor
 * this
 * abstract class and abstract method
 * single inheritance
 * super
 */


/*1)Convert Atmospheres to Torr
2)Convert Pascal to Newton per square meter
3)Convert Bar to kPa
*/
