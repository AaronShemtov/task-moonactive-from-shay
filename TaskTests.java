package home.task.moonactive.from.shay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TaskTests {

    private final String urlWrong = "https://jokes.p.rapidapi.com/jod/test";
    private final String urlGetCategories = "https://jokes.p.rapidapi.com/jod/categories";
    private final String urlGetCategoryDetails = "https://jokes.p.rapidapi.com/jod?category=";
    private final String headerName1 = "x-rapidapi-host";
    private final String headerValue1 = "jokes.p.rapidapi.com";
    private final String headerName2 = "x-rapidapi-key";
    private final String headerValue2 = "56d7a4653emsh4c19b463b18e6b7p144eb7jsn030e478c59b2";

    @Test
    public void getCategoriesTest()
            throws IOException {
        Requests requests = new Requests();
        CloseableHttpResponse objectCloseableHttpResponse = requests.getRequestPerform(urlWrong);
        Assert.assertEquals(objectCloseableHttpResponse.getStatusLine().getStatusCode(), 200, "Response code of Categories request");

        ConvertingJsonToClasses.JsonResponse jsonDataCategories = requests.responseConvertToClass(objectCloseableHttpResponse);
        Assert.assertNull(jsonDataCategories.getError());

        jsonDataCategories.contents.printEachCategory();
    }

    @Test
    public void writeToFileJoke3rdCategoryTest()
            throws IOException {
        Requests requests = new Requests();

        CloseableHttpResponse objectCloseableHttpResponseCategories = requests.getRequestPerform(urlGetCategories);
        Assert.assertEquals(objectCloseableHttpResponseCategories.getStatusLine().getStatusCode(), 200, "Response code of Categories request");

        ConvertingJsonToClasses.JsonResponse jsonDataCategories = requests.responseConvertToClass(objectCloseableHttpResponseCategories);
        Assert.assertNull(jsonDataCategories.getError());

        jsonDataCategories.contents.printEachCategory();
        String nameOf3rdCategory = jsonDataCategories.contents.getCategories().get(2).getName();

        CloseableHttpResponse objectCloseableHttpResponseJoke = requests.getRequestPerform(urlGetCategoryDetails + nameOf3rdCategory);
        Assert.assertEquals(objectCloseableHttpResponseJoke.getStatusLine().getStatusCode(), 200, "Response code of Joke request");

        ConvertingJsonToClasses.JsonResponse jsonDataJoke = requests.responseConvertToClass(objectCloseableHttpResponseJoke);
        Assert.assertNull(jsonDataCategories.getError());

        WorkWithFile objectWorkWithFile = new WorkWithFile();
        objectWorkWithFile.FormTextToWrite(jsonDataJoke);
    }

    @Test
    public void writeToFileJoke3rdCategoryTestWithJsonNode()
            throws IOException {
        Requests requests = new Requests();
        CloseableHttpResponse objectCloseableHttpResponseCategories = requests.getRequestPerform(urlGetCategories);
        Assert.assertEquals(objectCloseableHttpResponseCategories.getStatusLine().getStatusCode(), 200, "Response code is 200");

        JsonNode jsonNode = requests.getRequestCategoriesJsonNode(objectCloseableHttpResponseCategories);
        Assert.assertNull(jsonNode.get("error"));

        String nameOf3rdCategoryWitQuotes = jsonNode.get("contents").get("categories").get(2).get("name").toString();
        String nameOf3rdCategory = nameOf3rdCategoryWitQuotes.substring(1, nameOf3rdCategoryWitQuotes.length() - 1);

        CloseableHttpResponse objectCloseableHttpResponseJoke = requests.getRequestPerform(urlGetCategoryDetails + nameOf3rdCategory);
        Assert.assertEquals(objectCloseableHttpResponseJoke.getStatusLine().getStatusCode(), 200, "Response code of Joke request");

        ConvertingJsonToClasses.JsonResponse jsonDataJoke = requests.responseConvertToClass(objectCloseableHttpResponseJoke);
        Assert.assertNull(jsonDataJoke.getError());

        WorkWithFile objectWorkWithFile = new WorkWithFile();
        objectWorkWithFile.FormTextToWrite(jsonDataJoke);

        File file = new File(jsonDataJoke.contents.getJokes().get(0).joke.getId());
        Assert.assertTrue(file.exists());
    }

    public class Requests {

        public CloseableHttpResponse getRequestPerform(String url) throws
                IOException {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            httpGet.setHeader(headerName1, headerValue1);
            httpGet.setHeader(headerName2, headerValue2);

            CloseableHttpResponse responseGet = client.execute(httpGet);
            client.close();

            return responseGet;
        }

        public ConvertingJsonToClasses.JsonResponse responseConvertToClass(CloseableHttpResponse objectCloseableHttpResponse) throws
                IOException {
            HttpEntity entity = objectCloseableHttpResponse.getEntity();
            String stringToConvert = EntityUtils.toString(entity);

            ObjectMapper objectMapper = new ObjectMapper();
            ConvertingJsonToClasses.JsonResponse objectResponse = objectMapper.readValue(stringToConvert, ConvertingJsonToClasses.JsonResponse.class);
            return objectResponse;
        }

        public JsonNode getRequestCategoriesJsonNode(CloseableHttpResponse objectCloseableHttpResponse) throws IOException {
            HttpEntity entity = objectCloseableHttpResponse.getEntity();
            String stringToConvert = EntityUtils.toString(entity);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(stringToConvert);

            return jsonNode;
        }
    }
}