package com.example.t420.applazada.Adapters.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.t420.applazada.View.Fragment.DangNhap.FragmentDangKi;
import com.example.t420.applazada.View.Fragment.DangNhap.FragmentDangNhap;

/**
 * Created by t420 on 14-Nov-17.
 */

public class AdapterViewPagerDangNhap extends FragmentStatePagerAdapter {
    Fragment fragment=null;
    public AdapterViewPagerDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: fragment=new FragmentDangNhap(); break;
            case 1: fragment=new FragmentDangKi() ; break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0 : title="Đăng nhập";
                break;
            case 1 : title="Đăng kí" ;
                break;
        }
        return title;
    }
}
