package Clothing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class ClothingData {

    private ArrayList<String> clothing = new ArrayList<>();


    public ClothingData(){
        JSONFileScraper();
    }

    @SuppressWarnings("unchecked")
    public void JSONFileScraper(){

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("/Users/Mats/IdeaProjects/INFO216.SemCBOW/src/Misc/clothing.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray clothingList = (JSONArray) jsonObject.get("clothes");

            Iterator<String> itr = clothingList.iterator();
            while(itr.hasNext()){
                clothing.add(itr.next());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<String> getClothing() {
        return clothing;
    }
}


