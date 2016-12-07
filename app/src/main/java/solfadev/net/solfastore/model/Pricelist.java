package solfadev.net.solfastore.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.Serializable;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.MainActivity;
import solfadev.net.solfastore.adapter.CartAdapter;
import solfadev.net.solfastore.api.form.CartForm;

import static solfadev.net.solfastore.BaseActivity.getBaseActivity;

/**
 * Created by Ratri on 10/3/2016.
 */
public class Pricelist implements Serializable {
    String id, nama, harga, id_kategori;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public void cardOnClock(final BaseActivity activity, String kategori, String id_kategori){
        CartAdapter cartAdapter;
        SharedPreferences pref;
        pref = getBaseActivity().getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
        System.out.println(id_kategori);
        if (!pref.getBoolean(BaseActivity.IS_LOGGED_IN, false)) {
            getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN, "Login FRAGMENT");

        } else {
            switch (id_kategori){
                case "1":
                    CartForm cartForm= new CartForm();
                    cartForm.setUser(getBaseActivity().pref.getString(BaseActivity.UNIQUE_ID, ""));
                    cartForm.setProduk(getId());
                    cartForm.setJumlah(""+1);
                    cartForm.setKategori(id_kategori);
                    cartAdapter = new CartAdapter(getBaseActivity(), cartForm);
                    System.out.println("go to adapter");
                    cartAdapter.initData();

            }
//            case id_kategori=="1":
//            if (id_kategori==1) {
//                CartForm cartForm= new CartForm();
//                cartForm.setUser(BaseActivity.getBaseActivity().pref.getString(BaseActivity.UNIQUE_ID, ""));
//                cartForm.setProduk(getId());
//                cartForm.setJumlah(""+1);
//                cartForm.setKategori(id_kategori);
//                cartAdapter = new CartAdapter(getBaseActivity(), cartForm);
//                System.out.println("go to adapter");
//                cartAdapter.initData();
//
//            }
//            else if (id_kategori=="2"){
//                System.out.println("else if");
//            }
//            else {
//                System.out.println("else");
//            }

        }


//        Intent i = new Intent(activity, MainActivity.class);
//        i.putExtra(BaseActivity.EXTRA_MODEL, this);
//        i.putExtra(BaseActivity.KEY_FRAGMENT, BaseActivity.FRAGMENT_CHECKOUT);
//        i.putExtra("judul", "Price");
////        i.putExtra(CheckoutFragment.KEY_KATEGORI, kategori);
////        i.putExtra(CheckoutFragment.ID_KATEGORI, id_kategori);
//        activity.startFragment(i);
        System.out.println("tes");
    }
}
