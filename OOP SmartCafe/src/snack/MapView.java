package snack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import snack.JSONParserImp;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.GridBagLayout;


public class MapView extends JFrame {

	private JLayeredPane contentPane;
	private JTextField cals;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField userName;
	private JTextField password;
	
	//sign in vars
	private boolean flag, store;
	private JPanel userP, resultP;
	private JLabel lblSignIn, lblUsername_1, lblPassword_1, resultLabel;
	private JButton btnSignUp_1, btnCancel, btnBack_1, btnClose;
	private JTextField newCals;
	private JTextField newFunds;
	JRadioButton rdbtnLowSodium_1, rdbtnLowSugar_1;
	JButton btnViewDietaryProfile;
	JButton btnViewExpenseProfile;
	JButton btnEditDietaryProfile;
	
	//cafe vars
	private JPanel panel_1,panel_4;
	
	
	//Profile vars
	private JPanel panel_2, panel_3, panel;
	private JRadioButton rdbtnLowSodium, rdbtnLowSugar;
	
	protected static final String UserCredentialsfile = "/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/User.txt";
	protected static final String UserPreferencesFile = "/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/Preferences.txt";
	protected static final String DietaryProfileFile = "/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/DietaryProfile.txt";
	protected static final String ExpenseProfileFile = "/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/ExpenseProfile.txt";
	public JSONParserImp jsonParserImp;
	
	public FoodStore FS;
	public VendingMachine VM = new VendingMachine();
	public Cafe BC = new Cafe();
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MapView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 0, 1300, 717);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imgThisImg = new ImageIcon("/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/map.png");
		jsonParserImp = new JSONParserImp();
		createBC();
		createVM();
		
		JButton btnSignUp = new JButton("Sign Up");
		SignUpHandler signUp = new SignUpHandler();
		btnSignUp.addActionListener(signUp);
		
		JRadioButton rdbtnVendingMachine = new JRadioButton("Vending Machine");
		rdbtnVendingMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(store == true){
					contentPane.remove(panel_1);
				}
				rdbtnVendingMachine.setSelected(false);
				panel_1 = getCafePanel("Vending Machine");
				contentPane.add(panel_1,1,1);
				store = true;
			}
		});
		
		JRadioButton rdbtnBensonCafe = new JRadioButton("Benson Cafe");
		rdbtnBensonCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(store == true){
					contentPane.remove(panel_1);
				}
				rdbtnBensonCafe.setSelected(false);
				panel_1 = getCafePanel("Benson Cafe");
				contentPane.add(panel_1,1,2);
				store = true;
			}
		});
		rdbtnBensonCafe.setBounds(506, 570, 141, 23);
		contentPane.add(rdbtnBensonCafe);
		rdbtnVendingMachine.setBounds(482, 134, 141, 23);
		contentPane.add(rdbtnVendingMachine);
		btnSignUp.setBounds(1177, 0, 117, 29);
		contentPane.add(btnSignUp);
	
		
		JButton btnLogin = new JButton("Login");
		LoginHandler login = new LoginHandler();
		btnLogin.addActionListener(login);
		btnLogin.setBounds(1051, 0, 117, 29);
		contentPane.add(btnLogin);
		
		
		
		JComboBox cbCafeList = new JComboBox();
		cbCafeList.setBounds(5,10,200,16); 
		cbCafeList.addItem("--select a cafe--");
		cbCafeList.addItem("Benson Cafe");
		cbCafeList.addItem("Vending Machine");

		cbCafeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String cafeName = (String)cb.getSelectedItem();
		        if(cafeName.equals("Benson Cafe")){
		        	if(store == true){
						contentPane.remove(panel_1);
					}
					panel_1 = getCafePanel("Benson Cafe");
					contentPane.add(panel_1);
					store = true;
		        }else if(cafeName.equals("Vending Machine")){
		        	if(store == true){
						contentPane.remove(panel_1);
					}
					panel_1 = getCafePanel("Vending Machine");
					contentPane.add(panel_1);
					store = true;
		        }else{
		        	if(store == true){
						contentPane.remove(panel_1);
					}
		        }
		        
			}
		});
		contentPane.add(cbCafeList);
		
		
		btnViewDietaryProfile = new JButton("View Dietary Profile");
		btnViewDietaryProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel pan1 = jsonParserImp.DietaryProfBar();
				JPanel pan = jsonParserImp.DietaryProfLine();
				JFrame frame = new JFrame();
				frame.getContentPane().setLayout(new FlowLayout());
				frame.getContentPane().add(pan);
				frame.getContentPane().add(pan1);
				frame.pack();
				frame.setVisible(true);
				panel_3 = getDietaryPanel();
				contentPane.add(panel_3);
			}
		});
		btnViewDietaryProfile.setBounds(883, 0, 156, 29);
		contentPane.add(btnViewDietaryProfile);
		btnViewDietaryProfile.setVisible(false);
		
		btnViewExpenseProfile = new JButton("View Expense Profile");
		btnViewExpenseProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JPanel pan1 = jsonParserImp.ExpenseProfBar();
					JPanel pan = jsonParserImp.ExpenseProfLine();
					JFrame frame = new JFrame();
					frame.getContentPane().setLayout(new FlowLayout());
					frame.getContentPane().add(pan);
					frame.getContentPane().add(pan1);
					frame.pack();
					frame.setVisible(true);
					panel_2 = getExpensePanel();
					contentPane.add(panel_2);
			}
		});
		btnViewExpenseProfile.setBounds(698, 0, 173, 29);
		contentPane.add(btnViewExpenseProfile);
		btnViewExpenseProfile.setVisible(false);
		
		btnEditDietaryProfile = new JButton("Edit Preferences");
		btnEditDietaryProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = getEditDietPanel();
				contentPane.add(panel);
			}
		});
		btnEditDietaryProfile.setBounds(530, 0, 156, 29);
		contentPane.add(btnEditDietaryProfile);
		btnEditDietaryProfile.setVisible(false);
		
		
		
		
		//Map View
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 30, 1300, 687);
		lblNewLabel.setIcon(imgThisImg);
		contentPane.add(lblNewLabel,3);
	}
	

	/**
	 * User login class
	 */
	class LoginHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			userP = getSignInPanel();
			contentPane.add(userP);
		}
		
	}
	
	/**
	 * User sign up class
	 */
	class SignUpHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			userP = getNewUser();
			contentPane.add(userP);
		}
		
	}
	
	/**
	 * create sign in class
	 */
	class signInHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(lblSignIn.getText().equals("Sign Up")){
				saveUserData();
			}else{
				loginData();
			}

		}
		
		private JTextField newCals;
		private JTextField newFunds;
		JRadioButton rdbtnLowSodium_1, rdbtnLowSugar_1;
		
		public String saveUserData() {
			try {
				JSONObject mainUserObject = new JSONObject();
				String usrName = userName.getText();
				//hard coded required change 
				String name = "VM user";
				String pwd = password.getText();
				String cals = newCals.getText();
				String funds = newFunds.getText();
				String lsod = "false";
				String lsug = "false";
				String lcal = "false";
				if(rdbtnLowSodium_1.isSelected())
					lsod = "true";
				if(rdbtnLowSugar_1.isSelected())
					lsug = "true";
				if(Integer.parseInt(cals) <= 1500)
					lcal = "true";
				
				DietaryProfile DP = new DietaryProfile(Double.parseDouble(cals));
				DP.setOtherPreferences(lsod, lcal, lsug);
				ExpenseProfile EP = new ExpenseProfile(Double.parseDouble(funds));
				
				//SOMEHOW NEED TO SET PROFILE INFORMATION
				
				System.out.println(usrName);
				System.out.println(pwd);
				JSONObject userObject = jsonParserImp.createUserObject(usrName, pwd);
				JSONArray userArray = jsonParserImp.getRootValueArray(UserCredentialsfile, "Users");
				userArray.add(userObject);
				mainUserObject.put("Users", userArray);
				jsonParserImp.writeJSONToUserFile(mainUserObject, UserCredentialsfile);
				jsonParserImp.setLoggedUser(usrName);
				jsonParserImp.populateUserObj(name, usrName,pwd);
				
				contentPane.remove(userP);
				contentPane.revalidate();
				contentPane.repaint();
				
				JOptionPane.showMessageDialog(null, "SIGN UP COMPLETE!");
				
				
			}catch(Exception e) {System.out.println(e.toString());}	
			
			//System.out.println(jsonParserImp.getLoggedUser());
			return "Sign up complete!";
		}
		
		public String loginData() {
			try {
				JSONObject mainUserObject = new JSONObject();
				String usrName = userName.getText();
				//hard coded required change 
				String name = "VM user";
				String pwd = password.getText();
				System.out.println("User:"+usrName);
				if(!usrName.isEmpty() || !pwd.isEmpty()){
				
				if(jsonParserImp.userExists(usrName,UserCredentialsfile,"Users")) {
					System.out.println("User:"+usrName);
						if(jsonParserImp.isValidUser(usrName, pwd)) {
							//log the user and add which ever window you want to display
							jsonParserImp.setLoggedUser(usrName);
							jsonParserImp.populateUserObj(name, usrName,pwd);
							
							btnViewDietaryProfile.setVisible(true);
							btnViewExpenseProfile.setVisible(true);
							btnEditDietaryProfile.setVisible(true);
							
							contentPane.remove(userP);
							contentPane.revalidate();
							contentPane.repaint();
							
							JOptionPane.showMessageDialog(null, "SIGN IN COMPLETE!","CONGRADULATIONS", JOptionPane.INFORMATION_MESSAGE);
							
							return "Login complete!";
						}else{
							JOptionPane.showMessageDialog(null, "INCORRECT PASSWORD");
							return "Incorrect password";
						}
				}else{
					System.out.println("User1212:"+usrName);
					JOptionPane.showMessageDialog(null, "INVALID USERNAME");
					return "Invalid Username";
				}
				} else  {
					JOptionPane.showMessageDialog(null, "CARD NUMBER OR THE PASSWORD FIELD IS EMPTY");
					return "Retry";
				}
			}catch(Exception e) {System.out.println(e.toString());}	
			return "Complete";
		}
	}
	
	/**
	 * create the login Form
	 * @return the login panel
	 */
	public JPanel getSignInPanel() {
		
		userP = new JPanel();
		userP.setBounds(400, 250, 340, 225);
		userP.setLayout(null);
		
		lblSignIn = new JLabel("Login");
		lblSignIn.setBounds(142, 6, 61, 16);
		userP.add(lblSignIn);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(26, 49, 78, 16);
		userP.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(26, 91, 61, 16);
		userP.add(lblPassword_1);
		
		
		userName = new JTextField();
		userName.setBounds(108, 44, 130, 26);
		userP.add(userName);
		userName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(108, 86, 130, 26);
		userP.add(password);
		password.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(userP);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnCancel.setBounds(180, 161, 117, 29);
		userP.add(btnCancel);
		
		JButton btnSignUp_1 = new JButton("Sign Up");
		signInHandler signIn = new signInHandler();
		btnSignUp_1.addActionListener(signIn);
		btnSignUp_1.setBounds(40, 161, 117, 29);
		userP.add(btnSignUp_1);
		
		return userP;
	}
	
	
	/**
	 * create the new user Form
	 * @return the new user panel
	 */
	public JPanel getNewUser(){
		userP = new JPanel();
		userP.setBounds(400, 250, 340, 309);
		userP.setLayout(null);
		
		lblSignIn = new JLabel("Sign Up");
		lblSignIn.setBounds(142, 6, 61, 16);
		userP.add(lblSignIn);
		
		JLabel lblUsername_1_1 = new JLabel("Username");
		lblUsername_1_1.setBounds(26, 49, 78, 16);
		userP.add(lblUsername_1_1);
		
		JLabel lblPassword_1_1 = new JLabel("Password");
		lblPassword_1_1.setBounds(26, 91, 61, 16);
		userP.add(lblPassword_1_1);
		
		
		userName = new JTextField();
		userName.setBounds(108, 44, 130, 26);
		userP.add(userName);
		userName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(108, 86, 130, 26);
		userP.add(password);
		password.setColumns(10);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(userP);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnCancel_1.setBounds(185, 274, 117, 29);
		userP.add(btnCancel_1);
		
		JButton btnSignUp_1_1 = new JButton("Sign Up");
		signInHandler signIn = new signInHandler();
		btnSignUp_1_1.addActionListener(signIn);
		btnSignUp_1_1.setBounds(39, 274, 117, 29);
		userP.add(btnSignUp_1_1);
		
		JLabel lblCalories_1 = new JLabel("Calories");
		lblCalories_1.setBounds(26, 132, 61, 16);
		userP.add(lblCalories_1);
		
		newCals = new JTextField();
		newCals.setBounds(108, 127, 130, 26);
		userP.add(newCals);
		newCals.setColumns(10);
		
		JLabel lblday_1 = new JLabel("/Day");
		lblday_1.setBounds(241, 132, 61, 16);
		userP.add(lblday_1);
		
		JRadioButton rdbtnLowSodium_1 = new JRadioButton("Low Sodium");
		rdbtnLowSodium_1.setBounds(60, 186, 141, 23);
		userP.add(rdbtnLowSodium_1);
		
		JLabel lblDietaryRestrictions = new JLabel("Dietary Restrictions");
		lblDietaryRestrictions.setBounds(26, 165, 130, 16);
		userP.add(lblDietaryRestrictions);
		
		JRadioButton rdbtnLowSugar_1 = new JRadioButton("Low Sugar");
		rdbtnLowSugar_1.setBounds(60, 211, 141, 23);
		userP.add(rdbtnLowSugar_1);
		
		JLabel lblAvailableFunds = new JLabel("Available Funds");
		lblAvailableFunds.setBounds(26, 246, 99, 16);
		userP.add(lblAvailableFunds);
		
		newFunds = new JTextField();
		newFunds.setBounds(137, 241, 130, 26);
		userP.add(newFunds);
		newFunds.setColumns(10);
		
		JLabel label = new JLabel("$");
		label.setBounds(128, 246, 8, 16);
		userP.add(label);
		
		JLabel lblmonth = new JLabel("/Month");
		lblmonth.setBounds(269, 246, 47, 16);
		userP.add(lblmonth);
		
		return userP;
	}
	
	
	/**
	 * Create expense profile panel
	 */
	public JPanel getExpensePanel(){
		panel_2 = new JPanel();
		panel_2.setBounds(400, 250, 420, 225);
		panel_2.setLayout(null);
		
		User current = jsonParserImp.getCurrentUser();
		String date = jsonParserImp.todayDatestr();
		double funds = current.expenseProfile.getTotalFunds();
		double fundsSpent = current.expenseProfile.getSpentFunds();
		
		JLabel lblExpenseProfile = new JLabel("Expense Profile");
		lblExpenseProfile.setBounds(153, 6, 106, 16);
		panel_2.add(lblExpenseProfile);
		
		JLabel lblDate = new JLabel("Date: " + date);
		lblDate.setBounds(40, 46, 150, 16);
		panel_2.add(lblDate);
		
		JLabel lblFundsPerMonth = new JLabel("Funds per month:$" + String.valueOf(funds));
		lblFundsPerMonth.setBounds(40, 77, 200, 16);
		panel_2.add(lblFundsPerMonth);
		
		JLabel lblFundsSpent = new JLabel("Funds spent:$" + String.valueOf(fundsSpent));
		lblFundsSpent.setBounds(241, 46, 150, 16);
		panel_2.add(lblFundsSpent);
		
		JLabel lblRemainingFunds = new JLabel("Remaining funds:$" + String.valueOf(funds - fundsSpent));
		lblRemainingFunds.setBounds(241, 77, 200, 16);
		panel_2.add(lblRemainingFunds);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(userP);
				panel_2.remove(btnClose);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnClose.setBounds(26, 156, 117, 29);
		panel_2.add(btnClose);
		
		return panel_2;
	}
	
	
	/**
	 * Create dietary panel
	 */
	public JPanel getDietaryPanel(){
		panel_3 = new JPanel();
		panel_3.setBounds(400, 250, 420, 225);
		panel_3.setLayout(null);
		
		User current = jsonParserImp.getCurrentUser();
		
		double totalCals = current.dietaryProfile.getdailyCalorieLimit();
		double calsConsumed = current.dietaryProfile.getCaloriesConsumed();
		
		JLabel lblDietaryPreferences = new JLabel("Dietary Preferences");
		lblDietaryPreferences.setBounds(92, 6, 128, 16);
		panel_3.add(lblDietaryPreferences);
		
		JLabel lblCaloriePreference_1 = new JLabel("Calorie Preference: " + String.valueOf(totalCals));
		lblCaloriePreference_1.setBounds(30, 34, 300, 16);
		panel_3.add(lblCaloriePreference_1);
		
		JLabel lblCaloriesConsumedTo = new JLabel("Calories consumed to date: " + String.valueOf(calsConsumed));
		lblCaloriesConsumedTo.setBounds(30, 63, 300, 16);
		panel_3.add(lblCaloriesConsumedTo);
		
		JLabel lblCaloriesLeft = new JLabel("Calories left: " + String.valueOf(totalCals - calsConsumed));
		lblCaloriesLeft.setBounds(30, 91, 300, 16);
		panel_3.add(lblCaloriesLeft);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(userP);
				panel_3.remove(btnClose);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnClose.setBounds(26, 156, 117, 29);
		panel_3.add(btnClose);
		
		return panel_3;
	}
	
	/**
	 * Create edit dietary profile
	 */
	public JPanel getEditDietPanel(){
		panel = new JPanel();
		panel.setBounds(400, 250, 259, 267);
		panel.setLayout(null);
				
		JLabel lblEditDietaryPreferences = new JLabel("Edit Preferences");
		lblEditDietaryPreferences.setBounds(48, 6, 149, 16);
		panel.add(lblEditDietaryPreferences);
		
		JLabel lblCaloriePreference = new JLabel("Calorie Preference");
		lblCaloriePreference.setBounds(17, 32, 130, 16);
		panel.add(lblCaloriePreference);
		
		JLabel lblday = new JLabel("/Day");
		lblday.setBounds(147, 53, 61, 16);
		panel.add(lblday);
		
		JLabel lblSpecialRestrictions = new JLabel("Special Restrictions");
		lblSpecialRestrictions.setBounds(17, 86, 130, 16);
		panel.add(lblSpecialRestrictions);
		
		cals = new JTextField();
		cals.setBounds(17, 48, 130, 26);
		panel.add(cals);
		cals.setColumns(10);
		
		rdbtnLowSodium = new JRadioButton("Low Sodium");
		rdbtnLowSodium.setBounds(17, 114, 141, 23);
		panel.add(rdbtnLowSodium);
		
		rdbtnLowSugar = new JRadioButton("Low Sugar");
		rdbtnLowSugar.setBounds(17, 145, 141, 23);
		panel.add(rdbtnLowSugar);

		
		JButton btnSave = new JButton("Save");
		SavePreferences SP = new SavePreferences();
		btnSave.addActionListener(SP);
		btnSave.setBounds(17, 232, 117, 29);
		panel.add(btnSave);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnNewButton_1.setBounds(136, 232, 117, 29);
		panel.add(btnNewButton_1);
		
		return panel;
	}
	
	
	class SavePreferences implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				savePreferences(cals.getText(), rdbtnLowSodium.isSelected(), rdbtnLowSugar.isSelected());
				
			} catch (IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			contentPane.remove(panel);
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		protected void savePreferences(String cal, boolean sodium, boolean sugar) throws FileNotFoundException, IOException, ParseException{
				String lowSodium = "false";
				String lowCalorie = "false";
				String lowSugar = "false";
				
				if(sodium == true)
					lowSodium = "true";
				if(sugar == true)
					lowSugar = "true";
				if(Integer.parseInt(cal) <= 1500)
					lowCalorie = "true";
				
				
				JSONObject mainjsonObj = new JSONObject();
				JSONArray prefArr = jsonParserImp.getRootValueArray(UserPreferencesFile, "Preferences");
				//assuming user already exists
				JSONArray newprefArr = jsonParserImp.changeUserPreferences( cal, lowSodium, lowCalorie, lowSugar,prefArr);
				jsonParserImp.getUserPreferences();
				mainjsonObj.put("Preferences" , newprefArr );
				jsonParserImp.writeJSONToUserFile(mainjsonObj, UserPreferencesFile);
				JOptionPane.showMessageDialog(null, "PREFERENCES SUCESSFULLY UPDATED!");
		}
		
	}
	
	
	/**
	 * Create cafe panel
	 */
	public JPanel getCafePanel(String name){
		panel_1 = new JPanel();
		panel_1.setBounds(400, 250, 173, 199);
		panel_1.setLayout(null);

		
		JLabel vendorMenu = new JLabel(name);
		vendorMenu.setBounds(26, 6, 117, 16);
		panel_1.add(vendorMenu);
		
		JButton btnOrderFood = new JButton("Order Food");
		btnOrderFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_4 = getOrderPanel(name);
				contentPane.remove(btnOrderFood);
				panel_1.remove(btnClose);
				contentPane.remove(panel_1);
				contentPane.add(panel_4);
			}
		});
		btnOrderFood.setBounds(6, 34, 161, 29);
		panel_1.add(btnOrderFood);
		
		JButton btnDietaryPreferences = new JButton("Dietary Preferences");
		btnDietaryPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel pan1 = jsonParserImp.DietaryProfBar();
				JPanel pan = jsonParserImp.DietaryProfLine();
				JFrame frame = new JFrame();
				frame.getContentPane().setLayout(new FlowLayout());
				frame.getContentPane().add(pan);
				frame.getContentPane().add(pan1);
				frame.pack();
				frame.setVisible(true);
				panel_3 = getDietaryPanel();
				contentPane.remove(btnDietaryPreferences);
				panel_1.remove(btnClose);
				contentPane.remove(panel_1);
				contentPane.add(panel_3);
			}
		});
		btnDietaryPreferences.setBounds(6, 75, 161, 29);
		panel_1.add(btnDietaryPreferences);
		
		JButton btnViewExpenses = new JButton("View Expenses");
		btnViewExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel pan1 = jsonParserImp.ExpenseProfBar();
				JPanel pan = jsonParserImp.ExpenseProfLine();
				JFrame frame = new JFrame();
				frame.getContentPane().setLayout(new FlowLayout());
				frame.getContentPane().add(pan);
				frame.getContentPane().add(pan1);
				frame.pack();
				frame.setVisible(true);
				panel_2 = getExpensePanel();
				contentPane.remove(btnViewExpenses);
				panel_1.remove(btnClose);
				contentPane.remove(panel_1);
				contentPane.add(panel_2);
			}
		});
		btnViewExpenses.setBounds(6, 115, 161, 29);
		panel_1.add(btnViewExpenses);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel_1);
				panel_1.remove(btnClose);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnClose.setBounds(26, 156, 117, 29);
		panel_1.add(btnClose);
		
		return panel_1;
	}
	
	
	public JPanel getOrderPanel(String name){
		
		panel_4 = new JPanel();
		panel_4.setBounds(400, 250, 408, 300);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Order Food");
		lblNewLabel_1.setBounds(167, 6, 100, 16);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(39, 39, 61, 16);
		panel_4.add(lblItem);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(146, 39, 61, 16);
		panel_4.add(lblPrice);
		
		JLabel lblCalories = new JLabel("Calories");
		lblCalories.setBounds(244, 39, 61, 16);
		panel_4.add(lblCalories);
		
		JLabel lblPurchase = new JLabel("Purchase");
		lblPurchase.setBounds(341, 39, 61, 16);
		panel_4.add(lblPurchase);
		
		
		
		if(name.equals("Benson Cafe")){
			FS = BC;
		}else{
			FS = VM;
		}
		
		Snack temp;

		for(int i=0; i<FS.numItems; i++){
			
		}
		if(FS.numItems > 0){
			temp = FS.getItem(0);
			
			JLabel lblItem_1 = new JLabel(temp.getName());
			lblItem_1.setBounds(39, 77, 61, 16);
			panel_4.add(lblItem_1);
		
			JLabel lblPrice_1 = new JLabel(String.valueOf(temp.getPrice()));
			lblPrice_1.setBounds(146, 77, 61, 16);
			panel_4.add(lblPrice_1);
		
			JLabel lblCals = new JLabel(String.valueOf(temp.getCalories()));
			lblCals.setBounds(244, 77, 61, 16);
			panel_4.add(lblCals);
		
			JButton btnNewButton = new JButton("Buy1");
			BuyFood BF = new BuyFood();
			btnNewButton.addActionListener(BF);
			btnNewButton.setBounds(341, 72, 61, 29);
			panel_4.add(btnNewButton);
		}
		
		if(FS.numItems > 1){
			temp = FS.getItem(1);
			
			JLabel lblItem_2 = new JLabel(temp.getName());
			lblItem_2.setBounds(39, 126, 61, 16);
			panel_4.add(lblItem_2);
			
			JLabel lblPrice_2 = new JLabel(String.valueOf(temp.getPrice()));
			lblPrice_2.setBounds(146, 126, 61, 16);
			panel_4.add(lblPrice_2);
			
			JLabel lblCals_1 = new JLabel(String.valueOf(temp.getCalories()));
			lblCals_1.setBounds(244, 126, 61, 16);
			panel_4.add(lblCals_1);
			
			JButton btnBuy = new JButton("Buy2");
			BuyFood BF = new BuyFood();
			btnBuy.addActionListener(BF);
			btnBuy.setBounds(341, 121, 61, 29);
			panel_4.add(btnBuy);
		}
		
		if(FS.numItems > 2){
			temp = FS.getItem(2);
			
			JLabel lblItem_3 = new JLabel(temp.getName());
			lblItem_3.setBounds(39, 172, 61, 16);
			panel_4.add(lblItem_3);
			
			JLabel lblPrice_3 = new JLabel(String.valueOf(temp.getPrice()));
			lblPrice_3.setBounds(146, 172, 61, 16);
			panel_4.add(lblPrice_3);
			
			JLabel lblCals_2 = new JLabel(String.valueOf(temp.getCalories()));
			lblCals_2.setBounds(244, 172, 61, 16);
			panel_4.add(lblCals_2);
			
			JButton btnBuy_1 = new JButton("Buy3");
			BuyFood BF = new BuyFood();
			btnBuy_1.addActionListener(BF);
			btnBuy_1.setBounds(341, 167, 61, 29);
			panel_4.add(btnBuy_1);
		}
		System.out.println("Made it");
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel_4);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnClose.setBounds(200, 195, 61, 29);
		panel_4.add(btnClose);
	
		return panel_4;
	}
	
	/**
	 * User purchases an item
	 */
	class BuyFood implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getActionCommand());
			
			try {
				Snack temp = null;
				if(e.getActionCommand().equals("Buy1")){
					temp = FS.getItem(0);
				}else if(e.getActionCommand().equals("Buy2")){
					temp = FS.getItem(1);
				}else if(e.getActionCommand().equals("Buy3")){
					temp = FS.getItem(2);
				}else{
					System.out.println("Error");
				}
				
				String itemCalval = String.valueOf(temp.getCalories());
				String itemPrice = String.valueOf(temp.getPrice());
				/**
				 * checks if the purchase transaction can be completed or not
				 * checks if there are sufficient funds
				 * asks the user whether to continue if the caloric value is exceeded
				 */
				if(jsonParserImp.validTrans(itemPrice,itemCalval)) {
					changeDietProfile( itemCalval);
					changeExpenseProfile( itemPrice);
					JOptionPane.showMessageDialog(null, "PURCHASE COMPLETE!");
				}
			}
			catch(Exception e1) {
				System.out.println("Exception from savepreferences" + e1.toString());
			}
		}
	/**
	 * change the Expense profile of the user in ExpenseProfile file
	 * @param loggedUser userName/Card Num of the user - 
	 * - to identify which user is logged currently
	 * @param itemPrice price of the item bought
	 */
		
		private void changeExpenseProfile(String itemPrice) {
				try {
					JSONObject mainjsonObj = new JSONObject();
					//assuming user already exists
					JSONArray newExpArr = jsonParserImp.getExpProfArr(itemPrice );
					mainjsonObj.put("ExpenseProfiles", newExpArr);
					jsonParserImp.writeJSONToUserFile(mainjsonObj, ExpenseProfileFile);
					System.out.println("changed expense val");
					} catch(Exception e) {
						System.out.println("Exception from change expense profile" + e.toString());
					}	
				
		}
		
		/**
		 * change the diet profile of the user in the DietaryProfile file
		 * @param loggedUser cardNum of the user who is currently logged in
		 * used to know the who is logged in and fetch the data accordingly
		 * @param itemCalval caloric value of the item bought
		 */
		@SuppressWarnings("unchecked")
		private void changeDietProfile(String itemCalval) {
			try {
				JSONObject mainjsonObj = new JSONObject();
				//JSONArray dietArr = jsonParserImp.getRootValueArray(DietaryProfileFile , "DietaryProfiles");
				//assuming user already exists
				JSONArray newDietArr = jsonParserImp.getDietProfArr( itemCalval );
				mainjsonObj.put("DietaryProfiles", newDietArr);
				jsonParserImp.writeJSONToUserFile(mainjsonObj, DietaryProfileFile);
				
				System.out.println("changed diet val");
				} catch(Exception e) {
					System.out.println("Exception from change diet profile" + e.toString());
				}				
		}
	}
	
	/**
	 * 
	 * Snack(String name, int price, int calories, int stock, boolean sodium, boolean cholesterol)
	 * User login class
	 */
	public void createBC(){
		//create and add snack items
		Snack sandwich = new Snack("Sandwich",8,500,5,false,true);
		Snack coffee = new Snack("Coffee",4,100,10,true,true);
		Snack bagel = new Snack("Bagel",3,200,5,true,false);
		
		BC.addItem(sandwich);
		BC.addItem(coffee);
		BC.addItem(bagel);
	}
	
	public void createVM(){
		//create and add snack items
		Snack cookie = new Snack("Cookie",4,80,10,true,false);
		Snack chips = new Snack("Chips",2,200,10,false,false);
		Snack apple = new Snack("Apple",1,50,10,true,true);
		
		VM.addItem(cookie);
		VM.addItem(chips);
		VM.addItem(apple);
	}
	
	class BarChart extends JPanel
	{
		private Map<String, String> bars = new HashMap<String, String>();
		
		public BarChart()
		{
			setPreferredSize(new Dimension(500,500));		
		}
		
		/**
		 * Add new bar to chart
		 * @param color color to display bar
		 * @param value size of bar
		 */
		public void addBar(String date, String value)
		{
			bars.put(date, value);	
			// cannot call paintComponent() or paint() directly
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			// determine longest bar
			int max = Integer.MIN_VALUE;
			for (String value : bars.values())
			{
				int temp = Integer.parseInt(value);
				max = Math.max(max, temp);
			}
			// paint bars

			int width = (getWidth() / bars.size()) - 100;
			int x = 1;
			int counter = 0;
			for (String values : bars.values())
			{
				int value = Integer.parseInt(bars.get(values));
				int height = (int)
	                            ((getHeight()-5) * ((double)value / max));
				if(counter%2 == 0)
					g.setColor(Color.yellow);
				else
					g.setColor(Color.blue);
				
				
				g.fillRect(x, getHeight() - height, width, height);
				g.setColor(Color.black);
				g.drawRect(x, getHeight() - height, width, height);
				x += (width + 2);
			}
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(bars.size() * 10 + 2, 50);
		}
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapView frame = new MapView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
