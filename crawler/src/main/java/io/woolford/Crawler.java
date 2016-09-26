package io.woolford;

import io.woolford.db.entity.CategoryCompanyRecord;
import io.woolford.db.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class Crawler {

    @Autowired
    DbMapper dbMapper;

    @Scheduled(fixedDelay = 30000L)
    public void main() throws MalformedURLException, InterruptedException {

        for (CategoryCompanyRecord categoryCompanyRecord : dbMapper.getCategoryCompanyList()){

            URL url = new URL("http://consumeraffairs.com/" + categoryCompanyRecord.getCategory() + "/" + categoryCompanyRecord.getCompany() + ".html");

            PageReader pageReader = new PageReader();
            String html = pageReader.getHtml(url);
            System.out.println(url.toString());
            System.out.println(html);

            TimeUnit.SECONDS.sleep(10);


        }

    }

}
