package org.mythink.myenum.learn;

enum Shrubbery {GROUP,CRAWLING,HANGING}

public class MyEnumClass {

	public static void main(String[] args){
		for(Shrubbery s:Shrubbery.values()){
			print(s+" ordinal: "+ s.ordinal());
			print(s,s.compareTo(Shrubbery.CRAWLING));
			print(s.equals(Shrubbery.CRAWLING));
			print(s == Shrubbery.CRAWLING);
			print(s.getDeclaringClass());
			print(s.name());
			print(Shrubbery.valueOf(Shrubbery.class, "CRAWLING"));
		}
	}
	
	public static void print(Object... oo){
		for(Object o:oo){
			System.out.print(o);
			System.out.print("\t");
		}
		System.out.println();
	}
}
