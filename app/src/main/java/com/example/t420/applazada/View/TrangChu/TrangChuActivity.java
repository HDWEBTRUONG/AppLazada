package com.example.t420.applazada.View.TrangChu;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.t420.applazada.Adapters.ViewPager.AdapterViewPager;
import com.example.t420.applazada.Model.DangNhap.ModelDangNhap;
import com.example.t420.applazada.Presenter.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.t420.applazada.R;
import com.example.t420.applazada.View.DangNhap.DangNhapActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class TrangChuActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    String username = "";
    PresenterLogicXuLyMenu presenterLogicXuLyMenu;
    AccessToken accessToken;
    MenuItem itemDangNhap, itemDangXuat;
    Menu menu;
    ModelDangNhap modelDangNhap;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        overridePendingTransition(R.anim.anim_out_left, R.anim.anim_in_right);
        setContentView(R.layout.activity_trangchu);
        init();

    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutTrangChu);
        viewPager = (ViewPager) findViewById(R.id.viewpagerTrangChu);
        tabLayout.setupWithViewPager(viewPager);
        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu();

        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;

        googleSignInResult = modelDangNhap.LayThongTinGoogle(mGoogleApiClient);
        accessToken = presenterLogicXuLyMenu.LayTenNguoiDungFacebook();
        itemDangNhap = menu.findItem(R.id.it_dangnhap);
        itemDangXuat = menu.findItem(R.id.it_DangXuat);
        if (accessToken != null) {
            itemDangXuat.setVisible(true);
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            try {
                                username = object.getString("name");
                                itemDangNhap.setTitle(username);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");
            request.setParameters(parameters);
            request.executeAsync();
        }

        if (googleSignInResult != null) {
            itemDangXuat.setVisible(true);
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getEmail());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.it_dangnhap:
                if (accessToken == null && googleSignInResult == null) {
                    Intent intent = new Intent(this, DangNhapActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.it_DangXuat:
                if (accessToken != null || googleSignInResult != null) {
                    LoginManager.getInstance().logOut();
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;
            case R.id.it_giohang:
                break;

        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
