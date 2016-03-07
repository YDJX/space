package org.mythink.myenum.learn;

import java.util.Random;

public class Enums {

	private static Random rand = new Random(47);
	
	public static <T extends Enum<T>>T random(Class<T> ec){
		return random(ec.getEnumConstants());
	}
	public static <T> T random(T[] values){
		return values[rand.nextInt(values.length)];
	}
}


enum Activity{SITTING,LYING,STANDING,HOPPING,RUNNING,DODGING,JUMPING,FALLING,FLYING}

class RandomTest{

	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			System.out.print(Enums.random(Activity.class)+",");
		}
		
		System.out.println("\n=-=================");
		for(int i=0;i<10;i++){
			SecurityCategory sc = Enums.random(SecurityCategory.class);
			System.out.println(sc+" : "+sc.randomSelection());
		}
	}
}


enum SecurityCategory{
	STOCK(Security.Stock.class),BOUND(Security.Bound.class);
	Security[] values;
	private SecurityCategory(Class<? extends Security> kind) {
		values= kind.getEnumConstants();
	}
	interface Security{
		enum Stock implements Security{
			SHORT,LONG,MARGIN
		}
		enum Bound implements Security{
			MUNICIPAL,JUNK
		}
	}
	
	public Security randomSelection(){
		return Enums.random(values);
	}
	
}
