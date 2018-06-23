package kk.com.rxjava

import android.text.TextUtils


fun TextUtils.isA(str: String): Boolean {


    return "null" == str || "" == str
}


fun String.tttttt(str: Any?): String {
    return this.replace(' ', '_')
}