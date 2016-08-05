package lyj.money.book.couplemoney.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.common.BaseFragment;

public class LoginFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, mView);

        return mView;
    }

    @OnClick({R.id.bt_login, R.id.bt_login_finish})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                startFragment(getFragmentManager(), MainFragment.class);
                break;

            case R.id.bt_login_finish:
                finishFragment();
                break;
        }
    }
}