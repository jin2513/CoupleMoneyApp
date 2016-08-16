package lyj.money.book.couplemoney.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.kakao.auth.Session;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import net.daum.mf.oauth.MobileOAuthLibrary;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.common.BaseFragment;
import lyj.money.book.couplemoney.social.daum.DaumLogin;
import lyj.money.book.couplemoney.social.daum.DaumLoginActivity;
import lyj.money.book.couplemoney.social.facebook.FacebookLogin;
import lyj.money.book.couplemoney.social.facebook.FacebookLoginActivity;
import lyj.money.book.couplemoney.social.google.GoogleLogin;
import lyj.money.book.couplemoney.social.google.GoogleLoginActivity;
import lyj.money.book.couplemoney.social.kakao.KakaoLogin;
import lyj.money.book.couplemoney.social.kakao.KakaoLoginActivity;
import lyj.money.book.couplemoney.social.naver.NaverLogin;
import lyj.money.book.couplemoney.social.naver.NaverLoginActivity;
import lyj.money.book.couplemoney.social.twitter.TwitterLogin;
import lyj.money.book.couplemoney.social.twitter.TwitterLoginActivity;

public class LoginFragment extends BaseFragment {

    private static final String TWITTER_KEY = "75I3Gn07Jk5CVzF1RolbHBG8m";
    private static final String TWITTER_SECRET = "1Akk75gPerNhVPKzbzmuyEwT8O6v8IRkE9QdZntCvme2UR1Vi2";

    // 구글 RESULT_CODE
    private static final int RC_SIGN_IN = 9001;

    // 트위터
    @Bind(R.id.bt_login_twitter_login)
    TwitterLoginButton mBtTwitterLogin;
    // 페이스북
    private CallbackManager mCallbackManager;
    @Bind(R.id.bt_login_facebook_login)
    LoginButton mBtFacebookLogin;
    // 구글
    GoogleLogin mGoogleLogin;
    @Bind(R.id.bt_login_google_login)
    SignInButton mBtGoogleLogin;
    // 네이버
    NaverLogin mNaverLogin;
    @Bind(R.id.bt_login_naver_login)
    OAuthLoginButton mBtNaverLogin;
    // 다음
    DaumLogin mDaumLogin;
    // 카카오
    KakaoLogin mKakaoLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(getActivity(), new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getActivity());
        mView = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, mView);

        // 트위터
        TwitterLogin twitterLogin = new TwitterLogin(getActivity());
        mBtTwitterLogin.setCallback(twitterLogin.twitterCallback);

        // 페이스북
        FacebookLogin facebookLogin = new FacebookLogin(getActivity());
        mCallbackManager = CallbackManager.Factory.create();
        mBtFacebookLogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        mBtFacebookLogin.registerCallback(mCallbackManager, facebookLogin.facebookCallback);

        // 구글
        mGoogleLogin = new GoogleLogin(getActivity());
        mBtGoogleLogin.setSize(SignInButton.SIZE_STANDARD);
        mBtGoogleLogin.setScopes(mGoogleLogin.mGso.getScopeArray());

        // 네이버
        mNaverLogin = new NaverLogin(getActivity());
        mNaverLogin.initData();
        mBtNaverLogin.setOAuthLoginHandler(mNaverLogin.mOAuthLoginHandler);

        // 다음
        mDaumLogin = new DaumLogin(getActivity());
        mDaumLogin.onInit();

        // 카카오
        mKakaoLogin = new KakaoLogin(getActivity());
        mKakaoLogin.onInit();

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleLogin.mGoogleApiClient.connect();
        mGoogleLogin.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleLogin.mGoogleApiClient.disconnect();
    }

    @OnClick({R.id.bt_login_main, R.id.bt_login_kakao, R.id.bt_login_naver, R.id.bt_login_daum, R.id.bt_login_google, R.id.bt_login_twitter, R.id.bt_login_facebook, R.id.bt_login_google_login,
            R.id.bt_login_naver_login, R.id.bt_login_daum_login, R.id.bt_login_finish})
    void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.bt_login_main:
                startFragment(MainFragment.class);
                break;

            case R.id.bt_login_kakao:
                intent = new Intent(getActivity(), KakaoLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_naver:
                intent = new Intent(getActivity(), NaverLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_daum:
                intent = new Intent(getActivity(), DaumLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_google:
                intent = new Intent(getActivity(), GoogleLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_twitter:
                intent = new Intent(getActivity(), TwitterLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_facebook:
                intent = new Intent(getActivity(), FacebookLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login_google_login:
                mGoogleLogin.signIn();
                break;

            case R.id.bt_login_naver_login:
                mNaverLogin.login();
                break;

            case R.id.bt_login_daum_login:
                MobileOAuthLibrary.getInstance().authorize(getActivity(), mDaumLogin.oAuthListener);
                break;

            case R.id.bt_login_finish:
                finishFragment();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 트위터
        final TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        if (requestCode == twitterAuthClient.getRequestCode()) {
            mBtTwitterLogin.onActivityResult(requestCode, resultCode, data);
        }
        // 페이스북
        else if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        // 구글
        else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mGoogleLogin.handleSignInResult(result);
        }
        // 카카오
        Log.e("requestCode", "" + requestCode);
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mKakaoLogin.removeCallback();
    }
}