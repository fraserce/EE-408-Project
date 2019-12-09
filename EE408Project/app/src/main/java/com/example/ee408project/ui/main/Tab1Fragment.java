package com.example.ee408project.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.ee408project.DBManager;
import com.example.ee408project.MainActivity;
import com.example.ee408project.Person;
import com.example.ee408project.R;



public class Tab1Fragment extends Fragment {
    private  DBManager db;
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = new DBManager(getActivity());
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.mView = view;
        Button button = (Button) mView.findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    EditText fName = (EditText) mView.findViewById(R.id.first_name);
                    String fn = fName.getText().toString();
                    EditText lName = (EditText) mView.findViewById(R.id.last_name);
                    String ln = lName.getText().toString();
                    EditText street1 = (EditText) mView.findViewById(R.id.address_1);
                    String s1 = street1.getText().toString();
                    EditText street2 = (EditText) mView.findViewById(R.id.address_2);
                    String s2 = street2.getText().toString();
                    EditText city = (EditText) mView.findViewById(R.id.city);
                    String cityString = city.getText().toString();
                    Spinner state = (Spinner) mView.findViewById(R.id.state_spinner);
                    String stateString = state.getSelectedItem().toString();
                    EditText zip = (EditText) mView.findViewById(R.id.zip_code);
                    String zipString = zip.getText().toString();
                    Spinner country = (Spinner) mView.findViewById(R.id.country_spinner);
                    String countryString = country.getSelectedItem().toString();

                    Spinner ctype = (Spinner) mView.findViewById(R.id.card_type_spinner);
                    String ctypeString = ctype.getSelectedItem().toString();
                    EditText number = (EditText) mView.findViewById(R.id.card_num);
                    String numberString = number.getText().toString();
                    EditText cvc = (EditText) mView.findViewById(R.id.cvc);
                    String cvcString = cvc.getText().toString();
                    EditText expirationM = (EditText) mView.findViewById(R.id.month);
                    String expirationMString = expirationM.getText().toString();
                    EditText expirationY = (EditText) mView.findViewById(R.id.year);
                    String expirationYString = expirationY.getText().toString();

                try{
                    Person person = new Person(fn,ln, s1, s2, cityString, stateString, zipString, countryString);
                    person.updateCard(ctypeString, numberString, cvcString, expirationMString, expirationYString);
                    db.insertPerson(person);

                } catch(Exception e){

                }
            }
        });
        return view;
    }


}

