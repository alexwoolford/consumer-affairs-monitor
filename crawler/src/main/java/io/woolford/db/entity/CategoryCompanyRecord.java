package io.woolford.db.entity;

public class CategoryCompanyRecord {

    private String category;
    private String company;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CategoryCompanyRecord{" +
                "category='" + category + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

}