package solfadev.net.solfastore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.adapter.GocartAdapter;
import solfadev.net.solfastore.R;

import static solfadev.net.solfastore.BaseActivity.LOGIN_OPERATION;
import static solfadev.net.solfastore.BaseActivity.UNIQUE_ID;
import static solfadev.net.solfastore.BaseActivity.getBaseActivity;


/**
 * Created by Ratri on 11/30/2016.
 */

public class GocartFragment extends  BaseFragment {
    RecyclerView recyclerView;
    Context context;
    BaseActivity activity;
    GocartAdapter gocartAdapter;
    public String user, message;
    SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycle_view, container, false);
        baseActivity = (BaseActivity) getActivity();
        baseActivity.setBaseFragment(this);/*WAJIB ADA*/
        SharedPreferences pref;
        pref = baseActivity.getSharedPreferences(LOGIN_OPERATION, Context.MODE_PRIVATE);
        System.out.println("user" +pref.getString(UNIQUE_ID, ""));
        user=pref.getString(UNIQUE_ID, "");
//        setTitle("Home");
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        gocartAdapter = new GocartAdapter(getBaseActivity(), user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gocartAdapter);
        gocartAdapter.initData();
        return v;
    }

    public static GocartFragment newInstance(String message) {
        GocartFragment fragment = new GocartFragment();
        fragment.message = message;
        return  fragment;
    }
}
