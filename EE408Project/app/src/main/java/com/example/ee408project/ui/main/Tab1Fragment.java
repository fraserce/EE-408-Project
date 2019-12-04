package com.example.ee408project.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.ee408project.Person;
import com.example.ee408project.R;

public class Tab1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void insert(){
        EditText fName = (EditText) getView().findViewById(R.id.first_name);
        String fn = fName.getText().toString();
        EditText lName = (EditText) getView().findViewById(R.id.last_name);
        String ln = lName.getText().toString();
        EditText street1 = (EditText) getView().findViewById(R.id.address_1);
        String s1 = street1.getText().toString();
        EditText street2 = (EditText) getView().findViewById(R.id.address_2);
        String s2 = street2.getText().toString();
        EditText city = (EditText) getView().findViewById(R.id.city);
        String cityString = city.getText().toString();
        Spinner state = (Spinner) getView().findViewById(R.id.state_spinner);
        String stateString = state.getSelectedItem().toString();
        EditText zip = (EditText) getView().findViewById(R.id.zip_code);
        String zipString = zip.getText().toString();
        Spinner country = (Spinner) getView().findViewById(R.id.country_spinner);
        String countryString = country.getSelectedItem().toString();

        Spinner ctype = (Spinner) getView().findViewById(R.id.card_type_spinner);
        String ctypeString = ctype.getSelectedItem().toString();
        EditText number = (EditText) getView().findViewById(R.id.card_num);
        String numberString = number.getText().toString();
        EditText cvc = (EditText) getView().findViewById(R.id.cvc);
        String cvcString = cvc.getText().toString();
        EditText expirationM = (EditText) getView().findViewById(R.id.month);
        String expirationMString = expirationM.getText().toString();
        EditText expirationY = (EditText) getView().findViewById(R.id.year);
        String expirationYString = expirationY.getText().toString();

        try{
            Person person = new Person(fn,ln, s1, s2, cityString, stateString, zipString, countryString);
            person.updateCard(ctypeString, numberString, cvcString, expirationMString, expirationYString);
        } catch(Exception e){

        }
    }

}

