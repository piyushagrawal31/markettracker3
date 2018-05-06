package com.pstech.stocks.markettracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pstech.stocks.markettracker.model.Comment;
import com.pstech.stocks.markettracker.model.IpoScrip;
import com.pstech.stocks.markettracker.utils.AppConstants;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pstech.stocks.markettracker.utils.AppConstants.makeRange;
import static com.pstech.stocks.markettracker.utils.AppConstants.processString;

public class IpoScripDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "IpoScripDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private CommentAdapter mAdapter;

//    private TextView mAuthorView;
//    private TextView mTitleView;
//    private TextView mBodyView;

    @BindView(R.id.company_photo) ImageView companyPhotoView;
    @BindView(R.id.company_name) TextView companyNameView;

    @BindView(R.id.txt_issuesize) TextView issueSizeView;
//    @BindView(R.id.issue_type) TextView issueTypeView;
    @BindView(R.id.txt_issuedate) TextView issueDateView;
    @BindView(R.id.txt_issueprice) TextView offerPriceView;
    @BindView(R.id.txt_facevalue) TextView faceValueView;
    @BindView(R.id.txt_marketlot) TextView marketLotView;
    @BindView(R.id.txt_detail) TextView companyDetail;

    private EditText mCommentField;
    private Button mCommentButton;
//    private RecyclerView mCommentsRecycler;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child(AppConstants.IPO_SCRIP).child(mPostKey);
//        mCommentsReference = FirebaseDatabase.getInstance().getReference()
//                .child("post-comments").child(mPostKey);

        // Initialize Views
//        mTitleView = findViewById(R.id.post_title);
//        mBodyView = findViewById(R.id.post_body);
//        mCommentField = findViewById(R.id.field_comment_text);
//        mCommentButton = findViewById(R.id.button_post_comment);
//        mCommentsRecycler = findViewById(R.id.recycler_comments);

//        mCommentButton.setOnClickListener(this);
//        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                IpoScrip post = dataSnapshot.getValue(IpoScrip.class);
                // [START_EXCLUDE]
//                starView.setOnClickListener(starClickListener);

                companyNameView.setText(processString(post.getCompanyName()));
                issueSizeView.setText(processString(post.getIssueSize()));
//                issueTypeView.setText(processString(post.getIssueType().toString()));
                issueDateView.setText(makeRange(post.getStartDate(), post.getEndDate()));
                offerPriceView.setText(makeRange(String.valueOf(post.getMinPrice()),
                        String.valueOf(post.getMaxPrice())));
                faceValueView.setText(String.valueOf(post.getFaceValue()));
                marketLotView.setText(String.valueOf(post.getMarketLot()));
                companyDetail.setText(Html.fromHtml(processString(post.getCompanyDetail())));

                AppConstants.loadImage(getApplicationContext(), companyPhotoView,
                        post.getLogourl());
                getSupportActionBar().setTitle(processString(post.getCompanyName()));

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(IpoScripDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

        // Listen for comments
        // mAdapter = new CommentAdapter(this, mCommentsReference);
        // mCommentsRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mPostReference.removeEventListener(mPostListener);
        }

        // Clean up comments listener

        if (mAdapter != null)
            mAdapter.cleanupListener();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
//        if (i == R.id.button_post_comment) {
//            postComment();
//        }
    }

    private void postComment() {
        final String uid = getUid();
//        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // Get user information
//                        User user = dataSnapshot.getValue(User.class);
//                        String authorName = user.username;
//
//                        // Create new comment object
//                        String commentText = mCommentField.getText().toString();
//                        Comment comment = new Comment(uid, authorName, commentText);
//
//                        // Push the comment, it will appear in the list
//                        mCommentsReference.push().setValue(comment);
//
//                        // Clear the field
//                        mCommentField.setText(null);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
    }

    private static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView authorView;
        public TextView bodyView;

        public CommentViewHolder(View itemView) {
            super(itemView);

            authorView = itemView.findViewById(R.id.comment_author);
            bodyView = itemView.findViewById(R.id.comment_body);
        }
    }

    private static class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mCommentIds = new ArrayList<>();
        private List<Comment> mComments = new ArrayList<>();

        public CommentAdapter(final Context context, DatabaseReference ref) {
            mContext = context;
            mDatabaseReference = ref;

            // Create child event listener
            // [START child_event_listener_recycler]
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                    // A new comment has been added, add it to the displayed list
                    Comment comment = dataSnapshot.getValue(Comment.class);

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    mCommentIds.add(dataSnapshot.getKey());
                    mComments.add(comment);
                    notifyItemInserted(mComments.size() - 1);
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so displayed the changed comment.
                    Comment newComment = dataSnapshot.getValue(Comment.class);
                    String commentKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Replace with the new data
                        mComments.set(commentIndex, newComment);

                        // Update the RecyclerView
                        notifyItemChanged(commentIndex);
                    } else {
                        Log.w(TAG, "onChildChanged:unknown_child:" + commentKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    String commentKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Remove data from the list
                        mCommentIds.remove(commentIndex);
                        mComments.remove(commentIndex);

                        // Update the RecyclerView
                        notifyItemRemoved(commentIndex);
                    } else {
                        Log.w(TAG, "onChildRemoved:unknown_child:" + commentKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    Comment movedComment = dataSnapshot.getValue(Comment.class);
                    String commentKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                    Toast.makeText(mContext, "Failed to load comments.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            ref.addChildEventListener(childEventListener);
            // [END child_event_listener_recycler]

            // Store reference to listener so it can be removed on app stop
            mChildEventListener = childEventListener;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_comment, parent, false);
            return new CommentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            Comment comment = mComments.get(position);
            holder.authorView.setText(comment.author);
            holder.bodyView.setText(comment.text);
        }

        @Override
        public int getItemCount() {
            return mComments.size();
        }

        public void cleanupListener() {
            if (mChildEventListener != null) {
                mDatabaseReference.removeEventListener(mChildEventListener);
            }
        }

    }
}
