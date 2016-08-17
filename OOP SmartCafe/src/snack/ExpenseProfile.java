package snack;

public class ExpenseProfile {
	private double totalFunds;
	private  double spentFunds;
	 public ExpenseProfile(double totalFunds) {
		 this.totalFunds = totalFunds;
	 }
	 public double getAvailableFunds() {
		 return totalFunds - spentFunds;
	 }
	 
	public double getTotalFunds() {
		return totalFunds;
	}
	public void setTotalFunds(double totalFunds) {
		this.totalFunds = totalFunds;
	}
	public double getSpentFunds() {
		return spentFunds;
	}
	public void setSpentFunds(double spentFunds) {
		this.spentFunds = spentFunds;
	}
	public double getFundsLeft() {
		return totalFunds - spentFunds;
	}
}
