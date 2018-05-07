package com.pstech.stocks.markettracker.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pagrawal on 30-04-2018.
 */

public class BuybackScrip {
    public String companyName;
    public Date considerDate;
    public double buybackPrice;

    // Date at which company announces that it will consider buyback on particular date.
    // short for date of announcement of consideration
    public Date conAnnDate;
    public int issueType; // can be enum
    public Date recordDate;
    public Date startDate;
    public Date endDate;

    // in percentage
    public double buybackSize;
    public long buybackSizeAmount;
    public int buybackShares;

    // ratio mentioned in offer letter
    public double retailPreRatio;

    // ratio mentioned in post buyback announcement pdf
    public double retailPostRatio;

    // links
    public String considerLink;
    public String announcementLink;
    public String offerLetterLink;
    public String postBBCorpAnnLink;
    public String shareholdingPatternLink;

    public Map<String, Boolean> stars = new HashMap<>();
    public int starCount = 0;

    public BuybackScrip() {

    }

}
