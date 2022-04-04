package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import factory.foobar.Bar;
import factory.foobar.Foo;
import factory.foobar.Foobar;
import factory.moneybox.Moneybox;
import factory.robots.Robot;
import factory.robots.Robots;

public class MainTest {
	
	private Robot robot = new Robot();
	private Robots robots = new Robots();
	private Moneybox money = new Moneybox();
	
	@Test
	public void givenFooAndBarAreCreated_whenProbabilitiesAreGood_thenFoobarIsCreate () {
		// When
		List<Foo> fooList =  new ArrayList<Foo>();
		List<Bar> barList =  new ArrayList<Bar>();
		
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		
		barList.add(new Bar());
		barList.add(new Bar());
		barList.add(new Bar());
		barList.add(new Bar());
		

		Optional<Foobar> foobar = Optional.empty();
		int index = 0;
		while(foobar.isEmpty()) {
			foobar = robot.createOneFoobar(fooList.get(index), barList.get(index));
			index++;
		}
		
		assertEquals(foobar.isPresent(), true);
	}
	
	@Test
	public void givenThereIsOneToFiveFoobars_whenItSell_ThenOneEuroIsEarnByFoobar() {
		List<Foobar> foobarList = new ArrayList<Foobar>();
		Foo foo = new Foo();
		Bar bar = new Bar();
		foobarList.add(new Foobar(foo.getFooId(), bar.getBarId()));

		Foo foo1 = new Foo();
		Bar bar1 = new Bar();
		foobarList.add(new Foobar(foo1.getFooId(), bar1.getBarId()));

		Foo foo2 = new Foo();
		Bar bar2 = new Bar();
		foobarList.add(new Foobar(foo2.getFooId(), bar2.getBarId()));

		Foo foo3 = new Foo();
		Bar bar3 = new Bar();
		foobarList.add(new Foobar(foo3.getFooId(), bar3.getBarId()));
		
		int size = foobarList.size();
		
		foobarList = sell(foobarList);
		
		assertEquals(foobarList.size(), 0);
		assertEquals(money.getMoneybox(), size);		
	}
	
	private List<Foobar> sell(List<Foobar> foobarList) {
		if(foobarList.size() > 5) {
			foobarList.remove(0);
			foobarList.remove(0);
			foobarList.remove(0);
			foobarList.remove(0);
			foobarList.remove(0);
			foobarList.remove(0);
			money.addMoney(5);
		} else {
			money.addMoney(foobarList.size());
			foobarList.clear();
		}
		return foobarList;
	}

	@Test
	public void givenThereIsThreeEuros_whenOneRobotIsBought_ThenMoneyboxLooseThreeEuros() {
		
		money.addMoney(3);
		int moneyBeforeBuy = money.getMoneybox();
		
		List<Foo> fooList = new ArrayList<Foo>();
		int fooListBeforeBuy = fooList.size();
		fooList = buy(fooList);
		
		assertEquals(moneyBeforeBuy - 3, money.getMoneybox());
		assertEquals(fooListBeforeBuy + 6, fooList.size());
	}
	
	private List<Foo> buy(List<Foo> fooList) {
		money.removeMoney(3);
		robots.getRobotList().add(new Robot());
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		fooList.add(new Foo());
		return fooList;
	}
}
