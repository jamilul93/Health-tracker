package com.example.heathmonitor;

public class ModelClass {
    String date="";
    String systolic="";
    String diastolic="";
    String bloodPressure="";
    String comment="";

    public ModelClass(String date, String systolic, String diastolic, String bloodPressure) {
        this.date = date;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.bloodPressure = bloodPressure;
    }
    public String getDate() {
        return date;
    }

    public String getSystolic() {
        return systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }







}
