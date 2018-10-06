package com.thatvineyard.snapswriter.metre.calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.json.*;

/**
 * TextGainInterface
 */
public class TextgainReader {

    private final static String TEXTGAIN_URL = "https://api.textgain.com";
    private final static String textgainSyllablesUrl = TEXTGAIN_URL + "/1/syllables";
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
            StringBuilder response = new StringBuilder();
            String message;

            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            while ((message = buffer.readLine()) != null) {
                response.append(message);
            }

            return new JSONObject(response.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
