import java.util.LinkedList;
import java.util.Queue;

public class Schedule {

	private Queue<Order> orders = new LinkedList<Order>();
	private Queue<Integer> times = new LinkedList<Integer>();
	private Tables tables = new Tables();
	private int dinersDined = 0;
	
	Schedule(int total, Kitchen kitchen) {
		for(int t=0; t<total; t++) {
			Table table = new Table("Table-" + t, kitchen);
			tables.add(table);
		}
	}
	
	public void add(Order order, int t) {
		orders.add(order);
		times.add(t);
	}
	
	public int nextArrivalTime() {
		if(times.isEmpty()) {
			return -1;
		}else {
			return times.peek();
		}
	}
	
	public void dinerArrives() {
		Diner diner = new Diner(orders.remove(), times.remove());
		diner.start();
	}
	
	public synchronized int dinersDined() {
		return dinersDined;
	}
	
	public synchronized void incrementDinersDined() {
		dinersDined++;
	}
	
	
	private class Diner implements Runnable {
	   private Thread t;
	   private String name;
	   private Order order;
	   private int time;
	   
	   Diner(Order order, int time) {	
	      this.order = order;
	      name = "Diner-" + order.id;
	      this.time = time;
	   }
	   
	   public void run() {
		   order.table = tables.assign();
		   order.table.occupy(name, order);
		   incrementDinersDined();
		   tables.add(order.table);
	   }
	   
	   public void start () {
	      if (t == null) {
	         t = new Thread (this, name);
	         t.start ();
	      }
	      System.out.println(name + " arrived at time " + time);
	   }
	}
	
}
