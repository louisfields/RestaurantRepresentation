import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyResturantAppGUI extends Application {
	
	/*
	 * Back-end declarables
	 */

	private static Restaurant restaurant;

	private static String resturantName = "";

	private static String resturant_cuisine = "";

	private static EnumType typeOfCuisineENum;

	private static Kitchen kitchen_of_resturant;

	private static Scanner s;
	
	/*
	 * GUI Application types
	 * 
	 */
	
	private BorderPane window;
	private static final int width = 254;
	private static final int height = 360;
	
/*
 * Back-end methods
 */
	
	/*
	 * This method was taken from Stack overflow
	 * 
	 * @
	 * https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-
	 * a-string It is used to receive the enumType class as a stream, in this case
	 * an array of strings.
	 */

	public static String[] getNames(Class<? extends Enum<?>> e) {
		return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

	public static void promptUser() {
		String line = "";
		System.out.println("What is the name of your resturant?");
		while (!(line = s.nextLine()).isEmpty()) {
			resturantName = line;
			break;
		}
	}

	public static void verify_UserInput() {
		resturant_cuisine = "";
		System.out.println("Tell me about your cuisine...");
		String c = ""; 
		String[] typesOfCuisines = getNames(EnumType.class);
		while (!((c = s.nextLine()).isEmpty())) {
			if (Arrays.asList(typesOfCuisines).contains(c)) {
				System.out.println("Yes, I know that cuisine.");
				typeOfCuisineENum = EnumType.valueOf(c);
				resturant_cuisine = c;
				break;
			} else {
				System.out.println("What the fuck is that food?");
				System.out.println("The cuisine's that I know are: ");
				for(String cuisine: typesOfCuisines) {
					System.out.println(cuisine);
				}
				System.out.println("Type another cuisine.");
				resturant_cuisine = "";
			}
		}
	}

	public static void get_info_from_user() {
		promptUser();
		verify_UserInput();
		kitchen_of_resturant = new Kitchen(resturantName, typeOfCuisineENum);
		restaurant = new Restaurant(resturantName, typeOfCuisineENum, 0, kitchen_of_resturant);
		restaurant.setBusySeason(false);

	}

	public static void order_dishes(Map<Integer, Dish> todaysOrders) {
		// Lets take some orders
		String[] orders_splcied;
		String orderLine = "";
		System.out.println("Lets make some orders!");
		System.out.println("Enter orders in the the following comma seperated format: dish1,dish2,dish3,dish4...");
		orderLine = s.nextLine();
		orders_splcied = orderLine.split(",");
		// Potential bug
		for (int i = 0; i < orders_splcied.length; i++) {
			System.out.println("Bug here");
			todaysOrders.put(i + 1, new Dish(orders_splcied[i]));
		}
		restaurant.getKitchen().setDishesToMake(todaysOrders);
	}

	public static void make_dishes() {
		System.out.println("Dishes to make today: \n");
		Set<Integer> orderNumbers = restaurant.getKitchen().getKitchenOrders().keySet();
		for (Integer index : orderNumbers) {
			Dish currentDish = kitchen_of_resturant.getKitchenOrders().get(index);
			System.out.println(index + ". " + currentDish.getName());
			kitchen_of_resturant.make(index);
			System.out.println();
			kitchen_of_resturant.ding();
			System.out.println("\n---> Llevate tu " + currentDish.getName());
			System.out.println();
		}
	}

/*
* GUI Methods
*/

	public void setUpGUI(Stage s) {
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		s.setScene(scene);
		s.show();
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		 stage.setTitle("Welcome to " + resturantName);
		 setUpGUI(stage);
		   

	}
	
/*
 * MAIN
 */
	
	
	public static void main(String[] args) {
		// A Scanner object to scan for input
		s = new Scanner(System.in);
		// Get the starting information for the app
		get_info_from_user();
		// Get ready for today's orders
		Map<Integer, Dish> todaysOrders = new HashMap<Integer, Dish>();
		order_dishes(todaysOrders);
		// Make the dishes
		make_dishes();
	    launch(args);
		s.close();

	}
}
