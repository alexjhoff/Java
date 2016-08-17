package snack;

public class User {
	
	private String name;
	private String cardNumber;
	private String Password;
	public ExpenseProfile expenseProfile;
	public DietaryProfile dietaryProfile;
	
	public  User( String name, String cardNumber, String Password,ExpenseProfile expenseProfile,DietaryProfile dietaryProfile) {
		this.setName(name);
		this.cardNumber = cardNumber;
		this.Password = Password;
		this.expenseProfile = expenseProfile;
		this.dietaryProfile = dietaryProfile;
	}
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}	
	public ExpenseProfile getExpenseProfile() {
		return expenseProfile;
	}
	public void setExpenseProfile(ExpenseProfile expenseProfile) {
		this.expenseProfile = expenseProfile;
	}
	public DietaryProfile getDietaryProfile() {
		return dietaryProfile;
	}
	public void setDietaryProfile(DietaryProfile dietaryProfile) {
		this.dietaryProfile = dietaryProfile;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
