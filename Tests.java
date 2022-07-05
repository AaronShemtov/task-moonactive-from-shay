package home.task.moonactive.from.shay;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.testng.Assert;


public class Tests {

    private final String urlGetCategories = "https://jokes.p.rapidapi.com/jod/categories";
    private final String urlGetCategoryDetails = "https://jokes.p.rapidapi.com/jod?category=";
    private final String headerName1 = "x-rapidapi-host";
    private final String headerValue1 = "jokes.p.rapidapi.com";
    private final String headerName2 = "x-rapidapi-key";
    private final String headerValue2 = "56d7a4653emsh4c19b463b18e6b7p144eb7jsn030e478c59b2";

    @Test
    public void whenSendPostRequestUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "John"));
        params.add(new BasicNameValuePair("password", "pass"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse responsePost = client.execute(httpPost);
        //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        System.out.println(responsePost.getStatusLine().getStatusCode());
        System.out.println(responsePost.getStatusLine().toString());
        //client.close();
    }

    @Test
    public void getRequest1()
            throws ClientProtocolException, IOException {
        Requests requests = new Requests();

        TestPractice.JsonResponse jsonDataCategories = requests.responseRequest(urlGetCategories, headerName1, headerValue1, headerName2, headerValue2);
        jsonDataCategories.contents.printEachCategory();

        String nameOf3rdCategory = jsonDataCategories.contents.getCategories().get(2).getName();
        System.out.println("name of 3rd category: " + nameOf3rdCategory);

        TestPractice.JsonResponse jsonDataJokes = requests.responseRequest(urlGetCategoryDetails + nameOf3rdCategory, headerName1, headerValue1, headerName2, headerValue2);
        System.out.println("hi hi hi");

        //write data to file
        System.out.println(jsonDataJokes.contents.getJokes().get(0).getDescription());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).getCategory());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getTitle());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getId());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getText());
    }

    @Test
    public void getRequestCategoryJsonNode()
            throws IOException {
        Requests requests = new Requests();
        JsonNode jsonNode = requests.getRequestCategoriesJsonNode(urlGetCategories, headerName1, headerValue1, headerName2, headerValue2);

        //String nameOf3rdCategory = jsonNode.get("contents").get("categories").get(2).get("name").toString();
        JsonNode nameOf3rdCategory = jsonNode.get("contents").get("categories").get(2).get("name");


        System.out.println("name of 3rd category: " + nameOf3rdCategory);

        TestPractice.JsonResponse jsonDataJokes = requests.responseRequest(urlGetCategoryDetails + nameOf3rdCategory, headerName1, headerValue1, headerName2, headerValue2);
        System.out.println("hi hi hi");

        //write data to file
        System.out.println(jsonDataJokes.contents.getJokes().get(0).getDescription());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).getCategory());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getTitle());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getId());
        System.out.println(jsonDataJokes.contents.getJokes().get(0).joke.getText());
    }
}