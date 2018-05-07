package com.pstech.stocks.markettracker.fragments.ipofragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.pstech.stocks.markettracker.utils.AppConstants;

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
