package factory.foobar;

public class Foobar {
	
	private String foobarId;
	
	public Foobar(int fooId, int barId) {
	    this.foobarId = "" + fooId + barId;
	}

	public String getFoobarId() {
		return foobarId;
	}
}
