package io.woolford;


import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
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

    SentimentScorer sentimentScorer = new SentimentScorer();

    public List<ConsumerAffairsRecord> getAffairsRecords(String html) throws IOException, ParseException {

        Document doc = Jsoup.parse(html);

        String company = doc.select("section.company-info").first().select("div#basic-information").select("dd[itemprop=name]").text();

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

            Integer sentiment = sentimentScorer.getMainSentiment(content);

            ConsumerAffairsRecord consumerAffairsRecord = new ConsumerAffairsRecord();
            consumerAffairsRecord.setCompany(company);
            consumerAffairsRecord.setAuthor(author);
            consumerAffairsRecord.setTimestamp(timestamp);
            consumerAffairsRecord.setRating(rating);
            consumerAffairsRecord.setSentiment(sentiment);
            consumerAffairsRecord.setContent(content);

            // create record ID (MD5 hash of company/author/timestamp/rating/sentiment/content)
            Hasher hasher = Hashing.md5().newHasher();
            hasher.putObject(consumerAffairsRecord, consumerAffairsRecordFunnel);
            String md5 = hasher.hash().toString();

            consumerAffairsRecord.setId(md5);

            consumerAffairsRecordList.add(consumerAffairsRecord);

        }

        return consumerAffairsRecordList;

    }

    Funnel<ConsumerAffairsRecord> consumerAffairsRecordFunnel = new Funnel<ConsumerAffairsRecord>() {
        @Override
        public void funnel(ConsumerAffairsRecord consumerAffairsRecord, PrimitiveSink into) {
            into
                    .putString(consumerAffairsRecord.getCompany(), Charsets.UTF_8)
                    .putString(consumerAffairsRecord.getAuthor(), Charsets.UTF_8)
                    .putLong(consumerAffairsRecord.getTimestamp())
                    .putInt(consumerAffairsRecord.getRating())
                    .putInt(consumerAffairsRecord.getSentiment())
                    .putString(consumerAffairsRecord.getContent(), Charsets.UTF_8);
        }
    };

}
