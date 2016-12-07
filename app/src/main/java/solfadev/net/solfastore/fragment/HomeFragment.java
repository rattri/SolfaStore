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

/**
 * Created by Ratri on 11/21/2016.
 */
public class HomeFragment extends BaseFragment {
    String message, voucher;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String VOUCHER_GAME="1";
    public static String VOUCHER_PULSA="2";
    public static String VOUCHER_PLN="3";


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

    public static HomeFragment newInstance(String message) {
        HomeFragment fragment = new HomeFragment();
        fragment.message = message;
        return fragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(VoucherFragment.newInstance(VOUCHER_GAME), "GAME");
        adapter.addFragment(VoucherFragment.newInstance(VOUCHER_PULSA), "PULSA");
        adapter.addFragment(VoucherFragment.newInstance(VOUCHER_PLN), "PLN");
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
