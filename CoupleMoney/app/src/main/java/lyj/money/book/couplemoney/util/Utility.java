package lyj.money.book.couplemoney.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


@SuppressLint("WorldReadableFiles")
public class Utility {

    public static String notNullString(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    public static String notNullString(Object str) {
        if (str == null) {
            return "";
        } else {
            return str.toString();
        }
    }

    public static Object notNullObject(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj;
        }
    }

    public static boolean isEmptyString(String str) {
        return notNullString(str).equals("") || notNullString(str).equals("null") || notNullString(str).equals(null) || str.trim().length() == 0;
    }

    public static boolean isEmptyString(CharSequence str) {
        return isEmptyString(str.toString());
    }

    public static boolean isEmptyObject(Object obj) {
        return notNullObject(obj).equals("") || notNullObject(obj).equals(null);
    }

    /**
     * String Split
     *
     * @param str
     * @param format
     * @return
     */
    public static String[] stringSplit(String str, String format) {
        if (isEmptyString(str) || isEmptyString(format)) {
            return null;
        }

        String[] array;
        array = str.split(format);

        if (array.length == 0) {
            return null;
        }

        return array;

    }

    /**
     * 키보드 강제로 띄우기
     *
     * @param context
     */
    public static void showKeyBoard(Context context) {
        if (isEmptyObject(context)) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else {
            Log.e("Utility", "context null");
        }
    }

    /**
     * 키보드 강제로 내리기
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        if (!Utility.isEmptyObject(activity)) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            Log.e("Utility", "activity null");
        }
    }

    /**
     * money comma
     *
     * @param dblMoneyString
     * @return
     */
    public static String makeMoneyType(String dblMoneyString) {
        if (isEmptyString(dblMoneyString)) {
            return "0";
        }

        String format = "#,###.###";
        DecimalFormat df = new DecimalFormat(format);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();

        dfs.setGroupingSeparator(',');
        df.setGroupingSize(3);
        df.setDecimalFormatSymbols(dfs);

        String result = (df.format(Double.parseDouble(dblMoneyString))).toString();

        return result;
    }
}
