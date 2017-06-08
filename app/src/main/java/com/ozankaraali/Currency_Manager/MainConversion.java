package com.ozankaraali.Currency_Manager;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;


/**
 * Created by ozan on 6/6/17.
 * API used: fixer.io
 */
public class MainConversion {
    public static double getRate(String base, String symbol)throws JSONException, ExecutionException, InterruptedException{
        return getRateTable(base,1).get(symbol);
    }
    public static ArrayList<String> currencies() throws JSONException, ExecutionException, InterruptedException {
        JSONObject object;
        String jsonData = new RetrieveCurrencies().execute().get();
        System.out.println(jsonData);
        object = new JSONObject(jsonData);
        System.out.println("Hello!");
        Object[] arr = object.getJSONObject("rates").keySet().toArray();
        String[] arry = Arrays.copyOf(arr, arr.length+1, String[].class);
        arry[arr.length]=object.getString("base");
        ArrayList<String> retn =new ArrayList<>(Arrays.asList(arry));
        Collections.sort(retn);
        return retn;
    }
    public static TreeMap<String,Double> getRateTable(String base,double amountOfMoney)throws JSONException, ExecutionException, InterruptedException{
        JSONObject object;
        String jsonData = new RetrieveRates().execute(base).get();
        System.out.println(jsonData);
        object = new JSONObject(jsonData);
        TreeMap<String, Double> map = new TreeMap<>();
        JSONObject jObject = new JSONObject(object.getJSONObject("rates").toString());
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            Double value = jObject.getDouble(key);
            map.put(key, value*amountOfMoney);

        }
        return map;
    }

}