package edu.clarkson.ee408project;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class GetCardsActivity extends AppCompatActivity {
    private DBManager dbManager;
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DBManager( this );
        setContentView( R.layout.getcards_main );
        Toolbar toolbar = findViewById(R.id.getcardsToolbar);
        setSupportActionBar(toolbar);
        updateView( );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secondary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_newCard) {
            Intent addCardIntent = new Intent(this, MainActivity.class);
            this.startActivity(addCardIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    public void updateView( ) {
        ArrayList<PreviewPerson> people = dbManager.getPreviewPeople();
        if( people.size( ) > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( people.size( ) );
            grid.setColumnCount( 4 );

            // create arrays of components
            TextView[] ids = new TextView[people.size( )];
            EditText[][] namesAndNumbers = new EditText[people.size( )][2];
            Button[] buttons = new Button[people.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            int i = 0;

            for ( PreviewPerson person : people ) {
                // create the TextView for the entry
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + person.name );

                // create the two EditTexts for the person's name and card number
                namesAndNumbers[i][0] = new EditText( this );
                namesAndNumbers[i][1] = new EditText( this );
                namesAndNumbers[i][0].setText( person.name );
                namesAndNumbers[i][1].setText( "" + person.number);
                namesAndNumbers[i][1].setInputType( InputType.TYPE_CLASS_NUMBER );
                namesAndNumbers[i][0].setId( Integer.parseInt(person.number) );
                namesAndNumbers[i][1].setId( Integer.parseInt(person.number) );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( Integer.parseInt(person.number) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndNumbers[i][0], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndNumbers[i][1], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .35 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

     */
    public void updateView( ) {
        ArrayList<PreviewPerson> people = dbManager.getPreviewPeople();
        if (people.size() > 0) {
            // remove subviews inside scrollView if necessary
            ScrollView scrollView = new ScrollView( this );
            scrollView.removeAllViewsInLayout();

            // set up the grid layout
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((people.size() + 1));
            grid.setColumnCount(1);

            // create array of buttons, 1 per row
            PeopleButton[] buttons = new PeopleButton[people.size()];
            ButtonHandler bh = new ButtonHandler();

            // fill the grid
            int i = 0;
            Point size = new Point( );
            int buttonWidth = size.x / 2;

            for (PreviewPerson person : people) {
                // create the button
                buttons[i] = new PeopleButton(this, person);
                buttons[i].setText(person.name + "\n" + person.number);

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the button to grid
                grid.addView(buttons[i], buttonWidth,
                        GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve name and price of the candy
            int personId = v.getId( );
            EditText nameET = ( EditText ) findViewById( personId );
            EditText numberET = ( EditText ) findViewById( personId );
            String name = nameET.getText( ).toString( );
            String numString = numberET.getText( ).toString( );

            // update candy in database
            try {
                double cardNum = Double.parseDouble( numString );
                dbManager.updateCardByName( name, cardNum );
                Toast.makeText( GetCardsActivity.this, "Entry updated",
                        Toast.LENGTH_SHORT ).show( );

                // update screen
                updateView( );
            } catch( NumberFormatException nfe ) {
                Toast.makeText( GetCardsActivity.this,
                        "Error", Toast.LENGTH_LONG ).show( );
            }

        }
    }
}
