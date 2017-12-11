package com.example.t420.applazada.Adapters.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.t420.applazada.View.Fragment.FragmentDienTu;
import com.example.t420.applazada.View.Fragment.FragmentKhuyenMai;
import com.example.t420.applazada.View.Fragment.FragmentNoiBat;

/**
 * Created by t420 on 14-Nov-17.
 */

public class AdapterViewPager extends FragmentStatePagerAdapter {
    Fragment fragment=null;
    public AdapterViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : fragment=new FragmentNoiBat(); break;
            case 1 : fragment=new FragmentKhuyenMai(); break;
            case 2 : fragment=new FragmentDienTu(); break;
            case 3 : fragment=new FragmentNoiBat(); break;
            case 4 : fragment=new FragmentKhuyenMai(); break;
            case 5 : fragment=new FragmentDienTu(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0 : title="Nổi bật"; break;
            case 1 : title="Khuyến mại"; break;
            case 2 : title="Điện tử" ; break;
            case 3 : title="Nổi bật"; break;
            case 4 : title="Khuyến mại"; break;
            case 5 : title="Điện tử" ; break;
        }
        return title;
    }
}
