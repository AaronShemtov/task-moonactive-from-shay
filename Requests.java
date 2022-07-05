package home.task.moonactive.from.shay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Requests {

    public String getRequestPerform(String url, String headerName1, String headerValue1,
                                    String headerName2, String headerValue2) throws
            IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader(headerName1, headerValue1);
        httpGet.setHeader(headerName2, headerValue2);

        CloseableHttpResponse responseGet = client.execute(httpGet);

        HttpEntity entity = responseGet.getEntity();

        String resultString = EntityUtils.toString(entity);
        //byte[] resultString = Files.readAllBytes(Paths.get("task.txt"));

        client.close();
        return resultString;
    }

    public TestPractice.JsonResponse responseRequest(String url, String headerName1, String headerValue1,
                                                     String headerName2, String headerValue2) throws
            IOException {

        String stringResponse = Requests.this.getRequestPerform(url, headerName1, headerValue1, headerName2, headerValue2);

        ObjectMapper objectMapper = new ObjectMapper();
        TestPractice.JsonResponse objectResponse = objectMapper.readValue(stringResponse, TestPractice.JsonResponse.class);
        return objectResponse;
    }

    public JsonNode getRequestCategoriesJsonNode(String url, String headerName1, String headerValue1,
                                                 String headerName2, String headerValue2) throws IOException {

        String stringResponse = Requests.this.getRequestPerform(url, headerName1, headerValue1, headerName2, headerValue2);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(stringResponse);

        return jsonNode;
    }
}

