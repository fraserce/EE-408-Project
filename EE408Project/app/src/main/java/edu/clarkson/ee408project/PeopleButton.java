package edu.clarkson.ee408project;

import android.content.Context;
import android.widget.Button;

class PeopleButton extends Button {
    private PreviewPerson person;

    public PeopleButton(Context context, PreviewPerson newPerson ) {
        super( context );
        person = newPerson;
    }

    public String getCardNum( ) {
        return person.number;
    }
}
