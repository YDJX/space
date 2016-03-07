package org.mythink.myenum.learn;

import java.util.Random;

interface Generator<T extends Enum<T>>{
	public  T next();
}

enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY,SPANKY,PUNCHY,SILLY,BOUNCY,NUTTY,BOB;
	private Random rand = new Random(47);
	
	@Override
	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
}

public class EnumImplementation {
	public static <T extends Enum<T>> void printNext(Generator<? extends Enum<T>> rg){
		System.out.print(rg.next()+",");
	}
	
	public static void main(String[] args) {
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i=0;i<20;i++){
			printNext(cc.next());
		}
	}
}
