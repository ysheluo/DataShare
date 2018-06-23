package kk.com.rxjava;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });


        RecyclerView recyclerView;


        List<String> list=new ArrayList<>();




        NetWorkRxAdapter.newInstance().setUrl("").setParam(new BaseBean<>(new B(""))).request(new Consumer<String>() {
            @Override
            public void accept(String a) throws Exception {
                Log.e("=========", "呀呀呀" + a);
            }
        }).compose(new ObservableTransformer<String, String>() {
            @Override
            public ObservableSource<String> apply(Observable<String> upstream) {
                Log.e("=========", "啦啦啦啦啦了" + Thread.currentThread());

                return upstream;
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).compose(new ObservableTransformer<String, String>() {
            @Override
            public ObservableSource<String> apply(Observable<String> upstream) {
                return NetWorkRxAdapter.newInstance().setUrl("").setParam(new BaseBean<>(new B(""))).request(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("=========", "啦啦啦啦啦了" + s);
                    }
                });
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.e("=========", "22222222222");
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.e("=========", "99999999999");
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                Log.e("=========", "finallly");
            }
        });


    }


    public static class B {
        private final String hahha;

        public B(String hahha) {
            this.hahha = hahha;
        }
    }


    public static class A {
        public String a;

        public A(String a) {
            this.a = a;
        }
    }


}
