package urlgeneratorservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.core.env.Environment;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Resource {

    public static String getResource(Environment env, String resourceId) throws IOException {
        String port = env.getProperty("storageservice.port");
        String endpoint = "url/" + resourceId;
        String url = "http://127.0.0.1:" + port + "/" + endpoint;
        return getMethod(url);
    }

    public static String createResource(Environment env, String resourceId, String resource, String expiry) throws IOException {
        String port = env.getProperty("storageservice.port");
        String endpoint = "url";
        String url = "http://127.0.0.1:" + port + "/" + endpoint;
        return postMethod(url, resourceId, resource, expiry);
    }

    public static void deleteResource(Environment env, String resourceId) throws IOException {
        String port = env.getProperty("storageservice.port");
        String endpoint = "url/" + resourceId;
        String url = "http://127.0.0.1:" + port + "/" + endpoint;
        deleteMethod(url);
    }

    private static String getMethod(String url) throws IOException {
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        URL urlObj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // just want to do an HTTP GET here
        connection.setRequestMethod("GET");

        // give it 15 seconds to respond
        connection.setReadTimeout(15*1000);
        connection.connect();

        // read the output from the server
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        stringBuilder = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }

    private static String postMethod(String url, String resourceId, String resource, String expiration) throws IOException {
        URL urlObj = new URL(url);
        StorageUrlResource storageUrlResource = new StorageUrlResource(resourceId, resource, expiration);

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // just want to do an HTTP POST here
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        ObjectMapper mapper = new ObjectMapper();
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

        mapper.writeValue((OutputStream) wr, storageUrlResource);

        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private static void deleteMethod(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("DELETE");

        connection.connect();
        int responseCode = connection.getResponseCode();
        return;
    }
}
