import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomGoogleSearch {

    final static String apiKey = "AIzaSyBj-ITJkHzmIvWwx7NyTzyfgfz0QYLtmuo";
    final static String customSearchEngineKey = "011355480889527342621:bznrw1ufoek";
    final static String searchURL = "https://www.googleapis.com/customsearch/v1?";
    private String query = "fortnite youtube";
    private int maxResults;
    public CustomGoogleSearch(String query,int maxResults) {
    this.query=query;
    this.maxResults=maxResults;
        doSearch();
    }

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


        toSearch += "&imgSize=xxlarge";
        // specify response format as json
        toSearch += "&alt=json";

        // specify starting result number
        toSearch += "&start=" + start;

        // specify the number of results you need from the starting position
        toSearch += "&num=" + numOfResults;

        System.out.println("Seacrh URL: " + toSearch);
        return toSearch;
    }
       public void doSearch() {
           ImagesHandler imagesHandler = new ImagesHandler();
           List<BufferedImage> images = new ArrayList<BufferedImage>();
           String url = buildSearchString(query, 1, maxResults);
           String result = search(url);
           JSONObject json = new JSONObject(result);
           JSONArray items = (JSONArray) json.get("items");
           for (int jsonPictureIndex = 0; jsonPictureIndex < items.length(); jsonPictureIndex++) {
               JSONObject item = items.getJSONObject(jsonPictureIndex);
               try {
                   JSONObject imageContext = item.getJSONObject("image");
                   String imageThumbnailLink = (String) imageContext.get("thumbnailLink");
                   String originalImageLink = (String) item.get("link");
                   BufferedImage image = ImageIO.read(new URL(imageThumbnailLink));
                   images.add(image);
                   imagesHandler.showPicture(jsonPictureIndex,originalImageLink,image);
                   //  pictures.add(new AbstractMap.SimpleEntry(ImageIO.read(new URL(originalImageLink)),ImageIO.read(new URL(imageThumbnailLink))));
               } catch (Exception e) {
                   continue;
               }
               //ImagesHandler imagesHandler = new ImagesHandler(images);

               //imagesHandler.displayThumbnails();
            //   imagesHandler.displayImages(images);
               // JFrame snakeFrame = new JFrame();
               //snakeFrame.setBounds(100, 200, 800, 800);
               //snakeFrame.setVisible(true);

           }
       }

    /*public static void main(String[] args) throws Exception {
        doSearch();
    }
*/
    }
