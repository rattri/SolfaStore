package solfadev.net.solfastore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import solfadev.net.solfastore.fragment.GocartFragment;
import solfadev.net.solfastore.fragment.HomeFragment;
import solfadev.net.solfastore.fragment.LoginFragment;
import solfadev.net.solfastore.fragment.PricelistFragment;
import solfadev.net.solfastore.fragment.RegisterFragment;
import solfadev.net.solfastore.fragment.TransaksiFragment;
import solfadev.net.solfastore.model.Voucher;

public class MainActivity extends BaseActivity  implements ConnectivityReceiver.ConnectivityReceiverListener {
    Intent caller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseActivity = this;
        caller = getIntent();
        if (caller != null && caller.getExtras() != null) {
            currentFragment = caller.getExtras().getInt(KEY_FRAGMENT);
        } else currentFragment = FRAGMENT_HOME;

        FragmentManager manager = getSupportFragmentManager();
        switch (currentFragment) {
            case FRAGMENT_HOME:
                manager.beginTransaction().replace(R.id.container, HomeFragment.newInstance("INI HOME")).commit();
                isParrentView = true;
                break;
            case FRAGMENT_PRICELIST:
                Voucher voucher = (Voucher) caller.getSerializableExtra(BaseActivity.EXTRA_MODEL);
                PricelistFragment pricelistFragment = PricelistFragment.newInstance(voucher);
                manager.beginTransaction().replace(R.id.container, pricelistFragment).commit();
                isParrentView = false;
                break;
            case FRAGMENT_LOGIN:
                manager.beginTransaction().replace(R.id.container, LoginFragment.newInstance("INI LOGIN")).commit();
                isParrentView = false;
                break;
            case FRAGMENT_REGISTER:
                manager.beginTransaction().replace(R.id.container, RegisterFragment.newInstance("INI LOGIN")).commit();
                isParrentView = false;
                break;
            case FRAGMENT_TRANSAKSI:
                System.out.println("transaksi");
                manager.beginTransaction().replace(R.id.container, TransaksiFragment.newInstance("Ini Transaksi")).commit();
                isParrentView = false;
                break;
            case FRAGMENT_GOCART:
                manager.beginTransaction().replace(R.id.container, GocartFragment.newInstance("Ini Gocart")).commit();
        }
        setupToolbar();
        setupNavabar();
        navigationView.setNavigationItemSelectedListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        if (!isParrentView) {
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } else toggle.syncState();

        // Manually checking internet connection
        checkConnection();
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener( this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    //@Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


}
