import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Whilst being bored and trying to study for my CS335 final, I drafted up a simple little restaurant reprez 
 * I am a little to proud of.
 *
 * @author Luis Manuel Campos
 */

public class Restaurant {

	private String name;
	
	private EnumType type;
	
	private int rating;
	
	private boolean isBusySeason;
	
	Kitchen kitchen;
	
	public Restaurant(String name, EnumType type, int rating, Kitchen kitchen) {
		this.name = name;
		this.type = type;
		this.rating = rating;
		this.kitchen = kitchen;
		this.isBusySeason = false;
				
	}
	
	public Dish orderDish(int orderNumber) {
		Dish dish =  kitchen.make(orderNumber);
		
		return dish;
	}
	
	public String getName() {
		return this.name;
	}
	
	public EnumType getType() {
		return this.type;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public Kitchen getKitchen() {
		return this.kitchen;
	}
	
	public void setBusySeason(boolean maybeBusy) {
		this.isBusySeason = maybeBusy;
	}
	
	public boolean isBusy() {
		return this.isBusySeason;
	}
	
	public static void main(String [] args) {
		Map<Integer, Dish> todaysOrders = new HashMap<Integer, Dish>();
		Kitchen peruvian = new Kitchen("Peruvian", EnumType.Peruvian);
		Restaurant best = new Restaurant("Inca's Peruvian Cuisine", EnumType.Peruvian, 5, peruvian);
		best.setBusySeason(true); //It's winter season, snowbirds are here.
		//Lets take some orders
		todaysOrders.put(1, new Dish("Lomo Saltado"));
		todaysOrders.put(2,  new Dish("Aji de Gallina"));
		todaysOrders.put(3, new Dish("Ceviche Mixto"));
		best.kitchen.dishesToMake = todaysOrders;
		//The kitchen now knows todays orders, lets test it.
		Set<Integer> orderNumbers = best.kitchen.dishesToMake.keySet();
		System.out.println("Dishes to make today: ");
		for(Integer index :  orderNumbers) {
			System.out.println(index + ". " + best.kitchen.dishesToMake.get(index).getName());
			best.kitchen.make(index);
			System.out.print("\n---> "); best.kitchen.ding(); System.out.println();;
		}
		
		
		
		
		
	}
	
	
}
