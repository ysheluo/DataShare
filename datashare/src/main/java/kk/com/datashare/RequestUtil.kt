package kk.com.datashare

fun <T> shareSuccess(key: Int, data: T, cause: String, tag: String) {
    DataShareManager.getContainerByKey(key).getDataHolder<T>(tag).onSetSuccessData(data, cause)
}

fun shareFail(key: Int, failResult: String, cause: String, tag: String) {
    DataShareManager.getContainerByKey(key).getDataHolder<Any>(tag).onFail(failResult, cause)
}

fun <T> listen(key: Int, tag: String): IListener<T> {
    return DataShareManager.getContainerByKey(key).listen(tag)
}

fun <T> result(key: Int, tag: String): T? {
    return DataShareManager.getContainerByKey(key).listen<T>(tag).result(tag)
}

fun destroyShareContainer(key: Int) {
    DataShareManager.destroy(key)
}