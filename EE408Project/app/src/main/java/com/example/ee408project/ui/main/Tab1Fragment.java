package com.example.ee408project.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public void insert( ){

        EditText fName = (EditText) getView().findViewById(R.id.first_name);
        String fn = fName.getText().toString();
        EditText lName = (EditText) getView().findViewById(R.id.last_name);
        String ln = lName.getText().toString();
        EditText street1 = (EditText) getView().findViewById(R.id.address_1);
        EditText street2 = (EditText) getView().findViewById(R.id.address_2);
        EditText city = (EditText) getView().findViewById(R.id.city);
        Spinner state = (Spinner) getView().findViewById(R.id.state_spinner);
        EditText zip = (EditText) getView().findViewById(R.id.zip_code);

        Spinner ctype = (Spinner) getView().findViewById(R.id.card_type_spinner);
        EditText number = (EditText) getView().findViewById(R.id.card_num);
        EditText cvc = (EditText) getView().findViewById(R.id.cvc);
        EditText expirationM = (EditText) getView().findViewById(R.id.month);
        EditText expirationY = (EditText) getView().findViewById(R.id.year);

        try{
            Person person = new Person(fn,ln,)
        } catch( Exception e){

        }
    }

}

