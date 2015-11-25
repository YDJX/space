package org.mythink.java8.test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i=0; i<10;i++){
			map.putIfAbsent(i, "val"+i);
		}
		
		map.forEach((key,val)-> System.out.println(val));
		System.out.println("-------------------------------");
		map.computeIfPresent(3, (num,val)->val+num);
		System.out.println(map.get(3));
		map.computeIfPresent(9, (num,val)->null);
		System.out.println(map.containsKey(9));
		map.computeIfAbsent(23, num->"val"+num);
		System.out.println(map.containsKey(23));
		map.computeIfAbsent(3, num->"bam");
		System.out.println(map.get(3));
		map.remove(3,"val3");
		System.out.println(map.get(3));
		map.remove(3,"val33");
		System.out.println(map.get(3));
		System.out.println(map.getOrDefault(43, "not found"));
		
		System.out.println(map.get(9));
		map.merge(9, "val9", (value,newvalue)->value.concat(newvalue));
		System.out.println(map.get(9));
		map.merge(9, "val9", (value,newvalue)->value.concat(newvalue));
		System.out.println(map.get(9));
		map.merge(9, "abc", (value,newvalue)->value.concat(newvalue));
		System.out.println(map.get(9));
	}
}
