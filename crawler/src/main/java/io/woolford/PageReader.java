package io.woolford;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class PageReader {

    public String getHtml(URL url) throws MalformedURLException {

        WebDriver driver = new ChromeDriver();
        driver.get(url.toString());
        String html = driver.getPageSource();
        driver.close();

        return html;
    }

}