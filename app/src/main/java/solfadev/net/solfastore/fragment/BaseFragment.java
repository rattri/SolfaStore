package solfadev.net.solfastore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import solfadev.net.solfastore.BaseActivity;

/**
 * Created by Ratri on 11/21/2016.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity baseActivity;
    protected String titleFragment = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public BaseActivity getBaseActivity(){
        return baseActivity;
    }

    public String getTitle(){
        return titleFragment;
    }

    protected void setTitle(String title){
        titleFragment = title;
        baseActivity.setTitle(title);
    }
}