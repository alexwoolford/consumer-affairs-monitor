package io.woolford;

import io.woolford.db.entity.CategoryCompanyRecord;
import io.woolford.db.entity.ConsumerAffairsRecord;
import io.woolford.db.mapper.DbMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

//    @Scheduled(fixedDelay = 30000L)
    @PostConstruct
    public void main() throws IOException, InterruptedException, ParseException {

        ConsumerAffairsParser consumerAffairsParser = new ConsumerAffairsParser();

        // Iterate over each category/company specified in MySQL
        for (CategoryCompanyRecord categoryCompanyRecord : dbMapper.getCategoryCompanyList()){

            URL url = new URL("http://consumeraffairs.com/" + categoryCompanyRecord.getCategory() + "/" + categoryCompanyRecord.getCompany() + ".html");

            Boolean done = false;
            while (!done){

                PageReader pageReader = new PageReader();
                String html = pageReader.getHtml(url);

                List<ConsumerAffairsRecord> consumerAffairsRecordList = consumerAffairsParser.getAffairsRecords(html);

                for (ConsumerAffairsRecord consumerAffairsRecord : consumerAffairsRecordList){
                    //TODO: write to Solr
                    System.out.println(consumerAffairsRecord);
                }

                url = nextURL(html);
                if (url == null){
                    done = true;
                }

                TimeUnit.SECONDS.sleep(30);

            }
        }
    }

    private URL nextURL(String html) throws MalformedURLException {

        Document doc = Jsoup.parse(html);

        // check to see if "Next" link exists
        URL nextURL = null;
        Elements links = doc.select("a");
        for (Element link : links){
            if (link.text().contains("Next")){
                nextURL = new URL("http://consumeraffairs.com/" + link.attr("href"));
            }
        }

        return nextURL;
    }

}
