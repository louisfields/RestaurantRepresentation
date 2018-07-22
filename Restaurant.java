import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.stage.Stage;

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
	
	private Kitchen kitchen;
	
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
	
	public boolean isBusySeason(String date) {
		
		//Given the date as a string, determine first if it is close to a holiday
		
		return this.isBusySeason;
	}
	
	public void setResturantName(String s) {
		this.name = s;
	}

}
