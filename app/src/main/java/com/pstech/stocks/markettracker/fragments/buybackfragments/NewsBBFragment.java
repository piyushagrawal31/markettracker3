package com.pstech.stocks.markettracker.fragments.buybackfragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.pstech.stocks.markettracker.utils.AppConstants;

/**
 * Created by pagrawal on 20-04-2018.
 */

public class NewsBBFragment extends ScripListBBFragment {
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child(AppConstants.BUYBACK_SCRIP);
                //.orderByChild("endDate").startAt(AppConstants.getTodayDate()); // .endAt("2018-03-29");
    }
}
