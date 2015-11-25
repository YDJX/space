package org.mythink.java8.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.mythink.java8.interf.Converter;
import org.mythink.java8.interf.Formula;

public class DefaultInterfTest {

	public static void main(String[] args){
		//default
		Formula f = new Formula() {
			
			@Override
			public double calculate(int a) {
				return this.sqrt(a*100);
			}
		};
		System.out.println(f.calculate(25));
		System.out.println(f.sqrt(16));
		List<String> names = Arrays.asList("lily","karae","monke","abc","juney","tom");
		printList(names);
		Collections.sort(names, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		printList(names);
		
		List<String> nas = Arrays.asList("lily","karae","monke","abc","juney","tom");
		//lambda
		Collections.sort(nas,(String a,String b) -> { 
			return b.compareTo(a);
		});
//		Collections.sort(nas,(String a,String b) -> b.compareTo(a));
		printList(nas);
		
		Converter<Integer, String> convert = (from) -> Integer.valueOf(from);
		Integer c = convert.convert("123");
		System.out.println(c);
		
		Converter<Integer, String> ct = Integer::valueOf;
		Integer b = ct.convert("456");
		System.out.println(b);
		
		Something sth = new Something();
		Converter<String,String> ss = sth::firstChar;
		String startchar = ss.convert("Java");
		System.out.println(startchar);
		
		PersonFactory<Person> pf = Person::new;
		Person p =pf.create("stall", "rechard");
		System.out.println(p);
		
		Supplier<Person> supp = p::getThat;
		Consumer<Person> greeter = (pp) -> System.out.println("Hello,"+pp.firstName+", "+pp.lastName);
		greeter.accept(supp.get());
		
		Optional<String> optional = Optional.of("bam");
		boolean ispresent = optional.isPresent();
		System.out.println("optional is present:"+ispresent);
		String option = optional.get();
		System.out.println("optional is:"+option);
		System.out.println("if not option:"+optional.orElse("fallback"));
		optional.ifPresent((pp) -> System.out.println(pp.charAt(0)));
		
		
		List<String> stringCollection = new ArrayList<String>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		stringCollection.stream()
		.map(String::toUpperCase)
		.sorted((x,y)->y.compareTo(x))
		.filter((s)->s.startsWith("A"))
		.forEach(System.out::println);
		
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for(int i=0;i<max;i++){
			UUID uuid = UUID.randomUUID();
			values.add( uuid.toString());
		}
		
		long t0 = System.nanoTime();
		long count = values.stream().sorted((x,y)-> y.compareTo(x)).count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
		System.out.println(String.format("sequential sort took:%d ms", millis));
		
		long t2 = System.nanoTime();
		long count1 = values.parallelStream().sorted((x,y)-> y.compareTo(x)).count();
		System.out.println(count1);
		long t3 = System.nanoTime();
		long millis1 = TimeUnit.NANOSECONDS.toMillis(t3-t2);
		System.out.println(String.format("parallel sort took:%d ms", millis1));
		
		
	}
	
	public static <E> void printList(List<E> list){
		int item=0;
		System.out.println("------------------------------------");
		for(E e:list){
			System.out.println(++item +": "+e.toString());
		}
		System.out.println("------------------------------------");
	}
	
	
}

class Something{
	String firstChar(String s){
		return String.valueOf(s.charAt(0));
	}
}

class Person {
	String firstName;
	String lastName;
	public Person(String firstName,String lastName) {
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public Person getThat(){
		lastName="*instance*";
		return this;
	}
	@Override
	public String toString() {
		return "{firstName:"+firstName+",lastName:"+lastName+"}";
	}
}

interface PersonFactory<P extends Person> {
	P create(String firstName,String lastName);
}