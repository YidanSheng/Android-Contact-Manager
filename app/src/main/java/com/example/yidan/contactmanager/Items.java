package com.example.yidan.contactmanager;

/**
 * Written by Tianrou Chang for CS6326.001, assignment 4, starting Oct. 20, 2017.
 * NetID: txc172430
 * This activity is for the items-layout.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Items extends Activity {

    //Create a file object of File.
    //fn, ln, ph and em are the TextView object for items-layout.
    public File file =  new File();
    private TextView fn;
    private TextView ln;
    private TextView ph;
    private TextView em;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        //Get the data from main-activity
        //Use the Bundle class to receive the data
        Bundle bundle=getIntent().getExtras();
        final String Fname=bundle.getString("Fname").trim();
        final String Lname=bundle.getString("Lname").trim();
        final String Phone=bundle.getString("Phone").trim();
        final String Email=bundle.getString("Email").trim();

        //Show the data in the TextView on the screen
        fn=(TextView) findViewById(R.id.First_Name);
        fn.setText(Fname);
        ln=(TextView) findViewById(R.id.Last_Name);
        ln.setText(Lname);
        ph=(TextView) findViewById(R.id.Phone);
        ph.setText(Phone);
        em=(TextView) findViewById(R.id.E_mail);
        em.setText(Email);

        //Define a listener to listen the click action of edit-button
        Button edit=(Button)findViewById(R.id.B_edit);
        edit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Make a onClick event
                //Define a Intent object to jump to the edit-activity.
                Context context = getApplicationContext();
                Intent intent =new Intent();
                intent.setClass(context,Edit.class);

                //Define a Bundle object to transfer the data.
                Bundle bundle = new Bundle();
                bundle.putString("Fname", Fname);
                bundle.putString("Lname", Lname);
                bundle.putString("Phone", Phone);
                bundle.putString("Email", Email);

                //Put the data into the intent object.
                intent.putExtras(bundle);

                //Jump to the edit-activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Define a listener to listen the click action of delete-button
    public void deleteClick(View view){

        List<String[]> lists = new ArrayList<String[]>();
        Context c = getApplicationContext();

        //Read the file and get the information
        lists = file.readFile(c);
        String [] rec = new String[4];
        String fname = fn.getText().toString().trim();
        String lname = ln.getText().toString().trim();
        String pnumber = ph.getText().toString().trim();
        String email = em.getText().toString().trim();

        int index = 0;//index indicates the number of line which needs to delete.
        for(int i = 0;i<lists.size();i++){//To find the target index value.
            rec = lists.get(i);
            String string1 = rec[0].trim();
            String string2 = rec[1].trim();
            String string3 = rec[2].trim();
            String string4 = rec[3].trim();
            if(string1.equals(fname)&&string2.equals(lname)&&string3.equals(pnumber)&&string4.equals(email)){
                //If all of the information are the same, that means this line is which we need to delete.
                index = i;
            }
        }
        lists.remove(index);//delete the target line.

        //Write to the file.
        file.writeFilePri(c, lists);

        //Jump to the main activity to show the list
        Intent intent = new Intent();
        intent.setClass(c,MainActivity.class);
        startActivity(intent);

        //Give a notification to the user.
        Toast.makeText(c.getApplicationContext(),"Delete!",Toast.LENGTH_LONG).show();
    }
}
