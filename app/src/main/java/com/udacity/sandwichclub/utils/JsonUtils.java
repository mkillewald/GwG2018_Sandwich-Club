package com.udacity.sandwichclub.utils;

import android.nfc.Tag;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJSON = new JSONObject(json);

            JSONObject name = sandwichJSON.getJSONObject("name");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAsList = new ArrayList<String>();
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                }
            }

            JSONArray ingredientsArray = sandwichJSON.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<String>();
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i ++) {
                    ingredientsList.add(ingredientsArray.getString(i));
                }
            }

            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJSON.getString("description"));
            sandwich.setImage(sandwichJSON.getString("image"));
            sandwich.setIngredients(ingredientsList);


        } catch (JSONException e) {
            System.out.println("ERROR: Failure to parse JSON");
            e.printStackTrace();
        }

        return sandwich;
    }
}
