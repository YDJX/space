package org.mythink.clazzloader.learn;

public interface MyObjectInterface {

	public default String trans(final String name) {
		return "this is interface";
	};
	
	public String life(String style);
}
