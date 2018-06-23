package kk.com.datashare

interface IDataHolderFactory {

    fun createDataHolder(): IDataHolder<*>
}