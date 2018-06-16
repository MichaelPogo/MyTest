package misha.mytest.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import misha.mytest.MainActivity;
import misha.mytest.adapters.ItemsAdapter;
import misha.mytest.dataModels.Item;

/**
 * Created by micha on 6/15/2018.
 */

public class ItemsLoaderTask extends AsyncTask<Void,Void,List<Item>> {

    @Override
    protected List<Item> doInBackground(Void... voids) {
        List<Item> items = null;
        try {
            URL url = new URL("http://nikita.hackeruweb.co.il/hackDroid/items.json");
            HttpURLConnection urlConnection =  (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuilder jsonSB = new StringBuilder();
            while((line =bufferedReader.readLine()) != null){
                jsonSB.append(line);
            }
            items = Util.GetItems(jsonSB.toString()); // kinda Factory pattern - passing string to the method and getting a List of Items

        } catch (IOException e){ // MalformedURLException is an IOExeption so catching only IOException
            e.printStackTrace();
        }catch(JSONException ex){
            Log.e("Error","cant parse JSON");

        }
        return items;
    }
}
