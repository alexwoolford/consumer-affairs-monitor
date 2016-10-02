//package io.woolford;
//
//import io.woolford.db.entity.ConsumerAffairsRecord;
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Component
//public class Temp {
//
//    @Autowired
//    SolrClient solrClient;
//
//    @PostConstruct
//    public void solrWriteTemp() throws IOException, SolrServerException {
//
//        ConsumerAffairsRecord consumerAffairsRecord = new ConsumerAffairsRecord();
//
//        consumerAffairsRecord.setId("379179c25165a4040e73426b3b8a943d");
//        consumerAffairsRecord.setCompany("Wells Fargo");
//        consumerAffairsRecord.setAuthor("Tina of Milford, CT");
//        consumerAffairsRecord.setTimestamp(Long.valueOf(1473372112));
//        consumerAffairsRecord.setRating(1);
//        consumerAffairsRecord.setSentiment(1);
//        consumerAffairsRecord.setContent("Customer service is terrible. Mailing your payments 5 days early is not enough. The mail room must not log them timely and then they charge you 37.00. Call to have corrected and they report they already did that in May. The answer is yes you did because you DO NOT enter data timely. I will never bank with Wells Fargo again. Go to Webster Bank. Awesome customer service and never have I had a problem with any payments sent to them in the same time frame as Wells Fargo. I should have known better. Years ago I got my pay off value on my car payment and paid in full. I received the title in the mail and then 1 year later a bill from Wells Fargo for over $300??? Why would I pay you if you already mailed the title? Get it together.");
//
//        solrClient.addBean(consumerAffairsRecord);
//        solrClient.commit();
//
//    }
//
//
//}
