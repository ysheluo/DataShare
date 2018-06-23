package kk.com.rxjava;

import android.annotation.SuppressLint;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class NetWorkRxAdapter {


    private String url;


    private String param;


    private String type;


    public static NetWorkRxAdapter newInstance() {

        return new NetWorkRxAdapter();
    }


    public NetWorkRxAdapter setUrl(String url) {
        this.url = url;
        return this;
    }

    public NetWorkRxAdapter setParam(String param) {
        this.param = param;
        return this;
    }

    public NetWorkRxAdapter setType(String type) {
        this.type = type;
        return this;
    }


    public void setParam(JSONObject jsonObject) {


    }

    public NetWorkRxAdapter setParam(BaseBean baseBean) {


        return this;
    }


    @SuppressLint("CheckResult")
    public <Response> Observable<Response> request(Consumer<Response> responseConsumer) {
        Observable<Response> tObservable = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(final ObservableEmitter<Response> emitter) throws Exception {


                NetWorkHelper.doWork(param, url, new NetWorkHelper.ICallBack<String>() {
                    @Override
                    public void onCall(String data) {
                        emitter.onNext((Response) data);
                        emitter.onComplete();
                    }
                });


            }
        }).subscribeOn(AndroidSchedulers.mainThread());
        tObservable.subscribe(responseConsumer);
        return tObservable;
    }


}
