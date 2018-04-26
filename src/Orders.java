import java.util.LinkedList;
import java.util.Queue;

public class Orders {
	private int pending;
	private Queue<Order> q = new LinkedList<Order>();
	
	Orders(int total) {
		pending = total;
	}
	
	public synchronized void add(Order order) {
		q.add(order);
		notifyAll();
	}
	
	public synchronized int pending() {
		return pending;
	}
	
	public synchronized Order release() {
		while(q.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pending--;
		return q.remove();
	}
	
}
