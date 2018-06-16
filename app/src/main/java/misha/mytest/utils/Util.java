package misha.mytest.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import misha.mytest.dataModels.Item;

/**
 * Created by micha on 6/14/2018.
 */

public class Util {
    public static List<Item> GetItems(String jsonStr) throws JSONException{
        List<Item> items = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray JSONHits = jsonObject.getJSONArray("hits");
        for(int i = 0;i<JSONHits.length();i++){
            JSONObject jsonItem = JSONHits.getJSONObject(i);
            String fullName = getJSONItemFullName(jsonItem);
            int level = getJSONItemLevel(jsonItem);
            boolean identified = getJSONItemIdentified(jsonItem);
            items.add(new Item(fullName,level,identified));
        }
        return items;
    }

    private static String getJSONItemFullName(JSONObject jsonObject){ // get the name or set "no name" if failed
        try{
            return jsonObject.getJSONObject("_source").getJSONObject("info").getString("fullName");
        }catch (JSONException ex){
            return "no name";
        }
    }

    private static int getJSONItemLevel(JSONObject jsonObject){ // get the Level or -1 if failed
        try{
            return jsonObject.getJSONObject("_source").getJSONObject("requirements").getInt("Level");
        }catch (JSONException ex){
            return -1;
        }
    }

    private static boolean getJSONItemIdentified(JSONObject jsonObject){ //get if identified or false if failed
        try{
            return jsonObject.getJSONObject("_source").getJSONObject("attributes").getBoolean("identified");
        }catch (JSONException ex){
            return false;
        }
    }
}
