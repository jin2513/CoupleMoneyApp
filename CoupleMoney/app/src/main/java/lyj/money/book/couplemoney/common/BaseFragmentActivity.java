package lyj.money.book.couplemoney.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.view.LoginFragment;
import lyj.money.book.couplemoney.view.MainFragment;

public class BaseFragmentActivity extends FragmentActivity {

    private ActivityManager actManager = ActivityManager.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);

        onInit();

    }

    private void onInit() {
        setIntentData();
    }

    private void setIntentData() {
        Intent intent = getIntent();
        int fragmentType = intent.getIntExtra(EventCode.KeyType.FRAGMENT.key, 0);

        // 로그인 화면
        if (fragmentType == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_base_fragment, new LoginFragment(), "LoginFragment").commit();
//            setFragment(new LoginFragment(), null);
        }
        // 메인 화면
        else if (fragmentType == EventCode.FragmentType.MAIN.nId) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_base_fragment, new MainFragment()).commit();
        }
    }

    private void setFragment(Fragment mFragment, Bundle bundle) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (bundle != null) {
                mFragment.setArguments(bundle);
            }

            ft.add(R.id.fl_activity_base_fragment, mFragment);
//            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentManager fragment = getSupportFragmentManager();
        if (fragment != null) {
            fragment.findFragmentByTag("LoginFragment").onActivityResult(requestCode, resultCode, data);
        } else Log.d("Twitter", "fragment is null");
    }
}
