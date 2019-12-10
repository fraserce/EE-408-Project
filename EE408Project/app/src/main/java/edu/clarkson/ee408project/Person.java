package edu.clarkson.ee408project;

public class Person {

    //Personal Details
    public String name;
    public String zip;
    public String country_code;
    public String p_number;

    // Card Details
    public String card_type;
    public String number;
    public String cvc;
    public String expiration_m;
    public String expiration_y;


    // Default Constructor

    public Person(){
        this.name = "NOT SET";
    }

    // Constructor
    
    public Person(String n, String zip, String country_code, String p_number){
        this.name = n;
        this.country_code = country_code;
        this.p_number = p_number;
        this.zip = zip;
    }

    // This is a storage container class - information is readily available and editable using . notation, but functions added for ease of manipulation

    public void updateCard(String type, String num, String cvc, String expm, String expy){
        this.card_type = type;
        this.number = num;
        this.cvc = cvc;
        this.expiration_m = expm;
        this.expiration_y = expy;
    }




}
