package com.example.kamal.bmicalculator.model;

import com.example.kamal.bmicalculator.Utils;

public class BodyJava {
    private String heightText;
    private String weightText;
    private Double bmi;

    public BodyJava(String heightInCentimeterText, String weightInKgText) {
        this.heightText = heightInCentimeterText;
        this.weightText = weightInKgText;

        calcBmi();
    }

    public String getHeightText() {
        return heightText;
    }

    public String getWeightText() {
        return weightText;
    }

    public void setHeightText(String heightText) {
        this.heightText = heightText;

        calcBmi();
    }

    public void setWeightText(String weightText) {
        this.weightText = weightText;

        calcBmi();
    }

    public Double getBmi() {
        return bmi;
    }

    private void calcBmi() {
        if (this.heightText.isEmpty() || this.weightText.isEmpty()) {
            return;
        }

        double heightInCentimeter = Double.valueOf(this.heightText);
        double weightInKg = Double.valueOf(this.weightText);

        double heightInMeter = Utils.INSTANCE.centimeterToMeter(heightInCentimeter);
        bmi = Utils.INSTANCE.getBmiValue(heightInMeter, weightInKg);
    }
}
