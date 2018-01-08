import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Whilst being bored and trying to study for my CS335 final, I drafted up a simple little restaurant reprez 
 * that I am a little too proud of.
 *
 * @author Luis Manuel Campos
 */

public class Restaurant {

	private String name;
	
	private EnumType type;
	
	private int rating;
	
	Kitchen kitchen;
	
	private boolean isBusySeason;
	
	
	
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
	
	public void setRating(int rating) {
		this.rating =  rating;
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
		Restaurant incasPeruvianCuisine = new Restaurant("Inca's Peruvian Cuisine", EnumType.Peruvian, 5, peruvian);
		incasPeruvianCuisine.setBusySeason(true); //It's winter season, snowbirds are here.
		//Lets take some orders
		todaysOrders.put(1, new Dish("Lomo Saltado"));
		todaysOrders.put(2,  new Dish("Aji de Gallina"));
		todaysOrders.put(3, new Dish("Ceviche Mixto"));
		incasPeruvianCuisine.kitchen.dishesToMake = todaysOrders;
		//The kitchen now knows todays orders, lets test it.
		Set<Integer> orderNumbers = incasPeruvianCuisine.kitchen.dishesToMake.keySet();
		
		System.out.println("Dishes to make today: \n");
		
		for(Integer index :  orderNumbers) {
			Dish currentDish = incasPeruvianCuisine.kitchen.dishesToMake.get(index);
			System.out.println(index + ". " + currentDish.getName());
			incasPeruvianCuisine.kitchen.make(index);
			System.out.println(); incasPeruvianCuisine.kitchen.ding(); System.out.println("\n---> Llevate tu " + currentDish.getName()); System.out.println();
		}
		
		
		
		
		
		
	}
	
	
}
