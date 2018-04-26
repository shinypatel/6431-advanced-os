
public class Table {
	private String name;
	private Order order;
	private Kitchen kitchen;
	
	Table(String name, Kitchen kitchen) {
		this.name = name;
		System.out.println(name + " ready!");
		this.kitchen = kitchen;
	}
	
	public synchronized void deliver(String cook) {
		System.out.println(cook + " DELIVERED order " + order.id + " at time " + order.deliveryTime);
		notifyAll();
	}
	
	public synchronized void occupy(String diner, Order order) {
		this.order = order;
	    System.out.println(diner + " was seated at " + name + " at time " + kitchen.clock.getTime());
	    
	    kitchen.orders.add(order);
	    
	    try {	// wait for cook to deliver order
	    	wait();
	    } catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }

	    int time;
		do {
			time = kitchen.clock.getTime();
		}
	    while(time - order.deliveryTime < 30);
	    
	    System.out.println(diner + " FINISHED EATING at time " + time);
	}
	
}
