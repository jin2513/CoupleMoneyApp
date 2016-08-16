package lyj.money.book.couplemoney.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import lyj.money.book.couplemoney.R;
import lyj.money.book.couplemoney.util.Utility;

public class BaseFragment extends Fragment {
    public View mView;

    protected void startFragment(Class<? extends BaseFragment> fragmentClass) {
        BaseFragment fragment = null;
        try {
            fragment = fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Utility.isEmptyObject(fragment)) {
            throw new IllegalStateException("cannot start fragment. " + fragmentClass.getName());
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_base_fragment, fragment).addToBackStack(null).commit();
//        fm.beginTransaction().add(R.id.fl_activity_base_fragment, fragment).addToBackStack(null).commit();
    }

    protected void finishFragment() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}