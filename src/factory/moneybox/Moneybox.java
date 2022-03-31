package factory.moneybox;

public class Moneybox {

	private int moneybox;
	public static int SELL_1_TO_5_FOOBAR_TIMER = 10;
	
	public Moneybox() {
		this.moneybox = 0;
	}
	
	public int getMoneybox() {
		return this.moneybox;
	}
	
	public void addMoney(int money) {
		this.moneybox += money;
	}
	
	public void removeMoney(int money) {
		this.moneybox -= money;
	}
}
