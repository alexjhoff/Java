package snack;

class FoodStore{
	Snack[] snackItems;
	int numItems;
	
	FoodStore(){
		snackItems = new Snack[20];
		numItems = 0;
	}
	
	public Snack getItem(int index){
		return snackItems[index];
	}
	
	public void addItem(Snack newItem){
		snackItems[numItems] = newItem;
		numItems++;
	}
	
}