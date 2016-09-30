package io.woolford;

import io.woolford.db.entity.CategoryCompanyRecord;
import io.woolford.db.entity.ConsumerAffairsRecord;
import io.woolford.db.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ConsumerAffairsCrawler {

    @Autowired
    DbMapper dbMapper;

    @Scheduled(fixedDelay = 30000L)
    public void main() throws IOException, InterruptedException, ParseException {

        ConsumerAffairsParser consumerAffairsParser = new ConsumerAffairsParser();

        for (CategoryCompanyRecord categoryCompanyRecord : dbMapper.getCategoryCompanyList()){

            URL url = new URL("http://consumeraffairs.com/" + categoryCompanyRecord.getCategory() + "/" + categoryCompanyRecord.getCompany() + ".html");

            PageReader pageReader = new PageReader();
            String html = pageReader.getHtml(url);

            List<ConsumerAffairsRecord> consumerAffairsRecordList = consumerAffairsParser.getAffairsRecords(html);

            for (ConsumerAffairsRecord consumerAffairsRecord : consumerAffairsRecordList){
                System.out.println(consumerAffairsRecord);
            }

            TimeUnit.SECONDS.sleep(30);

        }

    }

}
