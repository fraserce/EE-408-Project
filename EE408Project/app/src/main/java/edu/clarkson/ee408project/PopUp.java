package edu.clarkson.ee408project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);
        DBManager db = new DBManager(this);
        String cardNum = getIntent().getStringExtra("PERSONID");
        Person person = db.getPersonByCard(cardNum);
        popUp(person);
    }


    public void popUp(Person person) {
        TextView name = (TextView) findViewById(R.id.Name);
        name.setText("Name: " + person.name);

        TextView zip = (TextView) findViewById(R.id.Zip);
        zip.setText("Zip Code: " + person.zip);

        TextView cCode = (TextView) findViewById(R.id.CountryCode);
        cCode.setText("Country Code: " + person.country_code);

        TextView phoneNum = (TextView) findViewById(R.id.PhoneNumber);
        phoneNum.setText("Phone Number: " + person.p_number);

        TextView cardType = (TextView) findViewById(R.id.CardType);
        cardType.setText("Card Type: " + person.card_type);

        TextView cardNum = (TextView) findViewById(R.id.CardNumber);
        cardNum.setText("Card Number: " + person.number);

        TextView cvc = (TextView) findViewById(R.id.CVC);
        cvc.setText("CVC: " + person.cvc);

        TextView exp = (TextView) findViewById(R.id.Expiration);
        exp.setText("Expiration: " + person.expiration_m + "/" + person.expiration_y);

        Button backButton = (Button) findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}