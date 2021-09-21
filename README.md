# WebScrapingApi NodeJS SDK

WebScrapingApi is an API that allows scraping websites while using rotating proxies to prevent bans. This SDK for Java makes the usage of the API easier to implement in any project you have.

## Installation

Run the following command in the main folder of your project:

```
Download the webscrapingapi.jar file and add it to your projects library.
```

## API Key

To use the API and the SDK you will need a API Key. You can get one by registering at [WebScrapingApi](https://app.webscrapingapi.com/register)

## Usage

Using the SDK it's quite easy.
An example of a GET call to the API is the following:

```
import WebScrapingAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class Test {

    public static void main(String[] args) throws IOException  //static method
    {
        WebScrapingAPI wsa = new WebScrapingAPI("YOUR_API_KEY");

        // For more parameter examples, you can always read the documentation.
        Map<String, String> params = Map.ofEntries(
                // API Parameters
                // Set to 0 (off, default) or 1 (on) depending on whether or not to render JavaScript on the target web page. JavaScript rendering is done by using a browser.
                entry("render_js", "1"),
                // Set datacenter (default) or residential depending on whether proxy type you want to use for your scraping request. Please note that a single residential proxy API request is counted as 25 API requests.
                entry("proxy_type", "datacenter"),
                // Specify the 2-letter code of the country you would like to use as a proxy geolocation for your scraping API request. Supported countries differ by proxy type, please refer to the Proxy Locations section for details.
                entry("country", "us"),
                // Set depending on whether or not to use the same proxy address to your request.
                entry("session", "1"),
                // Specify the maximum timeout in milliseconds you would like to use for your scraping API request. In order to force a timeout, you can specify a number such as 1000. This will abort the request after 1000ms and return whatever HTML response was obtained until this point in time.
                entry("timeout", "10000"),
                // Set desktop (default) or mobile or tablet, depending on whether the device type you want to your for your scraping request.
                entry("device", "desktop"),
                // Specify the option you would like to us as conditional for your scraping API request. Can only be used when the parameter render_js=1 is activated.
                entry("wait_until", "domcontentloaded"),
                // Some websites may use javascript frameworks that may require a few extra seconds to load their content. This parameters specifies the time in miliseconds to wait for the website. Recommended values are in the interval 5000-10000.
                entry("wait_for", "0"),
        );

        // Set headers
        Map<String, String> headers = Map.ofEntries(
            // API Headers
            entry("authorization", "bearer test")
            // Specify custom cookies to be passed to the request.
            entry("cookie", "test_cookie=abc; cookie_2=def")
        );

        // Send a request using WebSCrapingAPI SDK
        HashMap<String, String> response = wsa.get(params, headers);

        // If the response was a success
        if(Objects.equals(response.get("status"), "true")) {
            // continue with your code
            System.out.println(response.get("message"));
        }
        else if (Objects.equals(response.get("status"), "false")) {
            // manage errors
            System.out.println(response.get("message"));
        }
    }
}
```

For a better understanding of the parameters, please check out [our documentation](https://docs.webscrapingapi.com/#request-parameters)