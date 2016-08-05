package lyj.money.book.couplemoney.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.common.BaseFragmentActivity;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, BaseFragmentActivity.class);
//                intent.putExtra(EventCode.KeyType.FRAGMENT.key, EventCode.FragmentType.LOGIN.nId);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}