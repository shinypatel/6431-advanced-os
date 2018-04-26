
public class Machine {
	private String name;
	private boolean inUse = false;
	private TickTack clock;
	
	Machine(String name, TickTack clock) {
		this.name = name;
		this.clock = clock;
		System.out.println(name + " ready!");
	}
	
	public synchronized boolean isInUse() {
		if(!inUse) {
			inUse = true;
			return false;
		}
		return inUse;
	}
	
	public void use(String cook, int id, long time) {
	    int endTime, startTime = clock.getTime();
		System.out.println(cook + " used " + name + " at time " + startTime + " for order " + id);
	    
		do {
			endTime = clock.getTime();
		}
	    while(endTime - startTime < time);
	    
	    System.out.println(cook + " finished using " + name + " at time " + endTime + " for order " + id);
	    inUse = false;
	}
	
}
