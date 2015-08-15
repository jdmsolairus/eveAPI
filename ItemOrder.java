package eveAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.omg.CORBA.portable.ValueInputStream;

public class ItemOrder implements Comparable<ItemOrder>{
	int typeID;
	int orderID;
	int regionID;
	int stationID;
	int solarSystemID;
	String typeName;
	String solarSystemName;
	String regionName;
	int volRemaining;
	float typeVolume;
	float price;
	float profitTotal;
	float profitperVolume;
	
	public static void getDestinations(ArrayList<ItemOrder> allOrders, Map<Integer, String> allRegions, Map<Integer, String> allStations) throws FileNotFoundException, InterruptedException{
		PrintStream tradeDestination= new PrintStream(new File("C:\\Users\\Azazel\\Documents\\tradeDestination.txt"));
		ArrayList<ItemOrder> remainingOrders = new ArrayList<ItemOrder>();
		ArrayList<String> regions = new ArrayList<String>();
		ArrayList<String> regionsList = new ArrayList<String>();
		ArrayList<String> stations = new ArrayList<String>();
		Map<String, Integer> regionScore = new HashMap<String, Integer>();
		Map<String, Integer> stationScore = new HashMap<String, Integer>();
		Map<Integer, String> regionFind = new HashMap<Integer, String>();
		
		
		Scanner sc15=new Scanner(new FileReader("C:\\Users\\Azazel\\Documents\\RegionName.txt"));
	    while (sc15.hasNextLine()){
	        regionsList.add(sc15.nextLine());
	    }
		
		sc15.close();
		System.out.println(regionsList.size());
		Thread.sleep(1000);
		// create the text files that refer to regions and stations, iterate through those maps and add a value if it comes up in this smaller aray
		for (int i = 0; i < regionsList.size(); i++) {
			ArrayList<ItemOrder> regionOrders = new ArrayList<ItemOrder>();
			
			for (int j = 0; j < allOrders.size(); j++) {
				System.out.println(regionsList.get(i));
				System.out.println(allOrders.get(j).getRegionName());
				if (regionsList.get(i).equals(allOrders.get(j).getRegionName())) {
					System.out.println(true);
					regionOrders.add(allOrders.get(j));
				}
			}
			if (regionOrders.size()>0) {
				tradeDestination.println("best deals in region: "+regionsList.get(i));
			}
			System.out.println(regionOrders.size());
			Thread.sleep(100);
			for (int k = 0; k < regionOrders.size(); k++) {
				if (k<11) {
					tradeDestination.println(regionOrders.get(k).toString());	
				}
			}
			if (regionOrders.size()>0) {
				tradeDestination.println();
			}
		}
		
		
		
		for (int i = 0; i < allRegions.size(); i++) {
			regions.add(allRegions.get(i));
		}
		for (int i = 0; i < allStations.size(); i++) {
			stations.add(allStations.get(i));
		}
		
		
		int bestRegionIndex;
		int bestRegionIndexTwo;
		String bestRegion;
		
		String bestStation;
		//narrow down the orders to only the very best opportunities
		if(allOrders.size()>10){
			for (int i = 0; i < 10; i++) {
				remainingOrders.add(allOrders.get(i));
			}
		}
		else if (allOrders.size()<=10) {
			remainingOrders = allOrders;
		}
		
		
		for (int i = 0; i < allOrders.size(); i++) 
		{
			int bestRegionCounter = 0;
			for (int j = 0; j < allOrders.size()-1; j++) {
				if (allOrders.get(i).getRegionName() == allOrders.get(j).getRegionName()) {
					bestRegionCounter++;
				}
			}
			regionScore.put(allOrders.get(i).getRegionName(), bestRegionCounter);
		}
		
	tradeDestination.println("best items in all regions: "+regionScore);
	
		//bestRegion = best.getKey();

		for (int i = 0; i < remainingOrders.size(); i++) {
			tradeDestination.println(remainingOrders.get(i).toString());
		}
		
		
		/*int highestRegion = 0;
		String highestRegionKey = null;
		 for (String Key: regionScore.keySet()) {
			 if(regionScore.get(Key)>highestRegion)
			 {
				 highestRegionKey = Key;
				 highestRegion = regionScore.get(Key);
			 }
			  
			   System.out.println(highestRegionKey+""+highestRegion);
			};*/
			tradeDestination.println();
			
			tradeDestination.println();

		
		tradeDestination.close();
	
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getRegionID() {
		return regionID;
	}
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	public int getStationID() {
		return stationID;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public int getSolarSystemID() {
		return solarSystemID;
	}
	public void setSolarSystemID(int solarSystemID) {
		this.solarSystemID = solarSystemID;
	}
	public int getVolRemaining() {
		return volRemaining;
	}
	public void setVolRemaining(int volRemaining) {
		this.volRemaining = volRemaining;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getProfitTotal() {
		return profitTotal;
	}
	public void setProfitTotal(float profitTotal) {
		this.profitTotal = profitTotal;
	}
	public float getProfitperVolume() {
		return profitperVolume;
	}
	public void setProfitperVolume(float profitperVolume) {
		this.profitperVolume = profitperVolume;
	}
	public Float getTypeVolume() {
		return typeVolume;
	}
	
	@Override
	public int compareTo(ItemOrder other) {
		return Float.compare(this.profitTotal, other.profitTotal);
	}
	public void setTypeVolume(float f) {
		this.typeVolume = f;
		
	}
	public String toString(){
		return ("buy: "+this.getTypeName()+" at sell price: "+this.getPrice()+" in system:  "+this.getSolarSystemName()+" region: "+this.getRegionName()+" for a total profit of: "+this.getProfitTotal()+" with cargo size of: "+(this.getTypeVolume())+" this order has a profit/volume of: "+this.getProfitperVolume());
	}
	public String getSolarSystemName() {
		return solarSystemName;
	}
	public void setSolarSystemName(String solarSystemName) {
		this.solarSystemName = solarSystemName;
	}
	
	}


	

