package solfadev.net.solfastore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.R;
import solfadev.net.solfastore.model.ServerResponse;

/**
 * Created by Ratri on 11/23/2016.
 */

public class TransaksiFragment extends BaseFragment {
    String message, voucher;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final String BELUM_DIBAYAR = "1";
    private  final String DIPROSES = "2";
    private final String SELESEI = "3";
    private final String BATAL = "4";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        baseActivity = (BaseActivity) getActivity();
        baseActivity.setBaseFragment(this);/*WAJIB ADA*/

        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setTitle(message);
        return root;
    }

    public static TransaksiFragment newInstance(String message) {
        TransaksiFragment fragment = new TransaksiFragment();
        fragment.message = message;
        return fragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(TransaksiPagerFragment.newInstance(BELUM_DIBAYAR), "Belum Bayar");
        adapter.addFragment(TransaksiPagerFragment.newInstance(SELESEI), "Selesei");
        adapter.addFragment(TransaksiPagerFragment.newInstance(BATAL), "Batal");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
