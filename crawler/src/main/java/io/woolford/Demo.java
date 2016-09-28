package io.woolford;

import io.woolford.db.entity.CategoryCompanyRecord;
import io.woolford.db.entity.ConsumerAffairsRecord;
import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException, ParseException, SolrServerException {


        String zkHostString = "hadoop01:2181,hadoop02:2181,hadoop03:2181/solr";
        SolrClient solr = new CloudSolrClient.Builder().withZkHost(zkHostString).build();

        String html = FileUtils.readFileToString(new File("/Users/awoolford/chase_bank.html"));

        ConsumerAffairsParser consumerAffairsParser = new ConsumerAffairsParser();
        List<ConsumerAffairsRecord> categoryCompanyRecordList = consumerAffairsParser.getAffairsRecords(html);

        for (ConsumerAffairsRecord categoryCompanyRecord : categoryCompanyRecordList){

            SolrInputDocument document = new SolrInputDocument();
            document.addField("company", categoryCompanyRecord.getCompany());
            document.addField("author", categoryCompanyRecord.getAuthor());
            document.addField("timestamp", categoryCompanyRecord.getTimestamp());
            document.addField("rating", categoryCompanyRecord.getRating());
            document.addField("content", categoryCompanyRecord.getContent());

            UpdateResponse response = solr.add("consumer_affairs", document);
            solr.commit();

        }

    }

}
