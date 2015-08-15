package eveAPI;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class URLConnectionReader {
    public static void main(String[] args) throws Exception {
    PrintStream goodBuys= new PrintStream(new File("C:\\Users\\Azazel\\Documents\\goodBuys.txt"));
    double profitMargin = 1.2;
    double worthwhileBuy = 5000000;
    double maxSize = 2000;
    // add volumes to the equation
   
    Map<Integer, Float> allVolumes = new HashMap<Integer, Float>();  
	ArrayList<Integer> jitaItemID = new ArrayList<Integer>();
	Map<Integer, Float> jitaBuyList = new HashMap<Integer, Float>();
	Map<Integer, String> allItems = new HashMap<Integer, String>();
	Map<Integer, String> solarSystems = new HashMap<Integer, String>();
	Map<Integer, String> Regions = new HashMap<Integer, String>();
	allVolumes = CreateTextFiles.createAllVolumes();
	allItems = CreateTextFiles.createAllItems();
	jitaBuyList = CreateTextFiles.createjitaBuyList();
	jitaItemID = CreateTextFiles.createIDList();	
	solarSystems = CreateTextFiles.createsolarSystems();
	Regions = CreateTextFiles.createRegions();
	  
	JitaPrices.Prices();
	ArrayList<ItemOrder> sellOrders= new ArrayList<>();
	    for (int i = 0; i < jitaItemID.size(); i++) {
			
		String sellUrl = CreateTextFiles.sellRegionURL();
	    String url2 = sellUrl+jitaItemID.get(i);
	    String values = GetRequest.sendGet(url2);
        
        JSONObject jsonObject = new JSONObject();
        Object obj=JSONValue.parse(values);
        JSONArray array = (JSONArray)obj;
	    //System.out.println(array.get(0));
	    
	    JSONObject jsonObject2 = (JSONObject)array.get(0);
	   // System.out.println(jsonObject2.get("emd"));
	    Object obj2 = JSONValue.parse("["+jsonObject2.get("emd").toString()+"]");
	    JSONArray array2 = (JSONArray)obj2;
	    System.out.println(array2.get(0));
	    
	    JSONObject jsonObject3 = (JSONObject)array2.get(0);
	    System.out.println(jsonObject3.get("result"));
	    Object obj3 = JSONValue.parse(jsonObject3.get("result").toString());
	    JSONArray array3 = (JSONArray)obj3;
	    System.out.println(array3.size());
	    int count = 0;
	    float profit = 0;
	    ArrayList<Float> profitTotal = new ArrayList<Float>();
	    for (int j = 0; j < array3.size(); j++) {
	    	System.out.println(allVolumes.get(jitaItemID.get(i)));
	    	System.out.println(jitaBuyList.get(jitaItemID.get(i)));
	       JSONObject jsonObject4 = (JSONObject)array3.get(j);
	 	   System.out.println(jsonObject4.get("row"));
	 	   Object obj4 = JSONValue.parse("["+jsonObject4.get("row").toString()+"]");
	 	   JSONArray array4 = (JSONArray)obj4;
	 	   JSONObject jsonObject5 = (JSONObject)array4.get(0);
	 	   //System.out.println(jsonObject5.get("price"));
	 	   if(jitaBuyList.get(jitaItemID.get(i))>Float.parseFloat((String) jsonObject5.get("price")) 
	 			   && jitaBuyList.get(jitaItemID.get(i))/Float.parseFloat((String) jsonObject5.get("price"))>profitMargin
	 			   &&((jitaBuyList.get(jitaItemID.get(i))-Float.parseFloat((String)jsonObject5.get("price"))))*Float.parseFloat((String)jsonObject5.get("volRemaining"))>worthwhileBuy
	 			   && allVolumes.get(jitaItemID.get(i))*Float.parseFloat((String) jsonObject5.get("volRemaining"))<maxSize){
	 		  count+=1;
	 		  //System.out.println("sell price: "+jsonObject5.get("price")+"is lower than: "+jitaBuyList.get(jitaItemID.get(i))+"buy price");
	 		 //create the object
	 		ItemOrder sellOrder = new ItemOrder();
	 		sellOrder.setTypeID(Integer.parseInt((String) jsonObject5.get("typeID")));
	 		sellOrder.setSolarSystemID(Integer.parseInt((String) jsonObject5.get("solarsystemID")));
	 		sellOrder.setRegionID(Integer.parseInt((String) jsonObject5.get("regionID")));
	 		sellOrder.setPrice(Float.parseFloat((String) jsonObject5.get("price")));
	 		sellOrder.setVolRemaining(Integer.parseInt((String) jsonObject5.get("volRemaining")));
	 		sellOrder.setTypeVolume(allVolumes.get(jitaItemID.get(i))*Float.parseFloat((String) jsonObject5.get("volRemaining")));
	 		sellOrder.setProfitTotal(((jitaBuyList.get(jitaItemID.get(i))-Float.parseFloat((String)jsonObject5.get("price"))))*Float.parseFloat((String) jsonObject5.get("volRemaining"))); 
	 		sellOrder.setProfitperVolume((sellOrder.getVolRemaining()*sellOrder.getPrice())/sellOrder.getTypeVolume());
	 		sellOrder.setTypeName(allItems.get(Integer.parseInt((String) jsonObject5.get("typeID"))));
	 		sellOrders.add(sellOrder);
	 		//end creating the object
	 		  System.out.println(count);
	 		  profit = ((jitaBuyList.get(jitaItemID.get(i))-Float.parseFloat((String)jsonObject5.get("price"))))*Float.parseFloat((String) jsonObject5.get("volRemaining"));
	 		  profitTotal.add(profit);
	 		  goodBuys.println("sell price: "+sellOrder.getPrice()+"at "+sellOrder.getSolarSystemID()+"is lower than: "+jitaBuyList.get(jitaItemID.get(i))+"buy price "+allItems.get(jitaItemID.get(i))+" profit total: "+sellOrder.getProfitTotal());
	 	      
	 	   }
	    }  
		}
	    Collections.sort(sellOrders);
	    Collections.reverse(sellOrders);
	    for (int j = 0; j < sellOrders.size(); j++) {
			sellOrders.get(j).setSolarSystemName((solarSystems.get(sellOrders.get(j).getSolarSystemID())));
		}
	    for (int k = 0; k < sellOrders.size(); k++) {
			sellOrders.get(k).setRegionName((Regions.get(sellOrders.get(k).getRegionID())));
			System.out.println(sellOrders.get(k).getRegionName());
		}
	    
	    for (int i = 0; i < sellOrders.size(); i++) {
			goodBuys.println(sellOrders.get(i).toString());
			System.out.println(sellOrders.get(i).getSolarSystemName());
		}
	    
	    ItemOrder.getDestinations(sellOrders, Regions, solarSystems);
	    goodBuys.close();
	    Thread.sleep(1000);
	    System.out.println(sellOrders.size());
	    // create object that holds all of the data for an order;
	  
 	   
    }
}