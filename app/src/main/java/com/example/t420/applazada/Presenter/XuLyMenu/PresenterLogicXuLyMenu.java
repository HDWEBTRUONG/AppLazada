package com.example.t420.applazada.Presenter.XuLyMenu;

import android.content.Context;
import android.util.Log;

import com.example.t420.applazada.Model.TrangChu.DangNhap.ModelDangNhap;
import com.example.t420.applazada.Model.TrangChu.XyLyMenu.XuLyMenu;
import com.facebook.AccessToken;

/**
 * Created by t420 on 20-Nov-17.
 */

public class PresenterLogicXuLyMenu implements PresenterXuLyMenu {
    @Override
    public AccessToken LayTenNguoiDungFacebook() {
        ModelDangNhap modelDangNhap =new ModelDangNhap();
        AccessToken accessToken= modelDangNhap.LayTokenHienTai();
        return accessToken;
    }
}
