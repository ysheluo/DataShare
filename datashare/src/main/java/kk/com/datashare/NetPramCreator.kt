package kk.com.datashare

abstract class NetPramCreator(var createCause:String){

    abstract fun key(): String

    abstract fun provideParam(): String

    fun createCause():String{
        return createCause
    }

}
