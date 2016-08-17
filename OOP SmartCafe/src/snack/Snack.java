package snack;


public class Snack{
	String name;
	int price;
	int calories;
	int stock;
	boolean lowSodium;
	boolean lowCholesterol;
	boolean lowCalorie;
	
	
	Snack(String name, int price, int calories, int stock, boolean sodium, boolean cholesterol){
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.stock = stock;
		this.lowSodium = sodium;
		this.lowCholesterol = cholesterol;
		
		if(calories <= 100){
			this.lowCalorie = true;
		}
	}
	
	public void updateStock(int purchase){
		if(stock >= purchase)
			stock -= purchase;
		else
			System.out.println("Not enough" + name + "in stock stock");
	}
	
	public String getName(){
		return name;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getCalories(){
		return calories;
	}
	
	public int getStock(){
		return stock;
	}
	
	public boolean isLowSod(){
		return lowSodium;
	}
	
	public boolean isLowChol(){
		return lowCholesterol;
	}
	
	public boolean isLowCal(){
		return lowCalorie;
	}
}










