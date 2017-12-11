package com.example.t420.applazada.Model.DangNhap;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

/**
 * Created by t420 on 20-Nov-17.
 */

public class ModelDangNhap {
     AccessToken accessToken;
    AccessTokenTracker tokenTracker;
    public AccessToken LayTokenHienTai(){
        tokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                 accessToken=currentAccessToken;
            }
        };
        accessToken=AccessToken.getCurrentAccessToken();// nếu token còn thì gọi hàm , còn lại thì gọi hàm trên
        return  accessToken;
    }

    public void HuyTokenTracker(){
        tokenTracker.stopTracking();
    }
    public GoogleApiClient LayGoogleApiClient(Context context , GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleApiClient =new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity)context,onConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> result=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(result.isDone()) {
            return result.get();
        }else {
            return null;
        }
    }
}
