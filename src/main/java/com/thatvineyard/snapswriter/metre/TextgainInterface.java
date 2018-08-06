package com.thatvineyard.snapswriter.metre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.json.*;

/**
 * TextGainInterface
 */
public class TextgainInterface {

    private final static String TEXTGAINURL = "https://api.textgain.com";
    private final static String textgainSyllablesUrl = TEXTGAINURL + "/1/syllables";
    private final static String key = "***";

    private final static String lang = "en";

    public static int numberOfSyllablesInString(String text) {
        int numberOfSyllables = 0;

        String getRequest = buildGetRequest(text);

        JSONObject map = getJsonResponseFromUrl(getRequest);
        // map = map.getJSONObject("text");

        JSONArray listOfWords = map.getJSONArray("text");

        for (int i = 0; i < listOfWords.length(); i++) {
            numberOfSyllables += listOfWords.getJSONObject(i).getInt("n_syllables");
        }

        return numberOfSyllables;
    }

    private static String buildGetRequest(String text) {
        try {
            String getRequest = textgainSyllablesUrl;

            getRequest += "?q=" + URLEncoder.encode(text, "utf-8");
            getRequest += "&lang=" + lang + "&key=" + key;

            return getRequest;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static JSONObject getJsonResponseFromUrl(String url) {
        try {
            String response = "";
            String message;

            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            while ((message = buffer.readLine()) != null) {
                response += message;
            }

            return new JSONObject(response);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}