package com.pstech.stocks.markettracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;
import com.pstech.stocks.markettracker.fragments.AppFragment;
import com.pstech.stocks.markettracker.fragments.BuybackFragment;
import com.pstech.stocks.markettracker.fragments.IPOFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("IPO Stock Market");

        initDrawer();

//        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
//            }
//        });

        fragmentManager.beginTransaction().
                replace(R.id.main_content, IPOFragment.newInstance("param1")).commit();
    }

    private void initDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        AppFragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_ipo:
                fragment = IPOFragment.newInstance("param1");
                break;
            case R.id.nav_buyback:
                fragment = BuybackFragment.newInstance("param1");
                break;
        }
        fragmentManager.beginTransaction().
                replace(R.id.main_content, fragment).commit();

        return super.onNavigationItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
