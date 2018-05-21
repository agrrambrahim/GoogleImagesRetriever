import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomGoogleSearch {
    final static String apiKey = "AIzaSyBj-ITJkHzmIvWwx7NyTzyfgfz0QYLtmuo";
    final static String customSearchEngineKey = "011355480889527342621:bznrw1ufoek";
    final static String searchURL = "https://www.googleapis.com/customsearch/v1?";

    public static String search(String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String buildSearchString(String searchString, int start, int numOfResults) {

        // replace spaces in the search query with +
        String newSearchString = searchString.replace(" ", "%20");
        String toSearch = searchURL +"q="+newSearchString +"&cx=" + customSearchEngineKey +"&searchType=image"+"&key=" + apiKey ;

        // specify response format as json
        toSearch += "&alt=json";

        // specify starting result number
        toSearch += "&start=" + start;

        // specify the number of results you need from the starting position
        toSearch += "&num=" + numOfResults;

        System.out.println("Seacrh URL: " + toSearch);
        return toSearch;
    }

    public static void main(String[] args) throws Exception {
        List<String> imagesUrl = new ArrayList<String>();
        String url = buildSearchString("game", 1, 10);
        String result = search(url);
        JSONObject json = new JSONObject(result);
        JSONArray items = new JSONArray(json.get("items"));
        for (int i=0;i<items.length();i++) {
            JSONObject item = items.getJSONObject(i);
            imagesUrl.add((String) item.get("link"));

        }

        System.out.println(result);

    }
}