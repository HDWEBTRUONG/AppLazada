package com.example.t420.applazada.View.Fragment.DangNhap;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.t420.applazada.Model.TrangChu.DangNhap.ModelDangNhap;
import com.example.t420.applazada.R;
import com.example.t420.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener , GoogleApiClient.OnConnectionFailedListener {
    Button btnLoginFacebook ,btnLoginGoogle;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    GoogleApiClient mGoogleApiClient;
    public static int RC_SIGN_IN=100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
              Intent intentTrangChu=new Intent(getActivity(), TrangChuActivity.class);
              startActivity(intentTrangChu);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        ModelDangNhap modelDangNhap=new ModelDangNhap();
        mGoogleApiClient=modelDangNhap.LayGoogleApiClient(getActivity(),this);

        btnLoginFacebook = (Button) view.findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle= (Button) view.findViewById(R.id.btnLoginGoogle);
        btnLoginFacebook.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnLoginFacebook :
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this,Arrays.asList("public_profile"));
                break;
            case R.id.btnLoginGoogle :
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                Intent intentTrangChu=new Intent(getActivity(),TrangChuActivity.class);
                startActivity(intentTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
