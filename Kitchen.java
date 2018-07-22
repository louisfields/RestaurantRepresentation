import java.util.HashMap;
import java.util.Map;

public class Kitchen {
	
	private String name;
	
	private EnumType type;
	
	private Map<Integer, Dish> dishesToMake;
	
	
	public Kitchen(String name, EnumType type) {
		//Add a dish to the list of dishes this kitchen has to make.
		this.name = name;
		this.type =  type;
		dishesToMake = new HashMap<Integer, Dish>();
	}
	
	public void setDishesToMake(Map<Integer, Dish> dishesToMake) {
		this.dishesToMake = dishesToMake;
	}
	
	public void queueUpOrder(int orderNumber, Dish d) {
		this.dishesToMake.put(orderNumber, d);
	}
	
	public Map<Integer, Dish> getKitchenOrders(){
		return this.dishesToMake;
	}
	
	public Dish make(int orderNumber) {
		try {
			Dish dish = dishesToMake.get(orderNumber);
			System.out.println(dish.getName() + " is prepared!");
			cook();
			prepareForServing();
			return dish;
			
			
		}
		catch(Exception e) {
			System.out.println("We did not get an order.");
			return null;
		}

	}
	
	public void cook() {
		System.out.println("Food is cooked!");
	}
	
	public void prepareForServing() {
		System.out.println("Food is ready to be delivered.");
	}
	
	public void ding() {
		System.out.println("Get your food now, before it gets cold.");
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public EnumType getType() {
		return this.type;
	}
	
	
	
	
	
	
	

}
