import java.io.*;
import java.util.Arrays;

public class EC {
    public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "/Users/Alex/Documents/Alex_School_Work/SCU_Senior/-Spring/OOP/EC_ahoff/src/text.txt";

        // This will reference one line at a time
        String line = null;
        
        String breaks = "[ ,  ]+";
        
        String[] categories = new String[10];
        double[] total = new double[10];
        int[] frequency = new int[10];
        int cats = 0;
        double temp;
        double ave;
        boolean flag = false;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] tokens = line.split(breaks);
                //System.out.println(tokens[0]);
                
                tokens[1] = tokens[1].trim();
                temp = Double.parseDouble(tokens[1]);             
                //System.out.println(tokens[1]);
               
                if(tokens.length != 2){
                	//System.out.println("Entertainment");
                }else if(cats == 0){
                	categories[cats] = tokens[0];
            		total[cats] = temp;
            		frequency[cats] = 1;
            		cats=1;
                }else{
                	flag = false;
	                for(int i=0; i < cats; i++){
	                	if(categories[i].equals(tokens[0])){
	                		total[i] += temp;
	                		frequency[i]++;
	                		flag = true;
	                	}	
	                }
	                if(flag == false){
                		categories[cats] = tokens[0];
                		total[cats] = temp;
                		frequency[cats] = 1;
                		cats++;
	                }
                }
                //System.out.println(cats);
            }
            //System.out.println(Arrays.toString(categories));
            //System.out.println(categories[0]);
            System.out.println("Category\t" + "Total\t" + "Average");
            for(int i=0; i < cats; i++){
            	temp = total[i];
            	ave = frequency[i];
            	ave = temp/ave;
            	
            	if(categories[i].length() < 6){
            		System.out.println(categories[i] + "\t\t" + total[i] + "\t" + ave);
            	}else
            		System.out.println(categories[i] + "\t" + total[i] + "\t" + ave);
            }
                 

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}
