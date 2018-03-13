package com.example.yidan.contactmanager;

/**
 * Written by Yidan Sheng for CS6326.001, assignment 4, starting Oct. 20, 2017.
 * NetID: yxs173130
 * This activity is for the file's writing and reading.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Create a ListView object and a file object
    private ListView listView;
    public File file =  new File();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        listView = (ListView)findViewById(R.id.listView);
        //writeTestData();

        //Load current data from file to contact lists
        FiletoListView();
    }

    //Add several test data into file
/*
    private void writeTestData(){
        List<String[]> lists = new ArrayList<String[]>();
        String [] rec = new String[4];
        String [] rec2 = new String[4];
        Context c = getApplicationContext();
        rec[0] = "Yidan";rec[1] = "Sheng";rec[2] = "3024506703";rec[3] = "agnessyd015@gmail.com";
        rec2[0] = "Lu";rec2[1] = "Lu";rec2[2] = "3024232401";rec2[3] = "lulu9091@gmail.com";
        lists.add(rec);
        lists.add(rec2);
        file.writeFilePri(c, lists);
    }
*/

    //Function: Load current to from file to listview.
    private void FiletoListView(){

        //Read file and put data into ArrayList.
        List<String[]> lists = new ArrayList<String[]>();
        Context c = getApplicationContext();
        lists = file.readFile(c);
        String [] rec = new String[4];
        String Name = "";
        String PhoneNumber = "";

        //Create ArrayList<HashMap<String, String>> to put data into listview.
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        //Create ArrayList<String> to store the data in each attribute
        final ArrayList<String> Fname = new ArrayList<String>();
        final ArrayList<String> Lname = new ArrayList<String>();
        final ArrayList<String> Phone = new ArrayList<String>();
        final ArrayList<String> Email = new ArrayList<String>();
        for(int i = 0; i < lists.size();i++){
            rec = lists.get(i);
            Name = rec[0].trim() + " " + rec[1].trim();
            PhoneNumber = rec[2].trim();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("personName", Name);
            map.put("phoneNumber", PhoneNumber);
            mylist.add(map);
            Fname.add(rec[0].trim());
            Lname.add(rec[1].trim());
            Phone.add(rec[2].trim());
            Email.add(rec[3].trim());
        }
        //Make Adapter of listview
        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.my_listitem,
                new String[] {"personName", "phoneNumber"},
                new int[] {R.id.personName,R.id.phoneNumber});

        //Add data into Adapter and show them
        listView.setAdapter(mSchedule);

        //Add listener for each item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id){

                //Define a Bundle object to transfer the data.
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this,Items.class);
                bundle.putString("Fname", Fname.get(position));
                bundle.putString("Lname", Lname.get(position));
                bundle.putString("Phone", Phone.get(position));
                bundle.putString("Email", Email.get(position));
                intent.putExtras(bundle);

                //Jump to the Item.class activity and show information details of certain contact
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Context context = getApplicationContext();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addPerson) {
            Intent intent = new Intent();
            intent.setClass(context,Add.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
