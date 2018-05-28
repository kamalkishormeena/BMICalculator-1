package com.example.kamal.bmicalculator.model

import com.example.kamal.bmicalculator.Utils

// Not being used, because data binding can not find setters & getters in Kotlin class
data class Body(val heightText: String, val weightText: String) {
    var bmi: Double? = null

    set(value) {
        calcBmi()
    }

    private fun calcBmi() {
        if (this.heightText.isEmpty() || this.weightText.isEmpty()) {
            return
        }

        val heightInCentimeter = java.lang.Double.valueOf(this.heightText)!!
        val weightInKg = java.lang.Double.valueOf(this.weightText)!!

        val heightInMeter = Utils.centimeterToMeter(heightInCentimeter)
        bmi = Utils.getBmiValue(heightInMeter, weightInKg)
    }
}