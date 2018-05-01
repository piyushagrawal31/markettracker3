package com.pstech.stocks.buybackipostockmarket.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.pstech.stocks.buybackipostockmarket.utils.AppConstants;

/**
 * Created by pagrawal on 20-04-2018.
 */

public class PastFragment extends ScripListFragment {
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child(AppConstants.IPO_SCRIP)
                .orderByChild("endDate").endAt(AppConstants.getTodayDate());

    }
}