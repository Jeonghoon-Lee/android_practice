package ca.masoudbozorgi.customlist.xml_json;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ca.masoudbozorgi.customlist.Flower;


/**
 * Created by masoudbozorgi on 2018-03-07.
 */

public class JSONParser {

    ArrayList<Flower> flowerArrayList = new ArrayList<>();
    private InputStreamReader inputStreamReader;


    public ArrayList<Flower> processJSONFile(Context context, String fileName){

        AssetManager assetManager = context.getResources().getAssets();

        try {
            // Pass filename to assetManager.open(fileName). In next step pass it to InputStreamReader
            inputStreamReader = new InputStreamReader(assetManager.open(fileName));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String oneLine = null;
            StringBuilder stringBuilder = new StringBuilder();

            while((oneLine = bufferedReader.readLine()) != null){
                stringBuilder.append(oneLine);
            }

            bufferedReader.close();
            inputStreamReader.close();

            processJSONData(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flowerArrayList;
    }


    public void processJSONData(String data){

        try {
            //JSONObject  jsonObject = new JSONObject(data);
            //JSONArray jsonArray = jsonObject.getJSONArray("");

            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++){

                JSONObject currentJSONFlowerObject = jsonArray.getJSONObject(i);

                int productId         = currentJSONFlowerObject.getInt("productId");
                String category       = currentJSONFlowerObject.getString("category");
                String name           = currentJSONFlowerObject.getString("name");
                String instructions   = currentJSONFlowerObject.getString("instructions");
                double price          = currentJSONFlowerObject.getDouble("price");
                String photo          = currentJSONFlowerObject.getString("photo");

                // (long productId, String category, String name, String instructions, double price, String photo)
                Flower currentFlower = new Flower(productId, category, name, instructions, price, photo);
                flowerArrayList.add(currentFlower);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}