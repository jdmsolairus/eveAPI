package eveAPI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Decode {
	public static float getPrice(String url) throws Exception {
		
		float price = 0;
		
		 String values = GetRequest.sendGet(url);
         
         JSONObject jsonObject = new JSONObject();
         Object obj=JSONValue.parse(values);
         JSONArray array = (JSONArray)obj;
 	    //System.out.println(array.get(0));
 	    
 	    JSONObject jsonObject2 = (JSONObject)array.get(0);
 	   // System.out.println(jsonObject2.get("emd"));
 	    Object obj2 = JSONValue.parse("["+jsonObject2.get("emd").toString()+"]");
 	    JSONArray array2 = (JSONArray)obj2;
 	    //System.out.println(array2.get(0));
 	    
 	    JSONObject jsonObject3 = (JSONObject)array2.get(0);
 	    //System.out.println(jsonObject3.get("result"));
 	    Object obj3 = JSONValue.parse(jsonObject3.get("result").toString());
 	    JSONArray array3 = (JSONArray)obj3;
 	    //System.out.println(array3.get(0));
 	    
 	    JSONObject jsonObject4 = (JSONObject)array3.get(0);
 	    //System.out.println(jsonObject4.get("row"));
 	    Object obj4 = JSONValue.parse("["+jsonObject4.get("row").toString()+"]");
 	    JSONArray array4 = (JSONArray)obj4;
 	    
 	    JSONObject jsonObject5 = (JSONObject)array4.get(0);
 	    price = Float.parseFloat((jsonObject5.get("price").toString()));
		
		return price;
	}
	
}


