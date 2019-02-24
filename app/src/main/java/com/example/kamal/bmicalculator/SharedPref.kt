package com.example.kamal.bmicalculator


import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    internal var mySharedPref: SharedPreferences
    internal var colorChange: SharedPreferences

    init {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE)
        colorChange = context.getSharedPreferences("filename", Context.MODE_PRIVATE)

    }

    // this method will save the nightMode State : True or False
    fun setNightModeState(state: Boolean?) {
        val editor = mySharedPref.edit()
        editor.putBoolean("NightMode", state!!)
        editor.commit()
    }

    // this method will load the Night Mode State
    fun loadNightModeState(): Boolean? {
        return mySharedPref.getBoolean("NightMode", false)
    }

    //Red Color
    fun setColorRed(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Red", state!!)
        editor.commit()
    }

    fun loadColorRed(): Boolean? {
        return colorChange.getBoolean("Red", false)
    }

    //Default Color
    fun setDefaultColor(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Default", state!!)
        editor.commit()
    }

    fun loadDefaultColor(): Boolean? {
        return colorChange.getBoolean("Default", false)
    }

    //Orange Color
    fun setColorOrange(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Orange", state!!)
        editor.commit()
    }

    fun loadColorOrange(): Boolean? {
        return colorChange.getBoolean("Orange", false)
    }

    //Sky Color
    fun setColorSky(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Sky", state!!)
        editor.commit()
    }

    fun loadColorSky(): Boolean? {
        return colorChange.getBoolean("Sky", false)
    }

    //Green Color
    fun setColorGreen(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Green", state!!)
        editor.commit()
    }

    fun loadColorGreen(): Boolean? {
        return colorChange.getBoolean("Green", false)
    }

    //Pink Color
    fun setColorPink(state: Boolean?) {
        val editor = colorChange.edit()
        editor.putBoolean("Pink", state!!)
        editor.commit()
    }

    fun loadColorPink(): Boolean? {
        return colorChange.getBoolean("Pink", false)
    }
}