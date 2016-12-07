package solfadev.net.solfastore.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.R;

/**
 * Created by Ratri on 11/23/2016.
 */

public class TransaksiPagerFragment extends BaseFragment {
    Context context;
    BaseActivity activity;
    private String Transaksi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycle_view, container, false);
        baseActivity = (BaseActivity) getActivity();
        baseActivity.setBaseFragment(this);/*WAJIB ADA*/

        return v;
    }

    public static TransaksiPagerFragment newInstance (String transaksi ){
        TransaksiPagerFragment fragment = new TransaksiPagerFragment();
        fragment.Transaksi=transaksi;
        return fragment;
    }
}
