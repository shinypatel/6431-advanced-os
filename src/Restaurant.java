import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Restaurant {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		int diners = Integer.parseInt(br.readLine().trim());
		int tables = Integer.parseInt(br.readLine().trim());
		int cooks = Integer.parseInt(br.readLine().trim());

		TickTack clock = new TickTack();
		Kitchen kitchen = new Kitchen(clock, diners, cooks);
		Schedule schedule = new Schedule(tables, kitchen);

		int id = 0;

		String line;
		while ((line = br.readLine()) != null) {
			String[] arr = line.split("\\s+");

			int len = arr.length;
			int[] list = new int[len];
			for(int i=0; i<len; i++) {
				list[i] = Integer.parseInt(arr[i]);
			}

			int time = list[0];
			list = Arrays.copyOfRange(list, 1, len); 

			Order order = new Order(id, list);
			schedule.add(order, time);

			id++;
		}
		br.close();
		

		System.out.println("\nWelcome to Restaurant 643=(\n");	
		
		clock.start(schedule);
		while(schedule.dinersDined() != diners) {
			
		}
		
		System.out.println("Last diner has left!");
		System.out.println("\nRestaurant 643=) is now closed...");
		clock.stop();
		System.exit(-1);
		
	}
}
