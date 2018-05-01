package com.pstech.stocks.buybackipostockmarket.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pagrawal on 19-04-2018.
 */

public class IpoScrip {

    public String startDate;
    public String endDate;
    public int minPrice;
    public int maxPrice;
    public String companyName;
    public int allotmentPrice;
    public String allotmentDate;
    public String tradingDate;
    public int lastUpdatedPrice;
    public int lastUpdatedDate;
    public int scripCode;
    public String companyDetail;
    public int issueType; // can be enum
    public String issueSize;
    public int faceValue;
    public int marketLot;
    public int minOrderQuantity;
    public int discount;
    public int starCount = 0;
    public String uid;
    public Map<String, Boolean> stars = new HashMap<>();

    public IpoScrip() {
        // Default constructor required for calls to DataSnapshot.getValue(IpoScript.class)
    }

    public IpoScrip(String userId, String username, String title, String body) {
        this.uid = userId;
        this.companyName = username;
        this.issueSize = title;
        this.companyDetail = body;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getAllotmentPrice() {
        return allotmentPrice;
    }

    public String getAllotmentDate() {
        return allotmentDate;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public int getLastUpdatedPrice() {
        return lastUpdatedPrice;
    }

    public int getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public int getScripCode() {
        return scripCode;
    }

    public String getCompanyDetail() {
        return companyDetail;
    }

    public IssueType getIssueType() {
        return IssueType.getIssueType(issueType);
    }

    public String getIssueSize() {
        return issueSize;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getMarketLot() {
        return marketLot;
    }

    public int getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public int getDiscount() {
        return discount;
    }

    public int getStarCount() {
        return starCount;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", companyName);
        result.put("title", issueSize);
        result.put("body", companyDetail);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

}
