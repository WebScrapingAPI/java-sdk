import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WebScrapingAPI {
    private final String api_key;

    private static String urlEncodeUTF8(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private static String urlEncodeUTF8(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey()),
                    urlEncodeUTF8(entry.getValue())
            ));
        }
        return sb.toString();
    }

    public WebScrapingAPI(String key) {
        api_key = key;
    }

    private StringBuffer request(String method, Map<String, String> params, Map<String, String> headers, Map<String, String> data) throws IOException {

        // Prepare the url
        String prepare_url = "https://api.webscrapingapi.com/v1?api_key=" + this.api_key + "&" + urlEncodeUTF8(params);

        // Set up the connection
        HttpURLConnection connection;
        URL wsa_url = new URL(prepare_url);
        connection = (HttpURLConnection) wsa_url.openConnection();

        // Configure method and timeouts
        connection.setRequestMethod(method);
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        // Set headers
        if (headers != null)
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

        // Set data
        if (data != null) {
            byte[] post_data_bytes = urlEncodeUTF8(data).getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            connection.getOutputStream().write(post_data_bytes);
        }

        int status = connection.getResponseCode();

        BufferedReader streamReader;

        if (status > 299)
            streamReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        else
            streamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = streamReader.readLine()) != null) {
            content.append(inputLine);
        }

        streamReader.close();
        connection.disconnect();

        return content;
    }

    public StringBuffer get(Map<String, String> params, Map<String, String> headers) throws IOException {
        return request("GET", params, headers, null);
    }

    public StringBuffer post(Map<String, String> params, Map<String, String> headers, Map<String, String> data) throws IOException {
        return request("POST", params, headers, data);
    }
}
