package org.mythink.java8.interf;

@FunctionalInterface
public interface Converter<T,F> {

	T convert(F from);
}
