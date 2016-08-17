package snack;

import java.util.HashMap;

public class DietaryProfile {
	private double dailyCalorieLimit;
	private HashMap<String, Object> daycaloriesConsumed;
	private double caloriesConsumed;
	private String lowSodium;
	private String lowSugar;
	private String lowCalorie;

	public DietaryProfile(double dailyCalorieLimit) {
		this.dailyCalorieLimit = dailyCalorieLimit;
		
	}
	public void setOtherPreferences(String lowSodium, String lowCalorie, String lowSugar) {
		this.lowSodium = lowSodium;
		this.lowSugar = lowSugar;
		this.lowCalorie = lowCalorie;
	}

	
	
	public double caloriesLeft() {
		return dailyCalorieLimit - caloriesConsumed;
	}

	public double getdailyCalorieLimit() {
		return dailyCalorieLimit;
	}

	public void setdailyCalorieLimit(double dailyCalorieLimit) {
		this.dailyCalorieLimit = dailyCalorieLimit;
	}

	public double getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public void setCaloriesConsumed(double caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}

	public HashMap<String, Object> getdayCaloriesConsumed() {
		return daycaloriesConsumed;
	}

	public void setdayCaloriesConsumed(HashMap<String, Object> daycaloriesConsumed) {
		this.daycaloriesConsumed = daycaloriesConsumed;
	}

	public String getLowSodium() {
		return lowSodium;
	}

	public void setLowSodium(String lowSodium) {
		this.lowSodium = lowSodium;
	}

	

	public String getLowCholestrol() {
		return lowCalorie;
	}

	public void setLowCholestrol(String lowCalorie) {
		this.lowCalorie = lowCalorie;
	}

	public String getLowSugar() {
		return lowSugar;
	}

	public void setLowSugar(String lowSugar) {
		this.lowSugar = lowSugar;
	}

}
