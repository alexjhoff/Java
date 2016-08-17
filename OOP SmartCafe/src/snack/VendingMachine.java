    package snack;

public class VendingMachine extends FoodStore{
	
	public void purchaseItem(String item){
		for(int i=0; i < numItems; i++){
			if(snackItems[numItems].getName().equals(item)){
				//check users funds, dietary preferences and calories are sufficient
				//if so subtracts from users profile
				
				System.out.println("Thank you for your order your" + snackItems[numItems].getName());
				System.out.println("is being dispensed now");
			}
		}
	}
}