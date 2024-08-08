package com.doan.AppTuyenDung.Entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String thumbnail;
    private String coverImage;
    private String descriptionHTML;
    private String descriptionMarkdown;
    private String website;
    private String address;
    private String phonenumber;
    private Integer amountEmployer;
    private String taxnumber;
    private String statusCode;
    private String censorCode;
    private String file; // BYTES
    private Integer allowPost;
    private Integer allowHotPost;
    private Integer allowCvFree;
    private Integer allowCV;

    public Company() {
    }

    public Company(Integer id, String name, String thumbnail, String coverImage, String descriptionHTML,
            String descriptionMarkdown, String website, String address, String phonenumber, Integer amountEmployer,
            String taxnumber, String statusCode, String censorCode, String file, Integer allowPost,
            Integer allowHotPost, Integer allowCvFree, Integer allowCV) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.coverImage = coverImage;
        this.descriptionHTML = descriptionHTML;
        this.descriptionMarkdown = descriptionMarkdown;
        this.website = website;
        this.address = address;
        this.phonenumber = phonenumber;
        this.amountEmployer = amountEmployer;
        this.taxnumber = taxnumber;
        this.statusCode = statusCode;
        this.censorCode = censorCode;
        this.file = file;
        this.allowPost = allowPost;
        this.allowHotPost = allowHotPost;
        this.allowCvFree = allowCvFree;
        this.allowCV = allowCV;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "company")
    private Set<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescriptionHTML() {
        return descriptionHTML;
    }

    public void setDescriptionHTML(String descriptionHTML) {
        this.descriptionHTML = descriptionHTML;
    }

    public String getDescriptionMarkdown() {
        return descriptionMarkdown;
    }

    public void setDescriptionMarkdown(String descriptionMarkdown) {
        this.descriptionMarkdown = descriptionMarkdown;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getAmountEmployer() {
        return amountEmployer;
    }

    public void setAmountEmployer(Integer amountEmployer) {
        this.amountEmployer = amountEmployer;
    }

    public String getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCensorCode() {
        return censorCode;
    }

    public void setCensorCode(String censorCode) {
        this.censorCode = censorCode;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getAllowPost() {
        return allowPost;
    }

    public void setAllowPost(Integer allowPost) {
        this.allowPost = allowPost;
    }

    public Integer getAllowHotPost() {
        return allowHotPost;
    }

    public void setAllowHotPost(Integer allowHotPost) {
        this.allowHotPost = allowHotPost;
    }

    public Integer getAllowCvFree() {
        return allowCvFree;
    }

    public void setAllowCvFree(Integer allowCvFree) {
        this.allowCvFree = allowCvFree;
    }

    public Integer getAllowCV() {
        return allowCV;
    }

    public void setAllowCV(Integer allowCV) {
        this.allowCV = allowCV;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // Getters and Setters
}