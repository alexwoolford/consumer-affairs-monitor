package io.woolford.db.entity;


public class ConsumerAffairsRecord {

    private String company;
    private String author;
    private Long timestamp;
    private Integer rating;
    private String content;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ConsumerAffairsRecord{" +
                "company='" + company + '\'' +
                ", author='" + author + '\'' +
                ", timestamp=" + timestamp +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }

}
