package factory.robots;

import java.util.ArrayList;
import java.util.List;

public class Robots {
	
	private List<Robot> robotList;
	
	public Robots() {
		this.robotList = new ArrayList<Robot>(2);
	}
	
	public Robots(int nbRobots) {
		this.robotList = new ArrayList<Robot>(nbRobots);
		this.robotList.add(new Robot());
		this.robotList.add(new Robot());
	}
	
	public Robot getRobotByIndex(int index) {
		return this.robotList.get(index);
	}
	
	public List<Robot> getRobotList() {
		return this.robotList;
	}
	
	public int size() {
		return this.robotList.size();
	}
}
