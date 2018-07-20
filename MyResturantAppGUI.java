import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyResturantAppGUI extends Application {
	
	private static Restaurant restaurant;
	
	private static String resturantName = "";
	
	private static String resturant_cuisine = "";
	
	private static EnumType typeOfCuisineENum;
	
	private static Kitchen kitchen_of_resturant;
	
	private static Scanner s;
	
	
	
	/*
	 * This method was taken from Stack overflow 
	 * @ https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string
	 * It is used to receive the enumType class as a stream, in this case an array of strings. 
	 */
	
	public static String[] getNames(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	public static void promptUser() {
		System.out.println("What is the name of your resturant?");
		resturantName = s.nextLine();
		
	}
	
	public static void verify_UserInput() {
		String c = s.nextLine();
		String [] typesOfCuisines = getNames(EnumType.class);
		while(true) {
			if(Arrays.asList(typesOfCuisines).contains(c)) {
				System.out.println("Yes, I know that cuisine");
				typeOfCuisineENum = EnumType.valueOf(c);
				resturant_cuisine = c;
				break;
			}
			else {
				System.out.println("What the fuck is that food?");
				System.out.println("The cuisine that I know are: " + Arrays.asList(typesOfCuisines).toString());
				System.out.println("Type another cuisine.");
				c = s.nextLine();
			}
		}
	}
	
	public static void get_start_info() {
		s = new Scanner(System.in);
		promptUser();
		verify_UserInput();
		kitchen_of_resturant = null;
		restaurant = new Restaurant(resturantName, typeOfCuisineENum, 0, kitchen_of_resturant);
		s.close();
	}
	
	public static void main(String [] args) {
		
		// Get the starting information for the app
		get_start_info();
		
		
		
		String cuisine = s.nextLine();
		Map<Integer, Dish> todaysOrders = new HashMap<Integer, Dish>();
		Kitchen peruvian = new Kitchen(cuisine, EnumType.Peruvian);
		Restaurant incasPeruvianCuisine = new Restaurant(res_name, EnumType.Peruvian, 5, peruvian);
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
//		launch(args);
		
		
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
//		stage.setTitle("Welcome to " + );
		
	}
}
