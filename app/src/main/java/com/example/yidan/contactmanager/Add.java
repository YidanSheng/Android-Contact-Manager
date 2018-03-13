package com.example.yidan.contactmanager;

/**
 * Written by Yidan Sheng for CS6326.001, assignment 4, starting Oct. 20, 2017.
 * NetID: yxs173130
 * This activity is for the file's writing and reading.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Add extends Activity {

    //Create a file object of File.
    //fn, ln, ph and em are the TextView object for add-layout.
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText eMail;
    public File file =  new File();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        //Define four EditText objects on this layout.
        firstName = (EditText)findViewById(R.id.Afirst_name);
        lastName = (EditText)findViewById(R.id.Alast_name);
        phoneNumber = (EditText)findViewById(R.id.Aphone);
        eMail = (EditText)findViewById(R.id.Ae_mail);
    }

    //Add click button event
    public void addClick(View view){

        //Get the contents of Editexts.
        Context context = getApplicationContext();
        String fN = firstName.getText().toString();
        String lN = lastName.getText().toString();
        String pN = phoneNumber.getText().toString();
        String eM = eMail.getText().toString();

        //If the value of 'First Name' is null, then give a notification on the screen.
        if(fN.length() == 0){
            Toast.makeText(context,"Input can not be emtpy",Toast.LENGTH_LONG).show();
            return;
        }

        //The data structure for contact is ArrayList<String[]>
        //In each String[], put first name into the first field, last name into the second field,
        //put phone number in the third field, finally put email address into the fourth field.
        List<String[]> addlist = new ArrayList<String[]>();
        String [] rec = new String[4];
        rec[0] = fN.trim();rec[1] = lN.trim();rec[2] = pN.trim();rec[3] = eM.trim();
        addlist.add(rec);

        //Put the whole list into file.
        file.writeFileApp(context,addlist);

        //Give a notification to the user.
        Toast.makeText(context.getApplicationContext(),"Add!",Toast.LENGTH_LONG).show();

        //Jump to the main activity to show the list
        Intent intent = new Intent();
        intent.setClass(context,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Context context = getApplicationContext();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addPerson) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
