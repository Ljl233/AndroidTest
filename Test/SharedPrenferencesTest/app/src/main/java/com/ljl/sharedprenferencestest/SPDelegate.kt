package com.ljl.sharedprenferencestest

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

//inline fun <reified R, T> R.pref(context: Context, default: T) = SPDelegate(context, "")
class SPDelegate<T>(
        private val context: Context,
        private val defaultValue: T,
        private val defaultSpName: String = "sp",
        private val defaultMode: Int = Context.MODE_PRIVATE) {

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(defaultSpName, defaultMode)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val value = when (defaultValue) {
            is Boolean -> sp.getBoolean(property.name, defaultValue)
            is Int -> sp.getInt(property.name, defaultValue)
            is Float -> sp.getFloat(property.name, defaultValue)
            is Long -> sp.getLong(property.name, defaultValue)
            is String -> sp.getString(property.name, defaultValue)
            else -> throw IllegalArgumentException("Unsupported type.")
        }

        return value as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(sp.edit()) {
            when (value) {
                is Boolean -> putBoolean(property.name, value)
                is Int -> putInt(property.name, value)
                is Float -> putFloat(property.name, value)
                is Long -> putLong(property.name, value)
                is String -> putString(property.name, value)
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }.apply()
    }
}

//abstract class AbsSharedPreference(context: Context, defaultValue: String) {
//}

class Config(context: Context) {

    val sp = SPDelegate(context, "defaultValue")
    var username by sp
    var password by sp

}
