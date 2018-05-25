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

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJSON = new JSONObject(json);

            JSONObject name = sandwichJSON.getJSONObject(KEY_NAME);

            JSONArray alsoKnownAsArray = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            ArrayList<String> alsoKnownAsList = new ArrayList<String>();
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                }
            }

            JSONArray ingredientsArray = sandwichJSON.getJSONArray(KEY_INGREDIENTS);
            ArrayList<String> ingredientsList = new ArrayList<String>();
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i ++) {
                    ingredientsList.add(ingredientsArray.getString(i));
                }
            }

            sandwich.setMainName(name.getString(KEY_MAIN_NAME));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(sandwichJSON.getString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJSON.getString(KEY_DESCRIPTION));
            sandwich.setImage(sandwichJSON.getString(KEY_IMAGE));
            sandwich.setIngredients(ingredientsList);


        } catch (JSONException e) {
            System.out.println("ERROR: Failure to parse JSON");
            e.printStackTrace();
        }

        return sandwich;
    }
}
