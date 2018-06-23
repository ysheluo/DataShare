package kk.com.rxjava;

import android.text.TextUtils;

public class NetWorkHelper {

    static int i = 0;

    public static <T> void doWork(String param, String url, ICallBack<T> iCallBack) {
        if (i == 0) {
            iCallBack.onCall((T) "lllll");
            i = 1;
        } else {
            iCallBack.onCall((T) "独孤求败");
            i = 0;
        }
    }


    public interface ICallBack<T> {

        void onCall(T data);

    }

}
