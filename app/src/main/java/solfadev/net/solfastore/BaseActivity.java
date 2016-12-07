package solfadev.net.solfastore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.adapter.CounterCartAdapter;
import solfadev.net.solfastore.api.Interfaces.CounterCartService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.fragment.BaseFragment;


/**
 * Created by Ratri on 11/21/2016.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_PRICELIST = 1;
    public static final int FRAGMENT_LOGIN = 2;
    public static final int FRAGMENT_TRANSAKSI= 3;
    public static final int FRAGMENT_REGISTER= 4;
    public static final int FRAGMENT_GOCART=5;
    public static final String EXTRA_MODEL = "extra.model";
    public static final String KEY_FRAGMENT = "solfa.fragment";
    protected int currentFragment = FRAGMENT_HOME;
    public static BaseActivity baseActivity;
    public static BaseFragment  fragment;
    public boolean isParrentView = true;
    public Toolbar toolbar;
    public DrawerLayout drawer;
    public NavigationView navigationView;
    public String title;
    public TextView welcome, userName;
    public static final String BASE_URL = "http://solfadev.net/api/vouchershop/";
    public static final String REGISTER_OPERATION = "register";
    public static final String LOGIN_OPERATION = "drawer_notlogin";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";
    public static final String RESET_PASSWORD_INITIATE = "resPassReq";
    public static final String RESET_PASSWORD_FINISH = "resPass";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String UNIQUE_ID = "unique_id";
    public SharedPreferences pref;
    public static String CounterCart;
    public static String ID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static BaseActivity getBaseActivity (){
        return baseActivity;
    }

    public void setBaseFragment(BaseFragment fragment){
        this.fragment = fragment;
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setTitle(String title){
      getSupportActionBar().setTitle(title);
    }

    protected void setupNavabar() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        welcome = (TextView) header.findViewById(R.id.welcome);
        userName = (TextView) header.findViewById(R.id.username);
        pref = getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
        if (!pref.getBoolean(IS_LOGGED_IN, false)) {
            navigationView.inflateMenu(R.menu.drawer_notlogin);
            welcome.setText("Kamu belum drawer_notlogin");
        } else {
            navigationView.inflateMenu(R.menu.drawer_login);
            welcome.setText("Selamat Datang ");
            userName.setText(pref.getString(NAME, "")+ pref.getString(UNIQUE_ID,""));
            System.out.println(pref.getString(UNIQUE_ID,""));
        }
    }

//    public void setupCart (){
//        pref = getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
//        if (!pref.getBoolean(IS_LOGGED_IN, false)) {
//            CounterCart ="0";
//        }
//        else {
//           initData();
//        }
//
//    }



    public void startFragment(int TYPE, String judul) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(KEY_FRAGMENT, TYPE);
        i.putExtra("judul", judul);
        startActivity(i);
    }

    public void startFragment(Intent i) {
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemCart = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        pref = getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
        if (!pref.getBoolean(IS_LOGGED_IN, false)) {
            CounterCart ="0";
            setBadgeCount(this, icon, CounterCart);
        }
        else {
            CounterCart ="1";
            setBadgeCount(this, icon, CounterCart);
//            CounterCartAdapter counterCartAdapter = new CounterCartAdapter();
//            counterCartAdapter.initData();
        }

        return super.onCreateOptionsMenu(menu);
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            pref = getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
            if (!pref.getBoolean(IS_LOGGED_IN, false)) {
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN,"LOGIN FRAGMENT");
            } else {
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_GOCART,"GOCART FRAGMENT");
            }


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.beranda :
                System.out.println("klik di "+item.getItemId());
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_HOME, "HOME FRAGMENT");
                break;

            case R.id.login:
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN,"LOGIN FRAGMENT");
                break;
            case R.id.register:
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_REGISTER,"LOGIN FRAGMENT");
                break;
            case R.id.transaksi:
                System.out.println("transaksi klik");
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_TRANSAKSI,"TRANSAKSI FRAGMENT");
                break;
            case R.id.logout:
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean(BaseActivity.IS_LOGGED_IN,false);
                editor.putString(BaseActivity.EMAIL,"");
                editor.putString(BaseActivity.NAME,"");
                editor.putString(BaseActivity.UNIQUE_ID,"");
                editor.apply();

                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Kamu berhasil logout")
                        .show();
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_HOME, "HOME FRAGMENT");
                break;
//            case R.id.transaksi:
//                getBaseActivity().startFragment(BaseActivity.FRAGMENT_TRANSAKSI, "");
        }

        return super.onOptionsItemSelected(item);
    }
}


