package snack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONParserImp {

	String loggedUser;
	DietaryProfile dietaryProfile = null;
	ExpenseProfile expenseProfile = null;
	public User currentUser = null;
	int flag;

	@SuppressWarnings("unchecked")
	/**
	 * create a JSON object with the user credentials 
	 * @param userName 
	 * @param pwd
	 * @return
	 */
	public JSONObject createUserObject(String userName, String pwd) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("UserName", userName);
		jsonObject.put("Password", pwd);
		return jsonObject;
	}
	
	public boolean isValidUser(String userName, String pwd) throws FileNotFoundException, IOException, ParseException {
		JSONArray jsonarr = getRootValueArray("/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/User.txt", "Users");
		Iterator<JSONObject> it = jsonarr.iterator();
		while(it.hasNext()) {
			JSONObject jsonobj = it.next();
			String user = (String)jsonobj.get("UserName");
			String password = (String) jsonobj.get("Password");
			if( user.equals(userName) && password.equals(pwd) ) 
				return true;
			//else
				//display a label to reenter the credentials
			System.out.println("invalid user");

		}
		return false;
	}


	/**
	 * read from the file and return the JSONArray consisting of existing users
	 * 
	 * @return JSONArray array of users
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONArray getRootValueArray(String fileName, String key)
			throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) object;
		JSONArray usersArray = (JSONArray) jsonObject.get(key);
		// System.out.println(usersArray);
		return usersArray;

	}

	/**
	 * write the user to the file
	 * 
	 * @param obj
	 *            JSON object that should be written
	 * @param filename
	 *            name of the file to write
	 */

	public boolean userExists(String userName, String fileName, String key) {
		JSONObject jsonChildObject;

		try {
			JSONArray jsonArray = getRootValueArray(fileName, key);
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				jsonChildObject = iterator.next();
				String user = (String) jsonChildObject.get("UserName");
				// System.out.println(user + "");
				if (user.equals(userName))
					return true;

				;
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}
	/**
	 * update the preferences file when the preferences are edited
	 * @param cal dailyCalorieLimit
	 * @param lowSodium set to "true/false"
	 * @param lowCalorie set to "true/false"
	 * @param lowSugar set to "true/false"
	 * @param prefArr preferences JSONArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONArray changeUserPreferences(String cal, String lowSodium,
			String lowCalorie, String lowSugar, JSONArray prefArr) {
		JSONObject jsonObj;

		Iterator<JSONObject> iterator = prefArr.iterator();
		while (iterator.hasNext()) {
			jsonObj = iterator.next();
			String user = (String) jsonObj.get("UserName");
			if (user.equals(loggedUser)) {
				iterator.remove();
			}
		}
		JSONObject newPrefObj = new JSONObject();
		newPrefObj.put("UserName", loggedUser);
		newPrefObj.put("CalorieLimit", cal);
		newPrefObj.put("LowSodium", lowSodium);
		newPrefObj.put("LowCalorie", lowCalorie);
		newPrefObj.put("LowSugar", lowSugar);

		prefArr.add(newPrefObj);
		System.out.println("changed preferences");
		return prefArr;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public void getUserPreferences() throws FileNotFoundException, IOException,
			ParseException {
		String lowSodium, lowSugar, lowCalorie;
		int dailyCalorieLimit;
		JSONObject jsonObject;

		JSONArray jsonArr = getRootValueArray(
				"/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/Preferences.txt",
				"Preferences");
		Iterator<JSONObject> iterator = jsonArr.iterator();
		while (iterator.hasNext()) {
			jsonObject = iterator.next();
			String user = (String) jsonObject.get("UserName");
			if (user.equals(loggedUser)) {
				lowSodium = (String) jsonObject.get("LowSodium");
				lowSugar = (String) jsonObject.get("LowSugar");
				lowCalorie = (String) jsonObject.get("LowCalorie");
				dailyCalorieLimit = Integer.parseInt((String) jsonObject
						.get("CalorieLimit"));

				dietaryProfile = new DietaryProfile(dailyCalorieLimit);
				dietaryProfile.setOtherPreferences(lowSodium, lowCalorie,
						lowSugar);
				// currentUser.setDietaryProfile(dietaryProfile);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public JSONArray getDietProfArr(String calVal)
			throws FileNotFoundException, IOException, ParseException {
		flag = 0;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonChildObj = null;
		JSONObject calConsObj = null;
		JSONObject reqObj = null;
		String keystr = null;
		JSONArray newarr= null;
		jsonArray = getRootValueArray(
				"/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/DietaryProfile.txt",
				"DietaryProfiles");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			jsonChildObj = (JSONObject) iterator.next();
			String user = (String) jsonChildObj.get("UserName");
			if (user.equals(loggedUser)) {
				calConsObj = (JSONObject) jsonChildObj.get("CaloriesConsumed");
				for (Object key : calConsObj.keySet()) {
					keystr = (String) key;
					if (keystr.equals(todayDatestr())) {
						newarr = (JSONArray) calConsObj.get(keystr);
						newarr.add(calVal);
						calConsObj.replace(keystr, newarr);
						jsonChildObj.replace("caloriesConsumed",
							calConsObj);
						reqObj = jsonChildObj;
						iterator.remove();
						}
					}
				}
			}
		if(reqObj != null)
			jsonArray.add(reqObj);
		
		double calCons = calORFundsSpent(calConsObj);
		if (dietaryProfile == null)
			System.out.println("no preferences entered for the user");
		else
			dietaryProfile.setCaloriesConsumed(calCons);
			double d = this.toList(newarr);
			HashMap<String, Object> map = dietaryProfile.getdayCaloriesConsumed();
			map.replace(keystr, d);
			dietaryProfile.setdayCaloriesConsumed(map);
		currentUser.setDietaryProfile(dietaryProfile);

		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getExpProfArr(String itemPrice)
			throws FileNotFoundException, IOException, ParseException {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonChildObj = null;
		JSONObject fundsSpentObj = null;
		JSONObject reqObj = null;
		String keystr;
		String totalFunds = null;
		JSONArray newarr;
		jsonArray = getRootValueArray("/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/ExpenseProfile.txt",
				"ExpenseProfiles");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			jsonChildObj = (JSONObject) iterator.next();
			totalFunds = (String) jsonChildObj.get("Funds");
			String user = (String) jsonChildObj.get("UserName");
			if (user.equals(loggedUser)) {
				fundsSpentObj = (JSONObject) jsonChildObj.get("FundsSpent");
				
					for (Object key : fundsSpentObj.keySet()) {
						keystr = (String) key;
						if (keystr.equals(todayDatestr())) {
							newarr = (JSONArray) fundsSpentObj.get(keystr);
							newarr.add(itemPrice);
							fundsSpentObj.replace(keystr, newarr);
							jsonChildObj.replace("FundsSpent", fundsSpentObj);
							reqObj = jsonChildObj;
							iterator.remove();

						}
					}
				
			}
		}
		jsonArray.add(reqObj);

		currentUser.setExpenseProfile(expenseProfile);
		return jsonArray;
	}
	/**
	 * given json objects with json array as value
	 * computes the sum of elements in all the array
	 * used to calculate the total calories consumed or funds spent
	 * @param jsonObj
	 * @return
	 */

	private double calORFundsSpent(JSONObject jsonObj) {
		double fundsSpent = 0;
		for (Object val : jsonObj.values()) {
			JSONArray jsonArr = (JSONArray) val;
			for (Object obj : jsonArr) {
				Double i = Double.parseDouble((String) obj);
				fundsSpent += i;
			}
		}
		System.out.println("after purchase calories consumed/ funds spent : "
				+ fundsSpent);
		return fundsSpent;
	}

	public void writeJSONToUserFile(JSONObject obj, String filename) {
		try {
			File file = new File(filename);
			// if file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(obj.toJSONString());
			bw.flush();
			bw.close();
			// System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * computes today date
	 * @return todays date
	 */
	String todayDatestr() {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		String curDate = dateFormat.format(date);
		System.out.println(dateFormat.format(date));

		return curDate;
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		System.out.println("Set logged user");
		this.loggedUser = loggedUser;
	}

	public void populateUserObj(String name, String userName, String pwd)
			throws FileNotFoundException, IOException, ParseException {
		// the following methods are called as soon as a user logins
		//to fetch the whole profile of the user from the files
		this.getUserPreferences();
		dietaryProfile.setdayCaloriesConsumed(this.getUserDietaryProfile());
		this.getUserExpenseProfile();
		currentUser = new User(name, userName, pwd, expenseProfile,
				dietaryProfile);
		
	}
	/**
	 * fetches users expense profile
	 * fundsSpentMap has the daily funds spent with date as key and
	 * funds spent as value this can help you in displaying the graph
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	HashMap<String, Object> getUserExpenseProfile() throws FileNotFoundException,
			IOException, ParseException {
		JSONArray jsonArray = new JSONArray();
		HashMap<String, Object> fundsSpentMap = new HashMap<String, Object>();
		JSONObject jsonChildObj = null;
		JSONObject fundsSpentObj = null;
		String totalFunds = null;
		JSONArray newarr;
		jsonArray = getRootValueArray("/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/ExpenseProfile.txt",
				"ExpenseProfiles");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			jsonChildObj = (JSONObject) iterator.next();
			String user = (String) jsonChildObj.get("UserName");
			if (user.equals(loggedUser)) {
				totalFunds = (String) jsonChildObj.get("Funds");
				expenseProfile = new ExpenseProfile(
						Double.parseDouble(totalFunds));
				fundsSpentObj = (JSONObject) jsonChildObj.get("FundsSpent");
				double fundsSpent = calORFundsSpent(fundsSpentObj);
				expenseProfile.setSpentFunds(fundsSpent);
				jsonToMap(fundsSpentObj, fundsSpentMap);

			}
		}
		return fundsSpentMap;
	}
	
	/**
	 * fetches users dietary profile
	 * DietMap has the daily funds spent with date as key and
	 * calories consumed as value this can help you in displaying the graph
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private HashMap<String, Object> getUserDietaryProfile()
			throws FileNotFoundException, IOException, ParseException {
		JSONArray jsonArray = new JSONArray();
		HashMap<String, Object> DietMap = new HashMap<String, Object>();
		JSONObject jsonChildObj = null;
		JSONObject calConsObj = null;
		String totalFunds = null;
		JSONArray newarr;
		jsonArray = getRootValueArray("/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/275Project/src/snack/DietaryProfile.txt",
				"DietaryProfiles");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			jsonChildObj = (JSONObject) iterator.next();
			String user = (String) jsonChildObj.get("UserName");
			if (user.equals(loggedUser)) {
				calConsObj = (JSONObject) jsonChildObj.get("CaloriesConsumed");
				double calCons = calORFundsSpent(calConsObj);
				dietaryProfile.setCaloriesConsumed(calCons);
				jsonToMap(calConsObj, DietMap);

			}
		}
		return DietMap;

	}
 /**
  * converts jsonObject in to map
  * @param jsonObj jsonObject
  * @param hashMap HashMap
  */
	private void jsonToMap(JSONObject jsonObj,
			HashMap<String, Object> hashMap) {
		double dayFundsSpent = 0;
		for (Object obj : jsonObj.keySet()) {
			String key = (String) obj;
			Object value = jsonObj.get(key);
			if (value instanceof JSONArray) {
				dayFundsSpent = toList((JSONArray) value);
			}
			hashMap.put(key, dayFundsSpent);
		}

	}
	/**
	 * computes sum of elements in a JSONArray
	 * @param value a JSONArray
	 * @return sum of elements in a JSONArray
	 */
	private double toList(JSONArray value) {
		double count = 0;
		Iterator<String> it = value.iterator();
		while (it.hasNext()) {
			String val = it.next();
			Double dVal = Double.parseDouble(val);
			count += dVal;

		}
		return count;
	}
 /**
  * checks  if the purchase transaction has to be proceeded
  * @param ItemPrice price of item to be purchased
  * @param ItemCalVal caloric value of the item
  * @return
  */
	public boolean validTrans(String ItemPrice, String ItemCalVal) {
		double fundsSpent = expenseProfile.getSpentFunds();
		double itemVal = Double.parseDouble(ItemPrice);
		double itemCalVal = Double.parseDouble(ItemCalVal);
		if ((fundsSpent + itemVal) <= expenseProfile.getTotalFunds()) {	
			expenseProfile.setSpentFunds((fundsSpent + itemVal));
			HashMap<String , Object > map = dietaryProfile.getdayCaloriesConsumed();
			Object obj = map.get(todayDatestr());
			Double dayConsumption = (Double) obj;
			
			if (dayConsumption + itemCalVal > dietaryProfile
					.getdailyCalorieLimit()) {

				String message = "Your calorie consumption has exceeded the dailt limit"
						+ "Do you still want to purchase?";
				String title = "Really Purchase?";
				// display the JOptionPane showConfirmDialog
				int reply = JOptionPane.showConfirmDialog(null,
						message, title, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {	
							return true;
				} else return false;
			} else return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Insufficient Balance");
			return false;
		}
			
	}
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public JPanel ExpenseProfLine() {
		Map<String, Object> map;
		 JPanel jpanel = null;
		try {
			map = this.getUserExpenseProfile();
			List<Double> list = new ArrayList<Double>();
			List<String>  dateList = new ArrayList<String>();
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				 String key = entry.getKey();
		         Double value = (Double) entry.getValue();
		         dateList.add(key);
		         list.add(value);
		         jpanel = new DrawGraph(list,dateList, 1300);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return jpanel;
	}
	
	
	public JPanel ExpenseProfBar() {
		Map<String, Object> map;
		 JPanel jpanel = null;
		try {
			map = this.getUserExpenseProfile();
			List<Double> list = new ArrayList<Double>();
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				 String key = entry.getKey();
		         Double value = (Double) entry.getValue();
		         list.add(value);
		         jpanel = new DrawBar(list, 1300);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return jpanel;
	}
	
	public JPanel DietaryProfLine() {
		Map<String, Object> map;
		 JPanel jpanel = null;
		try {
			map = this.getUserDietaryProfile();
			List<Double> list = new ArrayList<Double>();
			List<String>  dateList = new ArrayList<String>();
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				 String key = entry.getKey();
		         Double value = (Double) entry.getValue();
		         dateList.add(key);
		         list.add(value);
		         jpanel = new DrawGraph(list,dateList, 1300);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return jpanel;
	}
	
	public JPanel DietaryProfBar() {
		Map<String, Object> map;
		 JPanel jpanel = null;
		try {
			map = this.getUserDietaryProfile();
			List<Double> list = new ArrayList<Double>();
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				 String key = entry.getKey();
		         Double value = (Double) entry.getValue();
		         list.add(value);
		         jpanel = new DrawBar(list, 1300);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return jpanel;
		
	}


}