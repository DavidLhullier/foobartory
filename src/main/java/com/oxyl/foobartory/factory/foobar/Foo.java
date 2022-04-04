package com.oxyl.foobartory.factory.foobar;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {


	private static final AtomicInteger count = new AtomicInteger(0);
	private int fooId;
	
	public Foo() {
		fooId = count.incrementAndGet();
	}
	
	public int getFooId() {
		return this.fooId;
	}
}
