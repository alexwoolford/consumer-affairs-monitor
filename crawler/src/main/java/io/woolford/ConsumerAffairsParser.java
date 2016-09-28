package io.woolford;


import io.woolford.db.entity.ConsumerAffairsRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsumerAffairsParser {

    public List<ConsumerAffairsRecord> getAffairsRecords(String html) throws IOException, ParseException {

        Document doc = Jsoup.parse(html);

        Element companyInfoBlock = doc.select("section.company-info").first().select("div.company-profile-contact").first();
        String company = companyInfoBlock.select("dd").first().text();

        List<ConsumerAffairsRecord> consumerAffairsRecordList = new ArrayList();

        Elements reviewBlocks = doc.select("div.review-post.user-post.complaint");

        for (Element reviewBlock : reviewBlocks){

            // get author details
            Element postAuthor = reviewBlock.select("div.post-author").first();
            String author = postAuthor.select("span.author-name").first().text();
            String postDateString = postAuthor.select("span.post-date").attr("content");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long timestamp = dateFormat.parse(postDateString).getTime() / 1000;

            Integer rating = Integer.valueOf(reviewBlock.select("div.review-rating").first().select("meta[itemprop=ratingValue]").attr("content"));

            String content = reviewBlock.select("div.post-content").first().text().replace(" Helpful?YesNo", "");

            ConsumerAffairsRecord consumerAffairsRecord = new ConsumerAffairsRecord();
            consumerAffairsRecord.setCompany(company);
            consumerAffairsRecord.setAuthor(author);
            consumerAffairsRecord.setTimestamp(timestamp);
            consumerAffairsRecord.setRating(rating);
            consumerAffairsRecord.setContent(content);

            consumerAffairsRecordList.add(consumerAffairsRecord);

        }

        return consumerAffairsRecordList;

    }

}
