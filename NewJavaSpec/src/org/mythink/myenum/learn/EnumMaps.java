package org.mythink.myenum.learn;

import java.util.EnumMap;
import java.util.Map;

interface Command{
	void action();
}

public class EnumMaps {

	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
		em.put(AlarmPoints.KITCHEN, new Command() {
			
			@Override
			public void action() {
				System.out.println("Kitchen fire");
				
			}
		});
		em.put(AlarmPoints.BATHROOM, new Command() {
			
			@Override
			public void action() {
				System.out.println("Bathroom alert");
				
			}
		});
		for(Map.Entry<AlarmPoints, Command> entry:em.entrySet()){
			System.out.print(entry.getKey()+":");
			entry.getValue().action();
		}
		try {
			em.get(AlarmPoints.UTILITY).action();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
