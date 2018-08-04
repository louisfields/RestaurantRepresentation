import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MyResturantAppGUI extends Application {
	
	/*
	 * Back-end declarables
	 */

	private static Restaurant restaurant;

	private static String resturantName = "";
	
	private static String restaurantAddress = "";

	private static String resturant_cuisine = "";

	private static EnumType typeOfCuisineENum;

	private static Kitchen kitchen_of_resturant;

	private static Scanner s;
	
	/*
	 * GUI Application types
	 * 
	 */
	
	private BorderPane window;
	
	private static final int width = 300;
	
	private static final int height = 360;
	
	private static MenuBar menuBar;
	
	private GridPane informationPane;
	
	private Label [] resturantInfoLabels;


	
	
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
		String line1 = "";
		String line2 = "";
		
		System.out.println("What is the name of your resturant?");
		while (!(line1 = s.nextLine()).isEmpty()) {
			resturantName = line1;
			System.out.println("What is the address of your resturant?");
			if (!(line2 = s.nextLine()).isEmpty()) {
				restaurantAddress = line2;
				break;
			}
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
		restaurant = new Restaurant(resturantName, typeOfCuisineENum, 0, kitchen_of_resturant, restaurantAddress);
		restaurant.setBusySeason(false);

	}

	public static void order_dishes(Map<Integer, Dish> todaysOrders) {
		// Lets take some orders
		String[] orders_spliced;
		String orderLine = "";
		System.out.println("Lets make some orders!");
		System.out.println("Enter orders in the the following comma seperated format: dish1,dish2,dish3,dish4...");
		orderLine = s.nextLine();
		orders_spliced = orderLine.split(",");
		// Potential bug
		for (int i = 0; i < orders_spliced.length; i++) {
			System.out.println("Bug here");
			todaysOrders.put(i + 1, new Dish(orders_spliced[i]));
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
	  private class MenuItemListener implements EventHandler<ActionEvent> {

		    @Override
		    public void handle(ActionEvent e) {
		    	Alert alert = new Alert(AlertType.CONFIRMATION, "You want a menu item!", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		    	alert.showAndWait();

		    	if (alert.getResult() == ButtonType.YES) {
		    	    //do stuff
		    		
		    	}
		    	  System.out.println("You touched a menu item!");
		      // Find out the text of the JMenuItem that was just clicked
//		      String text = ((MenuItem) e.getSource()).getText();
//		      if (text.equals("Button"))
//		        setViewTo(buttonView);
//		      else if (text.equals("Text"))
//		        setViewTo(textAreaView);
//		      else if (text.equals("Drawing"))
//		          setViewTo(drawingView);
//		      else if (text.equals("New Game"))
//		        theGame.startNewGame(); // The computer player has been set and should not change.
//		      else if (text.equals("Intermediate"))
//		        theGame.setComputerPlayerStrategy(new IntermediateAI());
//		      else if (text.equals("RandomAI"))
//		        theGame.setComputerPlayerStrategy(new RandomAI());
		    }
		  }
	

	
	private void setUp_restaurantInfo() {
		informationPane = new GridPane();
		informationPane.setHgap(5);
		informationPane.setVgap(5);
		resturantInfoLabels = new Label [4];
		Label header = new Label("Resturant Information: ");
		//WHY YOU NO WORK
		header.setStyle("-fx-font-weight: bold;");
		//Might want to refactor this a little bit. does it matter if I am using the static variables or the information from 
		//The created resturant object? I don't plan on changing the resturant in the current appilcation launch.
		resturantInfoLabels[0] = new Label("Restaurant name: " + resturantName);
		resturantInfoLabels[1] = new Label("Type of Cuisine served: " + resturant_cuisine);
		resturantInfoLabels[2] = new Label("Critic rating: " + restaurant.getRating());
		resturantInfoLabels[3] = new Label("Location/Address: " + restaurant.getAddress());
		informationPane.add(header, 0, 0);
		informationPane.add(resturantInfoLabels[0], 0, 1);
		informationPane.add(resturantInfoLabels[1], 0, 2);
		informationPane.add(resturantInfoLabels[2], 0, 3);
		informationPane.add(resturantInfoLabels[3], 0, 4);
	    Menu options = new Menu("Options");

	    menuBar = new MenuBar();
	    menuBar.getMenus().addAll(options);
	 
	    // Add the same listener to all menu items requiring action
	    MenuItemListener menuListener = new MenuItemListener();
	    window.setTop(menuBar);
	    BorderPane.setMargin(informationPane, new Insets(30, 30, 0, 30));
	    BorderPane.setAlignment(informationPane, Pos.CENTER);
	    window.setCenter(informationPane);
	    
		
		
		
		
	}

	public void setUpGUI(Stage s) {
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		setUp_restaurantInfo();
		
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
		//Send orders to kitchen
		order_dishes(todaysOrders);
		//Tell kitchen to make the dishes
		make_dishes();
		//Launch the GUI
	    launch(args);
	    //Close Scan
		s.close();

	}
}
