package kk.com.datashare

import java.util.concurrent.atomic.AtomicInteger

/**
 * 连接data和Lister的桥梁
 */
class DataManager {


    private val dataHolders: MutableMap<String, IDataHolder<*>> = mutableMapOf()


    companion object {

        var dataHolderFactory: IDataHolderFactory? = null

        private val blockDataManagers: MutableMap<Int, DataManager> = mutableMapOf()

        fun setFactory(factory: IDataHolderFactory) {

            dataHolderFactory = factory
        }

        @Synchronized
        private fun getDataManager(pageKey: Int): DataManager {
            var dataManager = blockDataManagers[pageKey]
            if (dataManager == null) {
                dataManager = DataManager()
                blockDataManagers[pageKey] = dataManager
            }
            return dataManager
        }

        fun <T> request(pageKey: Int, requester: IRequesterImpl<T>): DataManager {
            return getDataManager(pageKey).request(requester)
        }


        fun <T> request(pageKey: Int, vararg requesters: IRequesterImpl<T>): DataManager {
            return getDataManager(pageKey).request(*requesters)
        }


        fun <T> listen(pageKey: Int, listener: IListener<T>): DataManager {
            return getDataManager(pageKey).listen(listener)
        }

        fun createKey(): Int {
            return AtomicInteger().incrementAndGet()
        }

        fun destroy(createKey: Int) {
            val dataManager = getDataManager(createKey)
            dataManager.dataHolders.clear()
            blockDataManagers.remove(createKey)
        }

    }


    fun <T> request(vararg requester: IRequesterImpl<T>): DataManager {
        requester.forEach {
            request(it)
        }
        return this
    }


    fun <T> request(requester: IRequesterImpl<T>): DataManager {
        var dataHolder = getDataHolder<T>(requester.key())
        requester.bindDataHolder(dataHolder)
        requester.doRequest()
        return this
    }

    @Synchronized
    private fun <T> getDataHolder(key: String): IDataHolder<T> {
        var iDataHolder = dataHolders[key]
        if (iDataHolder == null) {

            iDataHolder = if (dataHolderFactory == null) {
                IDataHolderImpl<T>(key)
            } else {
                dataHolderFactory!!.createDataHolder()
            }
            dataHolders[key] = iDataHolder
        }
        return iDataHolder as IDataHolder<T>
    }


    fun <T> listen(listener: IListener<T>): DataManager {
        getDataHolder<T>(listener.key()).registerListener(listener)
        return this
    }


}