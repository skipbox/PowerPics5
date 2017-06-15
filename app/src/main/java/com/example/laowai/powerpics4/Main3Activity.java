package com.example.laowai.powerpics4;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import static com.example.laowai.powerpics4.MainActivity.READ_BLOCK_SIZE;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    load_log("");
        TextView tv_log_x= (TextView) findViewById(R.id.tv_my_log);
        tv_log_x.setMovementMethod(new ScrollingMovementMethod());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //this is to make the back button work with out recreating
        if (id == R.id.silent_sound) {Toast.makeText(this, "silent_sound", Toast.LENGTH_SHORT).show();
        finish();
        return true;
        }
        if (id == R.id.multiplier_on) {Toast.makeText(this, "multiplier_on", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
      //button clicks------------------------------------------------------------------
    public void but_click_act_3(View view) {
        int the_id = view.getId();
        if (the_id == R.id.but_show) {
            Toast.makeText(this, "but_show", Toast.LENGTH_SHORT).show();
            String my_log =  String.valueOf((read_stuff_act_3("my_log_now.txt")));
            load_log(my_log);
        }
        if (the_id == R.id.but_clear) {
            Toast.makeText(this, "but_clear", Toast.LENGTH_SHORT).show();
            clear_log_act_3("my_log_now.txt","clear"+"\n");
        }
        if (the_id == R.id.b1_x) {
         //Toast.makeText(this, "b1_x"+"\n"+"This will now append", Toast.LENGTH_SHORT).show();
            Date d = new Date();
            CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
            Button my_button = (Button)findViewById(R.id.b1_x);
            my_button.setText(s);
            //String my_seconds = String.valueOf(Integer.parseInt(read_stuff("time_interval.txt")));
            save_stuff_append_act_3("my_log_now.txt",String.valueOf(s));
            // could there be a timing issue here ?
            String my_log =  String.valueOf((read_stuff_act_3("my_log_now.txt")));
            load_log(my_log);
        }
    }
    private void save_stuff_append_act_3(String local_file_name, String local_data){
        local_data = local_data + "\n";
        try{
            FileOutputStream fileOut=openFileOutput(local_file_name,MODE_APPEND);//changed this
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.write(local_data);
            outputWriter.close();
            Toast.makeText(this, "File appended successfullly", Toast.LENGTH_SHORT).show();
        } catch (Exception e){e.printStackTrace();}
    }
     private void clear_log_act_3(String local_file_name, String local_data){
        //local_data = "clear celer";
        try{
            FileOutputStream fileOut=openFileOutput(local_file_name,MODE_PRIVATE);//changed this
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.write(local_data);
            outputWriter.close();
            Toast.makeText(this, "File cleared successfullly", Toast.LENGTH_SHORT).show();
        } catch (Exception e){e.printStackTrace();}
    }
 public void load_log(String text_to_load){
        TextView my_textview_x = (TextView)findViewById(R.id.tv_my_log);
        my_textview_x.setText(text_to_load);
        }
    // String whatever_xxx = read_stuff("my_app_data.txt");
// final_text_box.setText(whatever_xxx);
    public String read_stuff_act_3(String local_file_name){
        try{
            FileInputStream fileIn=openFileInput(local_file_name);
            InputStreamReader InputRead=new InputStreamReader(fileIn);
            char[] inputBuffer=new char[READ_BLOCK_SIZE];
            String s="";int charRead = 0;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;}
            InputRead.close();
            return s;
            //Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {e.printStackTrace();}
        return "error";
    }
}
