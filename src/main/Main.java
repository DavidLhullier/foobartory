package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import factory.foobar.Bar;
import factory.foobar.Foo;
import factory.foobar.Foobar;
import factory.moneybox.Moneybox;
import factory.robots.Robot;
import factory.robots.Robots;


public class Main {
		

	public static void main(String[] args) {

		// Initialization
		Robots robots = new Robots();
		robots.getRobotList().add(new Robot());
		robots.getRobotList().add(new Robot());
		List<Foo> fooStock = new ArrayList<Foo>();
		List<Bar> barStock = new ArrayList<Bar>();
		List<Foobar> foobarStock = new ArrayList<Foobar>();	
		Moneybox moneybox = new Moneybox();
		double timer = 0;
		
		
		while (robots.size() < 30) {
			double clock = 0;
			while(clock < 30) { // mine foo and bar
				for(Robot robot : robots.getRobotList()) {
					if(robot.getRobotId()%2 == 0) {	
						barStock.add(robot.createOneBar());
						timer += robot.getDurationTask();
						clock += robot.getDurationTask();
					} else {
						fooStock.add(robot.createOneFoo());
						timer += robot.getDurationTask();
						clock += robot.getDurationTask();
					}
				}
			}
			
			//Robots change their activity (5sec)
			timer += Robot.CHANGE_ACTIVITY_TIME*robots.size();
			
			int index = 0;
			while( index < Math.min(fooStock.size(), barStock.size()) ) {
				Robot robot1 = robots.getRobotByIndex(0);
				Optional<Foobar> foobar = robot1.createOneFoobar(fooStock.get(index), barStock.get(index));
				if(foobar.isPresent()) {
					foobarStock.add(foobar.get());
					fooStock.remove(index);
					barStock.remove(index);
				} else {
					fooStock.remove(index);
				}
				timer += robot1.getDurationTask();
				index ++;
			}
			
			// Sell
			int nbFoobar = foobarStock.size();
			while(foobarStock.size() > 0) {
				timer += Moneybox.SELL_1_TO_5_FOOBAR_TIMER;
				if(foobarStock.size() > 5) {
					foobarStock.remove(0);
					foobarStock.remove(0);
					foobarStock.remove(0);
					foobarStock.remove(0);
					foobarStock.remove(0);
					foobarStock.remove(0);
					moneybox.addMoney(5);
				} else {
					foobarStock.clear();
					moneybox.addMoney(foobarStock.size());
				}
			}
			// Buy
			while(moneybox.getMoneybox() > 2) {
				moneybox.removeMoney(3);
				robots.getRobotList().add(new Robot());
				fooStock.add(new Foo());
				fooStock.add(new Foo());
				fooStock.add(new Foo());
				fooStock.add(new Foo());
				fooStock.add(new Foo());
				fooStock.add(new Foo());	
			}
		}
		System.out.println("Le temps total pour avoir 30 robots est de " + timer + " unité de temps");
	}
}
