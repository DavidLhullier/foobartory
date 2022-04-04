package factory.robots;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import factory.foobar.Bar;
import factory.foobar.Foo;
import factory.foobar.Foobar;

public class Robot {

	public static int CHANGE_ACTIVITY_TIME = 5;
	private static final AtomicInteger count = new AtomicInteger(0);
	private int robotId;
	private Task task;

	public Robot() {
	    robotId = count.incrementAndGet();
	}
	
	public int getRobotId() {
		return this.robotId;
	}
	
	public Task getTask() {
		return this.task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}

	public void moveTo() {
		setTask(Task.CHANGE_ACTIVITY);
	}

	public Bar createOneBar() {
		setTask(Task.MINE_BAR);
		return new Bar();
	}

	public Foo createOneFoo() {
		setTask(Task.MINE_FOO);
		return new Foo();
	}
	
	public Optional<Foobar> createOneFoobar(Foo foo, Bar bar) {
		setTask(Task.CREATE_FOOBAR);
		Random rand = new Random();
		if( rand.nextInt(100) > 40 ) {
			return Optional.of(new Foobar(foo.getFooId(), bar.getBarId()));
		} else {
			return Optional.empty();
		}
	}

	public double getDurationTask() {
		switch (this.task) {
			case MINE_BAR : 
				Random rand = new Random();
				return 0.5 + rand.nextDouble()*1.5;
			case MINE_FOO :
				return 1;
			case CHANGE_ACTIVITY :
				return 5;
			case CREATE_FOOBAR :
				return 2;
			default :
				return 0;
		}
	}
}
