package factory.foobar;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {


	private static final AtomicInteger count = new AtomicInteger(0);
	private int fooId;
	
	public Foo() {
		// crate a foo
		fooId = count.incrementAndGet();
	}
	
	public int getFooId() {
		return this.fooId;
	}
}
