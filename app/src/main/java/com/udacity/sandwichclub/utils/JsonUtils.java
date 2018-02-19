package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String JSON_NAME = "name";
    private static final String JSON_MAIN_NAME = "mainName";
    private static final String JSON_ALSO_KNOWS_AS = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_INGREDIENTS = "ingredients";

    @Nullable
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject nameObject = sandwichObject.getJSONObject(JSON_NAME);

            String mainName = nameObject.getString(JSON_MAIN_NAME);
            List<String> alsoKnownAs = getStringList(nameObject.getJSONArray(JSON_ALSO_KNOWS_AS));

            String placeOfOrigin = sandwichObject.getString(JSON_PLACE_OF_ORIGIN);
            String description = sandwichObject.getString(JSON_DESCRIPTION);
            String image = sandwichObject.getString(JSON_IMAGE);
            List<String> ingredients = getStringList(sandwichObject.getJSONArray(JSON_INGREDIENTS));

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> getStringList(JSONArray jsonArray) throws JSONException{
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            resultList.add(jsonArray.getString(i));
        }
        return resultList;
    }
}
