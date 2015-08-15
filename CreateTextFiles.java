package eveAPI;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateTextFiles {
	public static Map<Integer, String> createAllItems() throws Exception{
		ArrayList<Integer> itemID = new ArrayList<Integer>();
		ArrayList<String> typeName = new ArrayList<String>();
		
		Map<Integer, String> allItems = new HashMap<Integer, String>();

		
		Scanner sc3=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\typeID2.txt"));
	    while (sc3.hasNextLine()){
	        itemID.add(sc3.nextInt());
	    }
	    
	    System.out.println(itemID);
	    sc3.close();
	    
		Scanner sc4=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\typeName2.txt"));
	    while (sc4.hasNextLine()){
	        typeName.add(sc4.nextLine());
	    }
	    
	    System.out.println(typeName);
	    sc4.close();
	    System.out.println(typeName.size());
	    System.out.println(itemID.size());
	    for (int i = 0; i < itemID.size(); i++) {
			allItems.put(itemID.get(i), typeName.get(i));
	   }
	    return allItems;
		
	}
	
	public static Map<Integer, String> createsolarSystems() throws Exception{
		ArrayList<Integer> solarID = new ArrayList<Integer>();
		ArrayList<String> solarName = new ArrayList<String>();
		
		Map<Integer, String> solarSystem = new HashMap<Integer, String>();

		
		Scanner sc10=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\solarSystemID.txt"));
	    while (sc10.hasNextLine()){
	        solarID.add(sc10.nextInt());
	    }
	    
	    System.out.println(solarID);
	    sc10.close();
	    
		Scanner sc11=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\solarSystemName.txt"));
	    while (sc11.hasNextLine()){
	        solarName.add(sc11.nextLine());
	    }
	    
	    System.out.println(solarName);
	    
	    
	    
	   
	   for (int i = 0; i < solarID.size(); i++) {
			solarSystem.put(solarID.get(i), solarName.get(i));
	   }
	    sc11.close();
	    return solarSystem;
		
	}
	
	public static Map<Integer, String> createRegions() throws Exception{
		ArrayList<Integer> regionID = new ArrayList<Integer>();
		ArrayList<String> regionName = new ArrayList<String>();
		
		Map<Integer, String> regions = new HashMap<Integer, String>();

		
		Scanner sc14=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\RegionID.txt"));
	    while (sc14.hasNextLine()){
	        regionID.add(sc14.nextInt());
	    }
	    
	    
	    sc14.close();
	    
		Scanner sc13=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\RegionName.txt"));
	    while (sc13.hasNextLine()){
	        regionName.add(sc13.nextLine());
	    }
	    
	    
	   
	   for (int i = 0; i < regionID.size(); i++) {
			regions.put(regionID.get(i), regionName.get(i));
	   }
	   sc13.close();
	    return regions;
		
	}
	
	
	
	public static Map<Integer, Float> createAllVolumes() throws Exception{
		 ArrayList<Integer> allItemID = new ArrayList<Integer>();
		    ArrayList<Float> volumes = new ArrayList<Float>();
		    Map<Integer, Float> allVolumes = new HashMap<Integer, Float>();
		 System.out.println("true");
		    Scanner sc6=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\typeIDTotal.txt"));
		    while (sc6.hasNextInt()){
		        allItemID.add(sc6.nextInt());
		    }
		    
		    sc6.close();
		   
		    Scanner sc7=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\volumeID.txt"));
		    while (sc7.hasNextFloat()){
		        volumes.add(sc7.nextFloat());
		    }
		    
		    sc7.close();
		   
		   for (int i = 0; i < volumes.size(); i++) {
			allVolumes.put(allItemID.get(i), volumes.get(i));
		   } 
		   return allVolumes;
	}
	public static Map<Integer, Float> createjitaBuyList() throws Exception{
		    ArrayList<Float> jitabuyPrices = new ArrayList<Float>();
			ArrayList<Integer> jitaItemID = new ArrayList<Integer>();
			Map<Integer, Float> jitaBuyList = new HashMap<Integer, Float>();
			
			Scanner sc=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\relevantID.txt"));
		    while (sc.hasNextInt()){
		        jitaItemID.add(sc.nextInt());
		    }
		    
		   
		    sc.close();
		    
			Scanner sc2=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\priceStream.txt"));
		    while (sc2.hasNextFloat()){
		        jitabuyPrices.add(sc2.nextFloat());
		    }
		    
		    sc2.close();
		    System.out.println(jitabuyPrices);
		    
		    for (int i = 0; i < jitaItemID.size(); i++) {
		    	jitaBuyList.put(jitaItemID.get(i), jitabuyPrices.get(i));
			}
		    System.out.println(jitaBuyList);
		    
		    return jitaBuyList;
		    
		    }
	public static ArrayList<Integer> createIDList() throws Exception{
    	ArrayList<Integer> jitaItemID = new ArrayList<Integer>();
    	Scanner sc=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\relevantID.txt"));
	    while (sc.hasNextInt()){
	        jitaItemID.add(sc.nextInt());
	    }
	    sc.close();
	    return jitaItemID;
		
    }
	
	public static String buyRegionURL() throws Exception{
		String url = "&region_ids=10000002&buysell=b";
		return url;
	}
	public static String sellRegionURL() throws Exception{
		String url3 = "http://api.eve-marketdata.com/api/item_orders2.json?char_name=SolariusLunarian&buysell=s&region_ids=10000012,10000010,10000055,10000035,10000023,10000057,10000011,10000029,10000003,10000046,10000045,10000051,10000022,10000041,10000010,10000015,10000054,10000047,11000031,10000008&type_ids=";
		return url3;
	}

	
	
}
	

