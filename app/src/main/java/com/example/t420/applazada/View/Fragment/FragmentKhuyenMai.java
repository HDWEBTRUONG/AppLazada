package com.example.t420.applazada.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.t420.applazada.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKhuyenMai extends Fragment {


    public FragmentKhuyenMai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khuyenmai, container, false);
    }

}
