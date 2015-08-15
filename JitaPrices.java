package eveAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JitaPrices {
	public static Map<Integer, Float> Prices() throws Exception {
		ArrayList<Integer> itemID = new ArrayList<Integer>();
    	ArrayList<String> typeName = new ArrayList<String>();
    	ArrayList<Float> prices = new ArrayList<Float>();
    	Map<Integer, String> allItems = new HashMap<Integer, String>();
    	Map<Integer, Float> jitaBuy = new HashMap<Integer, Float>();
    	Map<Integer, String> test = new HashMap<Integer, String>();
    	
    		Scanner sc=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\typeID2.txt"));
    	    while (sc.hasNextLine()){
    	        itemID.add(sc.nextInt());
    	    }
    	    
    	    System.out.println(itemID);
    	    sc.close();
    	    
    		Scanner sc2=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\typeName2.txt"));
    	    while (sc2.hasNextLine()){
    	        typeName.add(sc2.nextLine());
    	        
    	    }
    	    
    	    System.out.println(typeName);
    	    sc2.close();
    	    System.out.println(typeName.size());
    	    System.out.println(itemID.size());
    	    for (int i = 0; i < itemID.size(); i++) {
    			allItems.put(itemID.get(i), typeName.get(i));
    	   }
        	
    	    
        	int count = 0;
        	
        	PrintStream typeIDStream= new PrintStream(new File("C:\\Users\\Azazel\\Documents\\relevantID.txt"));
    	    PrintStream priceStream = new PrintStream(new File("C:\\Users\\Azazel\\Documents\\priceStream.txt"));
        	for (int i = 0; i < itemID.size()-1; i++) {
    	    
            String buyRegion = CreateTextFiles.buyRegionURL();
    	    String urls = "http://api.eve-marketdata.com/api/item_prices2.json?char_name=lunariussolarian&type_ids="+itemID.get(i)+buyRegion;
    	    
    	    float price = Decode.getPrice(urls);
    	    
    	    if(price!= 0)
    	    {
    	    	jitaBuy.put(itemID.get(i), price);
    	    	typeIDStream.println(itemID.get(i));
    	    	priceStream.println(price);
    	    }
    	    if(price == 0)
    	    {
    	    	count+=1;
    	    	System.out.println(count);
    	    }
    	    
    	    System.out.println(jitaBuy);
    	    System.out.println(jitaBuy.size());
    	    System.out.println(count);
    	    }
    	   
		
    	    
    	    return jitaBuy;
	}
}
