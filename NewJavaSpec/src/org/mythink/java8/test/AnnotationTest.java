package org.mythink.java8.test;

import java.lang.annotation.Repeatable;

public class AnnotationTest {

	public static void main(String[] args){
		Hint hint = Animal.class.getAnnotation(Hint.class);
		System.out.println(hint);
		
		Hint[] hint2 = Animal.class.getAnnotationsByType(Hint.class);
		System.out.println(hint2.length);
		
		Hints hints = Animal.class.getAnnotation(Hints.class);
		System.out.println(hints.value().length);
		
	}
}


@interface Hints {
	Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
	String value();
}

//@Hints({@Hint("hint1"),@Hint("hint2")})
@Hint("hint1")
@Hint("hint2")
class Animal{
	
}