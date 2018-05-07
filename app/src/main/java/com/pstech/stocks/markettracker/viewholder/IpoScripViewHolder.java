package com.pstech.stocks.markettracker.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.pstech.stocks.markettracker.utils.AppConstants.*;

import com.bumptech.glide.Glide;
import com.pstech.stocks.markettracker.R;
import com.pstech.stocks.markettracker.model.IpoScrip;
import com.pstech.stocks.markettracker.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IpoScripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.company_photo) ImageView companyPhotoView;
    @BindView(R.id.company_name) TextView companyNameView;
    @BindView(R.id.star)ImageView starView;
    @BindView(R.id.post_num_stars) TextView numStarsView;

    @BindView(R.id.txt_issuesize) TextView issueSizeView;
//    @BindView(R.id.issue_type) TextView issueTypeView;
    @BindView(R.id.txt_issuedate) TextView issueDateView;
    @BindView(R.id.txt_issueprice) TextView offerPriceView;
    @BindView(R.id.txt_facevalue) TextView faceValueView;
    @BindView(R.id.txt_marketlot) TextView marketLotView;
    @BindView(R.id.btn_share) Button btnShare;
    @BindView(R.id.btn_moreinfo) Button btnMoreInfo;
    private Context context;

    public IpoScripViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.context = context;
        btnShare.setOnClickListener(this);
        btnMoreInfo.setOnClickListener(this);
    }

    public void bindToPost(IpoScrip post, View.OnClickListener starClickListener) {

        numStarsView.setText(String.valueOf(post.getStarCount()));
        starView.setOnClickListener(starClickListener);

        companyNameView.setText(processString(post.getCompanyName()));
        issueSizeView.setText(processString(post.getIssueSize()));
//        issueTypeView.setText(processString(post.getIssueType().toString()));
        issueDateView.setText(makeRange(post.getStartDate(), post.getEndDate()));
        offerPriceView.setText(makeRange(String.valueOf(post.getMinPrice()),
                String.valueOf(post.getMaxPrice())));
        faceValueView.setText(String.valueOf(post.getFaceValue()));
        marketLotView.setText(processInt(post.getMarketLot()));
        AppConstants.loadImage(context, companyPhotoView, post.getLogourl());

    }

    @Override
    public void onClick(View view) {
        if (view == btnShare) {
            Toast.makeText(this.context, "Share button clicked", Toast.LENGTH_SHORT).show();
        } else if (view == btnMoreInfo) {
            Toast.makeText(this.context, "More Info button clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
