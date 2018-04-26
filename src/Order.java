import java.util.stream.IntStream;

public class Order {
	public int id;
	public int[] list= new int[4];
	public Table table;
	public int deliveryTime;

	Order(int id, int[] list) {
		this.id = id;
		this.list = list;
	}
	
	public Boolean completed() {
		return IntStream.of(list).sum() == 0;
	}
	
}
