package com.example.yidan.contactmanager;

/**
 * Written by Yidan Sheng for CS6326.001, assignment 4, starting Oct. 20, 2017.
 * NetID: yxs173130
 * This activity is for the file's writing and reading.
 */

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class File {

    //Define the file path in the internal storage.
    public String fileName = "file.txt";

    //Parameter: context is the object of Context, lists is data what we need to write in file.
    //Function: Write the sorted records into write, write mode is APPEND.
    public void writeFileApp(Context context, List<String[]> lists){
        try {

            //Define the Comparator to sort the contact list.
            Comparator<String[]> comparator1 = new Comparator<String[]>(){
                public int compare(String[] s1, String[] s2) {

                    //If the first names of two items are not equal, then sort them by first name
                    if(!s1[0].equals(s2[0])){
                        return s1[0].compareTo(s2[0]);
                    }
                    else{

                        //If the first names of them are the same and last names are not equal,
                        // then sort by Last name.
                        if(!s1[1].equals(s2[1])){
                            return s1[1].compareTo(s2[1]);
                        }
                        else{
                            return 0;
                        }
                    }
                }
            };

            //Sort lists using Collection Class.
            Collections.sort(lists,comparator1);

            //Create fileoutputstream for the file
            FileOutputStream fileOutputStream = context.openFileOutput(fileName,Context.MODE_APPEND);
            String [] rec = new String[4];
            String stringMessage = "";

            //Use fileoutputstream to write message into file line by line.
            //Add "\t|" to seperate each item (first name, last name, phone number and address)
            //"\t" is in case of some field is empty, so put "\t" first and them add the actual value.
            for(int i = 0; i < lists.size();i++){
                rec = lists.get(i);
                stringMessage = rec[0].trim() + "\t|" + rec [1].trim()  + "\t|" + rec[2].trim()  +"\t|" + rec[3].trim()  + "\t\n";
                fileOutputStream.write(stringMessage.getBytes());
                fileOutputStream.flush();
            }

            //Close the fileoutputstream
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Function: Write the sorted records into write, write mode is MODE_PRIVATE.
    //Except the write mode, other functions are the same from above.
    public void writeFilePri(Context context, List<String[]> lists){
        try {
            Comparator<String[]> comparator2 = new Comparator<String[]>(){
                public int compare(String[] s1, String[] s2) {
                    if(!s1[0].equals(s2[0])){
                        return s1[0].compareTo(s2[0]);
                    }
                    else{
                        if(!s1[1].equals(s2[1])){
                            return s1[1].compareTo(s2[1]);
                        }
                        else{
                            return 0;
                        }
                    }
                }
            };
            Collections.sort(lists,comparator2);
            String [] rec = new String[4];
            String stringMessage = "";
            FileOutputStream fileOutputStream2 = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            for(int i = 0; i < lists.size();i++){
                rec = lists.get(i);
                stringMessage = rec[0].trim()  + "\t|" + rec [1].trim()  + "\t|" + rec[2].trim()  +"\t|" + rec[3].trim()  + "\t\n";
                fileOutputStream2.write(stringMessage.getBytes());
                fileOutputStream2.flush();
            }
            fileOutputStream2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Parameter: context is the object of Context
    //Return: sorted ArrayList.
    //Function: Read the sorted lists from the file

    public List<String[]> readFile(Context context){
        List<String[]> lists = new ArrayList<String[]>();
        try {

            String [] rec = new String[4];
            String Message;

            //Create fileoutputstream for the file
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            //Read content from file line by line. Then store the items to string array from each line by splitint "|"
            while ((Message=bufferedReader.readLine())!=null){
                stringBuffer.append(Message + "\n");
                rec = Message.split("\\|");
                lists.add(rec);
            }

            //Close the fileinputstream
            fileInputStream.close();

            //Define the Comparator to sort the contact list.
            Comparator<String[]> comparator3 = new Comparator<String[]>(){
                public int compare(String[] s1, String[] s2) {
                    if(!s1[0].equals(s2[0])){
                        return s1[0].compareTo(s2[0]);
                    }
                    else{
                        if(!s1[1].equals(s2[1])){
                            return s1[1].compareTo(s2[1]);
                        }
                        else{
                            return 0;
                        }
                    }
                }
            };

            //Sort lists.
            Collections.sort(lists,comparator3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }


}
