package lyj.money.book.couplemoney.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.view.LoginFragment;
import lyj.money.book.couplemoney.view.MainFragment;

public class BaseFragmentActivity extends FragmentActivity {

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

        if (intent.getIntExtra(EventCode.KeyType.FRAGMENT.key, 0) == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_base_fragment, new LoginFragment()).commit();
//            setFragment(new LoginFragment(), null);
        }
        // 자동 로그인
        else {
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
}
