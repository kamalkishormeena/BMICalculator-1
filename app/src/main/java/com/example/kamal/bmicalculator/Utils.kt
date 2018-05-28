package com.example.kamal.bmicalculator

import android.content.Context

object Utils {

    private const val CENTIMETERS_PER_METER = 100

    fun getBmiText(context: Context, x: Double?): String {
        if (x == null) {
            return ""
        }

        val string = context.getString(R.string.your_bmi_is, x);
        val desc : String

        if (x < 18.5) {
            desc = context.getString(R.string.bmi_underweight)
        } else if (x < 24.9) {
            desc = context.getString(R.string.bmi_normal_weight)
        } else if (x < 29.9) {
            desc = context.getString(R.string.bmi_overweight)
        } else {
            desc = context.getString(R.string.bmi_obesity)
        }

        return string + ", " + desc;
    }

    fun getBmiValue(heightInMeter: Double, weightInKg: Double): Double {
        if (heightInMeter == 0.0) {
            return 0.0
        }
        return weightInKg / heightInMeter / heightInMeter
    }

    fun centimeterToMeter(centimeters: Double): Double {
        return centimeters / CENTIMETERS_PER_METER
    }

    fun inchToMeter(feet: Double?, inches: Double?): Double {
        val feetNonNull = feet ?: 0.0
        val inchesNonNull = inches ?: 0.0
        val length = feetNonNull * 12 + inchesNonNull

        return length * 0.0254
    }

    fun poundToKg(weightInPounds: Double): Double {
        return weightInPounds * 0.453592
    }

}