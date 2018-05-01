package com.pstech.stocks.buybackipostockmarket.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pagrawal on 23-04-2018.
 */

public enum IssueType {

    BOOK_BUILT(0, "Book Built Issue IPO"),
    TENDER(1, "Tender"),
    OPEN_MARKET(2, "Open Market"),
    UNKNOWN(-1, "Unknown");

    private static final Map<Integer, IssueType> lineMap = new HashMap<>();
    static {
        for (IssueType issueType : IssueType.values()) {
            lineMap.put(issueType.typeId, issueType);
        }
    }

    public static IssueType getIssueType(int typeId) {
        IssueType issueType = lineMap.get(typeId);
        if (issueType == null)
            return IssueType.UNKNOWN;
        return issueType;
    }

    String typeName;
    int typeId;

    IssueType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
