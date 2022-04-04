package factory.foobar;

import java.util.concurrent.atomic.AtomicInteger;

public class Bar {


	private static final AtomicInteger count = new AtomicInteger(0);
	private int barId;
	
	public Bar() {
		barId = count.incrementAndGet();
	}
	
	public int getBarId() {
		return this.barId;
	}
}
