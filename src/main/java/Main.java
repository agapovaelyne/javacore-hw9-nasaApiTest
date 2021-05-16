import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ApiKey key = new ApiKey();
        String url = String.format("https://api.nasa.gov/planetary/apod?api_key=%s", key.getValue() );

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.stream(response.getAllHeaders())
                .forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        List<NasaResponse> NASAResponseObject = null;
        try {
            NASAResponseObject = mapper.readValue(
                    response.getEntity().getContent(), new TypeReference<List<NasaResponse>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        NASAResponseObject.forEach(System.out::println);
        String imageUrl = NASAResponseObject.get(0).getUrl();


        request = new HttpGet(imageUrl);

        Pattern imageNamePattern = Pattern.compile("([A-Za-z0-9\\-_]+\\.jpg)");
        Matcher matcher = imageNamePattern.matcher(imageUrl);
        String imageName = "NASAResponse.jpg";
        if (matcher.find()) {
            imageName = matcher.group();
        }

        response = null;
        try {
            response = httpClient.execute(request);
            byte[] imageBody = response.getEntity().getContent().readAllBytes();
            FileOutputStream fos = new FileOutputStream(new File(imageName));
            fos.write(imageBody, 0, imageBody.length);
            fos.close();
            System.out.printf("Image \"%s\" downloaded", imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            response = httpClient.execute(request);
            InputStream is = response.getEntity().getContent();
            FileOutputStream fos = new FileOutputStream(new File(imageName));

            byte[] imageBytes = new byte[5600];
            int read;
            while((read = is.read(imageBytes)) > 0) {
                fos.write(imageBytes,0,read);
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
