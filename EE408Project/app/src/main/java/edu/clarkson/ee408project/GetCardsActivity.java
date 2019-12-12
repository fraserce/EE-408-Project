package edu.clarkson.ee408project;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;

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
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateView( ) {
        ArrayList<PreviewPerson> people = dbManager.getPreviewPeople();
        if( people.size( ) > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( people.size( ) );
            grid.setColumnCount( 1 );

            // create arrays of components
            PeopleButton[] buttons = new PeopleButton[people.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            int i = 0;

            for ( PreviewPerson person : people ) {

                // create the button
                buttons[i] = new PeopleButton( this, person );
                buttons[i].setText("\nName: " + person.name + "\nCard Number: " + person.number + "\n" );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( buttons[i], ( int ) ( width  ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

    public class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            String personId = ((PeopleButton) v).getCardNum();
            Person person = dbManager.getPersonByCard(personId);

            Intent intent = new Intent(getBaseContext(), PopUp.class);
            intent.putExtra("PERSONID", personId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(intent);
        }
    }
}
