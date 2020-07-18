package com.example.covid19;

class COVIDBlog {
    String tName,tAge,tArea,tCity,tCountry,tAdditional,tAdmit,tDis,tDiet,tMedicine,tDisease,tHname,tHdoctor,tState,tRecover,currDisease,key;

    public COVIDBlog() {
    }

    public COVIDBlog(String tName, String currDisease, String tAge, String tCountry, String tState, String tCity, String tArea, String tDiet, String tMedicine, String tDisease, String tHname, String tHdoctor, String tAdmit, String tDis,String tAdditional,String tRecover,String key) {
        this.tName = tName;
        this.currDisease = currDisease;
        this.tAge = tAge;
        this.tArea = tArea;
        this.tCity = tCity;
        this.tCountry = tCountry;
        this.tAdditional = tAdditional;
        this.tAdmit = tAdmit;
        this.tDis = tDis;
        this.tDiet = tDiet;
        this.tMedicine = tMedicine;
        this.tDisease = tDisease;
        this.tHname = tHname;
        this.tHdoctor = tHdoctor;
        this.tState = tState;
        this.tRecover = tRecover;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCurrDisease() {
        return currDisease;
    }

    public void setCurrDisease(String currDisease) {
        this.currDisease = currDisease;
    }

    public String gettName() {
        return tName;
    }

    public String gettAge() {
        return tAge;
    }

    public String gettArea() {
        return tArea;
    }

    public String gettCity() {
        return tCity;
    }

    public String gettCountry() {
        return tCountry;
    }

    public String gettAdditional() {
        return tAdditional;
    }

    public String gettAdmit() {
        return tAdmit;
    }

    public String gettDis() {
        return tDis;
    }

    public String gettDiet() {
        return tDiet;
    }

    public String gettMedicine() {
        return tMedicine;
    }

    public String gettDisease() {
        return tDisease;
    }

    public String gettHname() {
        return tHname;
    }

    public String gettHdoctor() {
        return tHdoctor;
    }

    public String gettState() {
        return tState;
    }

    public String gettRecover() {
        return tRecover;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public void settAge(String tAge) {
        this.tAge = tAge;
    }

    public void settArea(String tArea) {
        this.tArea = tArea;
    }

    public void settCity(String tCity) {
        this.tCity = tCity;
    }

    public void settCountry(String tCountry) {
        this.tCountry = tCountry;
    }

    public void settAdditional(String tAdditional) {
        this.tAdditional = tAdditional;
    }

    public void settAdmit(String tAdmit) {
        this.tAdmit = tAdmit;
    }

    public void settDis(String tDis) {
        this.tDis = tDis;
    }

    public void settDiet(String tDiet) {
        this.tDiet = tDiet;
    }

    public void settMedicine(String tMedicine) {
        this.tMedicine = tMedicine;
    }

    public void settDisease(String tDisease) {
        this.tDisease = tDisease;
    }

    public void settHname(String tHname) {
        this.tHname = tHname;
    }

    public void settHdoctor(String tHdoctor) {
        this.tHdoctor = tHdoctor;
    }

    public void settState(String tState) {
        this.tState = tState;
    }

    public void settRecover(String tRecover) {
        this.tRecover = tRecover;
    }
}
