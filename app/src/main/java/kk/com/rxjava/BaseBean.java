package kk.com.rxjava;

public class BaseBean<RealInfo> {

    public BaseBean(RealInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    private RealInfo resultInfo;

    public RealInfo getResultInfo() {
        return resultInfo;
    }
}
