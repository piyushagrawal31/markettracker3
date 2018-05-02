package com.pstech.stocks.markettracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pstech.stocks.markettracker.utils.AppConstants;
import com.pstech.stocks.markettracker.viewholder.AboutUsActivity;


public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;

    public BaseActivity() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            skipAuth();
        }
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null || user.isAnonymous()) {
            return "anonymous";
        }

        return user.getUid();
    }

    private void skipAuth() {

        Log.d(AppConstants.TAG, "skipAuth");
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(AppConstants.TAG, "signIn:onComplete:" + task.isSuccessful());
                hideProgressDialog();

                if (task.isSuccessful()) {
//                    onAuthSuccess(task.getResult().getUser());
                    Log.d(AppConstants.TAG, "signInAnonymously:SUCCESS");

                } else {
                    Exception e = task.getException();
                    AuthResult result = task.getResult();
                    String answer = "EMPTY";
                    if (e != null && result != null) {
                        answer = e.getMessage() + " : " + result.toString();
                    }
//                    Toast.makeText(SignInActivity.this, "Sign In Failed" + answer,
//                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static final String READ_ONLY_USER = "read_only_user";

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, AppConstants.SHARE_TXT);
            sendIntent.setType("text/html");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        } else if (id == R.id.nav_rate) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } else if (id == R.id.nav_feedback) {
            sendEmail();
        } else if (id == R.id.nav_about) {
            Intent aboutUsIntent = new Intent(BaseActivity.this, AboutUsActivity.class);
            startActivity(aboutUsIntent);
        }

        return true;
    }

    public void sendEmail() {
        String emailId = getResources().getString(R.string.emailId);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailId, null));
        String[] addresses = {emailId};
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject: " + getPackageName());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses); // String[] addresses
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

}
