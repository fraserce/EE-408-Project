package edu.clarkson.ee408project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "creditInfo";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_PEOPLE = "people";



    private static final String NAME = "firstname";
    private static final String ID = "firstname";
    private static final String ZIP = "zip";
    private static final String COUNTRY_CODE = "country_code";
    private static final String P_NUMBER = "phone_number";


    private static final String CARD_TYPE = "card_type";
    private static final String NUMBER = "card_number";
    private static final String CVC = "cvc";
    private static final String EXPIRATION_M = "expiration_m";
    private static final String EXPIRATION_Y = "expiration_y";


    public DBManager(Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db){
        String create = "create table " + TABLE_PEOPLE + "( " + NAME + " text, ";
        create += ZIP + " text, " + COUNTRY_CODE + " text, " + P_NUMBER + " text, ";
        create += CARD_TYPE + " text, " + NUMBER + " text, ";
        create += CVC + " text, " + EXPIRATION_M + " text, " + EXPIRATION_Y + " text )";
        db.execSQL(create);
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL( "drop table if exists " + TABLE_PEOPLE);
        onCreate( db );
    }

    // Takes an object of class "Person" and inserts it into the database
    public void insertPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_PEOPLE;
        sqlInsert += " values('" + person.name + "', '" + person.zip + "', '";
        sqlInsert += person.country_code + "', '" + person.p_number + "', '" + person.card_type + "', '" + person.number + "', '";
        sqlInsert += person.cvc + "', '" + person.expiration_m + "', '" + person.expiration_y + "' )" ;

        db.execSQL( sqlInsert );
        db.close();
    }

    // Returns a list of "PreviewPerson" objects consisting of people's names and credit number
    public ArrayList<PreviewPerson> getPreviewPeople(){
        String sqlQuery = "select " + NAME + "," + NUMBER + "from " + TABLE_PEOPLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<PreviewPerson> people = new ArrayList<>();
        while( cursor.moveToNext()){
            PreviewPerson currentPerson = new PreviewPerson(cursor.getString(0), cursor.getString(1));
            people.add(currentPerson);
        }
        cursor.close();
        db.close();
        return people;
    }

    // Returns a full "Person" object based on their credit card number - this should be unique
    public Person getPersonByCard(String cardNum){
        String sqlQuery = "select * from " + TABLE_PEOPLE + " where " + NUMBER + " = " + cardNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( sqlQuery, null);
        Person person = null;
        if (cursor.moveToFirst()){
            person = new Person(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            person.updateCard(cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));


        }
        cursor.close();
        db.close();
        return person;

    }

    public void updateById(int personId, String name, double cardNum) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_PEOPLE;
        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += NUMBER + " = '" + cardNum + "'";
        sqlUpdate += " where " + personId + " = " + ID;

        db.execSQL( sqlUpdate );
        db.close( );
    }

    //TODO: Add a way to check if a credit card number already exists

}
