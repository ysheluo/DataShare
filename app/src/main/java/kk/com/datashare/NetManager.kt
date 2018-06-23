package kk.com.datashare

class NetManager {

    companion object {


        fun <T> query(url: String, param: String, iCallBack: ICallBack<T>) {
            Thread(Runnable {
                Thread.sleep(10000)
                iCallBack.onSuccess("可以啦" as T)
            }).start()
        }

    }


    interface ICallBack<T> {


        fun onSuccess(data: T)
    }

}