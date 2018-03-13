package com.example.yidan.contactmanager;

/**
 * Written by Tianrou Chang for CS6326.001, assignment 4, starting Oct. 20, 2017.
 * NetID: txc172430
 * This activity is for the edit-layout.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Edit extends Activity {
    //Create a file object
    public File file =  new File();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        //Get the data from items-activity
        //Use the Bundle class to receive the data
        Bundle bundle=getIntent().getExtras();
        final String Fname=bundle.getString("Fname").trim();
        final String Lname=bundle.getString("Lname").trim();
        final String Phone=bundle.getString("Phone").trim();
        final String Email=bundle.getString("Email").trim();

        //Show the data in the EditText on the screen
        final EditText Efname = (EditText)findViewById(R.id.Efirst_name);
        Efname.setText(Fname);
        final EditText Elname= (EditText)findViewById(R.id.Elast_name);
        Elname.setText(Lname);
        final EditText Ephone= (EditText)findViewById(R.id.Ephone);
        Ephone.setText(Phone);
        final EditText Eemail= (EditText)findViewById(R.id.Ee_mail);
        Eemail.setText(Email);

        //Define a listener to listen the click action of Save-button
        Button save = (Button)findViewById(R.id.Esave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make a onClick event
                Context c = getApplicationContext();
                //Receive the new information from the EditText
                EditText Ef = (EditText)findViewById(R.id.Efirst_name);
                EditText El = (EditText)findViewById(R.id.Elast_name);
                EditText Ep = (EditText)findViewById(R.id.Ephone);
                EditText Ee = (EditText)findViewById(R.id.Ee_mail);
                String fn = Ef.getText().toString();
                String ln = El.getText().toString();
                String pn = Ep.getText().toString();
                String em = Ee.getText().toString();

                //If the value of 'First Name' is null, then give a notification on the screen.
                if(fn.length() == 0){
                    Toast.makeText(c,"Input can not be emtpy",Toast.LENGTH_LONG).show();
                    return;
                }

                //Read the information from the file
                List<String[]> lists = new ArrayList<String[]>();
                lists = file.readFile(c);
                String [] rec = new String[4];
                int index = 0;//index indicates the number of line which needs to update.
                for(int i = 0;i<lists.size();i++){//To find the target index value.
                    rec = lists.get(i);
                    String string1 = rec[0].trim();
                    String string2 = rec[1].trim();
                    String string3 = rec[2].trim();
                    String string4 = rec[3].trim();
                    if(string1.equals(Fname)&&string2.equals(Lname)&&string3.equals(Phone)&&string4.equals(Email)){
                        //If all of the information are the same, that means this line is which we need to update.
                        index = i;
                    }
                }

                //Update the infomation
                String[] updated = new String[4];
                updated[0] = fn;
                updated[1] = ln;
                updated[2] = pn;
                updated[3] = em;
                lists.set(index,updated);

                //Write in the file.
                file.writeFilePri(c, lists);

                //Jump to the main activity to show the list
                Intent intent = new Intent();
                intent.setClass(c,MainActivity.class);
                startActivity(intent);

                //Give a notification to the user.
                Toast.makeText(c.getApplicationContext(),"Save!",Toast.LENGTH_LONG).show();
            }
        });

    }


}