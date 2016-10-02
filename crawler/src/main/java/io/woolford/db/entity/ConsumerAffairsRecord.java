package io.woolford.db.entity;

import org.apache.solr.client.solrj.beans.Field;

public class ConsumerAffairsRecord {

    public ConsumerAffairsRecord(){
        // required for solrj to make an instance
    }

    public ConsumerAffairsRecord(String id, String company, String author, Long timestamp, Integer rating, Integer sentiment, String content){
        this.id = id;
        this.company = company;
        this.author = author;
        this.timestamp = timestamp;
        this.rating = rating;
        this.sentiment = sentiment;
        this.content = content;
    }

    private String id;
    private String company;
    private String author;
    private Long timestamp;
    private Integer rating;
    private Integer sentiment;
    private String content;

    public String getId() {
        return id;
    }

    @Field("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    @Field("company")
    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthor() {
        return author;
    }

    @Field("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Field("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getRating() {
        return rating;
    }

    @Field("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getSentiment() {
        return sentiment;
    }

    @Field("sentiment")
    public void setSentiment(Integer sentiment) {
        this.sentiment = sentiment;
    }

    public String getContent() {
        return content;
    }

    @Field("content")
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ConsumerAffairsRecord{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                ", author='" + author + '\'' +
                ", timestamp=" + timestamp +
                ", rating=" + rating +
                ", sentiment=" + sentiment +
                ", content='" + content + '\'' +
                '}';
    }

}
