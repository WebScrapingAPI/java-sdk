﻿[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.webscrapingapi/webscrapingapi-sdk/badge.svg)](https://search.maven.org/artifact/io.github.webscrapingapi/webscrapingapi-sdk)

# WebScrapingAPI Java SDK

[**WebScrapingAPI**](https://www.webscrapingapi.com/) is the leading REST API for web scraping, allowing users to easily scrape websites and collect HTML code, while using techniques such as rotating proxies to prevent detection or CAPTCHA solving, in the background, in order to ensure the highest quality of services. 

The Java SDK is designed to facilitate the implementation and use of the WebScrapingAPI in any Java project, with less effort and and much faster, allowing you to concentrate more on the manipulation of the data collecteed, rather than on setting up the project.  

## Installation
The Java SDK for WebScrapingAPI is available both on Nexus and on Github, hence, there are two instalation methods:
### #1: Download from GitHub
1. Download the `webscrapingapi.jar` from this repository;
2. Add the file to your project's library classpath;
3. Import the package in your file.

### #2: Install using Maven
1. Check the latest version of this SDK [here](https://s01.oss.sonatype.org/#nexus-search;quick~io.github.webscrapingapi)
2. Add the dependancy to your POM.xml file:
```xml
<dependency>
  <groupId>io.github.webscrapingapi</groupId>
  <artifactId>webscrapingapi-sdk</artifactId>
  <version>1.0.1</version>
</dependency>
```
3. run `mvn clean install` in your console
4. Import the package in your file.
## API Key
Please note that the use of WebScrapingAPI requires an API key. You can obtain one by accessing the [WebScrapingApi](https://app.webscrapingapi.com/register) website.

## Usage
Using the SDK it's quite easy. Here is a quick example of a GET request to the WebScrapingAPI:

```
import webscrapingapi.com.WebScrapingAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class Example {

    public static void main(String[] args) throws IOException  //static method
    {
        WebScrapingAPI wsa = new WebScrapingAPI("YOUR_API_KEY");

        // For more parameter examples, you can always read the documentation.
        Map<String, String> params = Map.ofEntries(
                // API Parameters
                // The url you're scraping
                entry("url","https://en.wikipedia.org/wiki/Kangaroo"),
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
                entry("wait_for", "0")
        );

        // Set headers
        Map<String, String> headers = Map.ofEntries(
            // API Headers
            entry("authorization", "bearer test"),
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

For a better understanding of the API and its parameters, please refer to the [WebScrapingAPI documentation](https://docs.webscrapingapi.com/#request-parameters)
