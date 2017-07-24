package ua.artcode.englishfun.utils;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ua.artcode.englishfun.exception.HttpRequestException;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by serhii on 09.07.17.
 */
public final class HttpClientUtils {

    private HttpClientUtils() {
    }

    public static String get(String url) throws IOException, HttpRequestException {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        HttpClient httpClient = httpClientBuilder.build();

        HttpGet getRequest = new HttpGet(url);

        // todo may be passed as an argument
        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new HttpRequestException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }


        String responseContent =
                CharStreams.toString(new InputStreamReader(response.getEntity().getContent(), Charsets.UTF_8));


        return responseContent;
    }
}
