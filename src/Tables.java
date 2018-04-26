import java.util.LinkedList;
import java.util.Queue;

public class Tables {
	
	private Queue<Table> q = new LinkedList<Table>();
	
	Tables(){}
	
	public synchronized void add(Table table) {
		q.add(table);
		notifyAll();
	}
	
	public synchronized Table assign() {
		while(q.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return q.remove();
	}
	
	
}
