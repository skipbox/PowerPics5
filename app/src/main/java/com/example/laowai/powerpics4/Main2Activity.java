package com.example.laowai.powerpics4;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import static com.example.laowai.powerpics4.R.id.tv_special;
import static java.util.Locale.ENGLISH;

public class Main2Activity extends AppCompatActivity {
    //SharedPreferences========================================================
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String key_1 = "k1";
    public static final String key_2 = "k2";
    public static final String key_3 = "k3";
    public static final String key_4 = "k4";
    public static final String key_5 = "k5";
        public static final String key_6 = "k6"; //check box
        public static final String key_7 = "k7"; //check box

        public static final String key_10 = "k10"; //time
        public static final String key_11 = "k11"; //x
        public static final String key_12 = "k12"; //x
        public static final String key_13 = "k13"; //x
        public static final String key_14 = "k14"; //x
        public static final String key_15 = "k15"; //x

    //can also use integers
    SharedPreferences sharedpreferences;
//SharedPreferences========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        load_preferences();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //https://stackoverflow.com/questions/9554885/activity-methodsoncreate-and-ondestroy

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
    public void buttonOnClick_2(View view)
    {
        int the_id = view.getId();
        if(the_id == R.id.but_set_english){
        //Toast.makeText(this, "but_set_english", Toast.LENGTH_SHORT).show();

            Date d = new Date();
            CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());

            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_1, String.valueOf("ENGLISH"));editor.apply();

            String language_now = sharedpreferences.getString(key_1,"default");
            TextView tv_language = (TextView) findViewById(R.id.tv_language);
            tv_language.setText("language_now="+language_now+"\n"+s);
        }
//==
        if (the_id == R.id.my_check_box) {

            //CheckBox my_check_box_view = (CheckBox) findViewById(R.id.my_check_box);
            boolean check_state = ((CheckBox) findViewById(R.id.my_check_box)).isChecked();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_5, String.valueOf(check_state));
            editor.apply(); //replaced commit with apply

            String temp_test = sharedpreferences.getString(key_5,"default");
            Toast.makeText(this, "my_check_box\n"+check_state+"\n"+temp_test, Toast.LENGTH_SHORT).show();
        }
        if (the_id == R.id.my_check_box_2) {
            //CheckBox my_check_box_view = (CheckBox) findViewById(R.id.my_check_box);
            boolean check_state = ((CheckBox) findViewById(R.id.my_check_box_2)).isChecked();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_6, String.valueOf(check_state));
            editor.apply(); //replaced commit with apply

            String temp_test = sharedpreferences.getString(key_6,"default");
            Toast.makeText(this, "my_check_box\n"+check_state+"\n"+temp_test, Toast.LENGTH_SHORT).show();
        }

            if (the_id == R.id.my_check_box_3) {
            //CheckBox my_check_box_view = (CheckBox) findViewById(R.id.my_check_box);
            boolean check_state = ((CheckBox) findViewById(R.id.my_check_box_3)).isChecked();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_7, String.valueOf(check_state));
            editor.apply(); //replaced commit with apply

            String temp_test = sharedpreferences.getString(key_7,"default");
            Toast.makeText(this, "my_check_box\n"+check_state+"\n"+temp_test, Toast.LENGTH_SHORT).show();
        }

        if(the_id == R.id.but_set_jap){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_1, String.valueOf("JAPANESE"));editor.apply();

            String language_now = sharedpreferences.getString(key_1,"default");
            TextView tv_language = (TextView) findViewById(R.id.tv_language);
            tv_language.setText("language_now="+language_now);
        }
        if(the_id == R.id.but_set_chinese){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_1, String.valueOf("CHINESE"));editor.apply();

            String language_now = sharedpreferences.getString(key_1,"default");
            TextView tv_language = (TextView) findViewById(R.id.tv_language);
            tv_language.setText("language_now="+language_now);
        }
        }


public void load_preferences(){
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String language_now = sharedpreferences.getString(key_1,"default");
        //String time_saved_1 = sharedpreferences.getString(key_xxx,"default");
      //show the global time
        global_time_x = Integer.parseInt(sharedpreferences.getString(key_10,"22")); //default is 22
        TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
        tv_time_x.setText(String.valueOf(global_time_x));
        DateUtils.formatElapsedTime(global_time_x);
        TextView tv_hh_mm_ss_x = (TextView) findViewById(R.id.tv_hh_mm_ss);
        tv_hh_mm_ss_x.setText(String.valueOf(DateUtils.formatElapsedTime(global_time_x)));


        boolean check_box_state = Boolean.parseBoolean(sharedpreferences.getString(key_5,"default"));
        CheckBox check_box_x = (CheckBox) findViewById(R.id.my_check_box);
        check_box_x.setChecked(check_box_state);

        boolean check_box_state_2 = Boolean.parseBoolean(sharedpreferences.getString(key_6,"default"));
        CheckBox check_box_state_x_2 = (CheckBox) findViewById(R.id.my_check_box_2);
        check_box_state_x_2.setChecked(check_box_state_2);

        boolean check_box_state_3 = Boolean.parseBoolean(sharedpreferences.getString(key_7,"default"));
        CheckBox check_box_state_x_3 = (CheckBox) findViewById(R.id.my_check_box_3);
        check_box_state_x_3.setChecked(check_box_state_3);

      TextView tv_language = (TextView) findViewById(R.id.tv_language);
      tv_language.setText("language_now="+language_now);



    }
//=====add_time_buttons
int global_time_x = 5;
    public void add_time(View view)
    {
        int the_id = view.getId();
        if(the_id == R.id.b_add_1) {global_time_x = global_time_x + 1;
          TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
          tv_time_x.setText(String.valueOf(global_time_x));
        }
        if(the_id == R.id.b_add_10) {global_time_x = global_time_x + 10;
          TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
          tv_time_x.setText(String.valueOf(global_time_x));
        }
          if(the_id == R.id.b_add_60) {global_time_x = global_time_x + 60;
          TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
          tv_time_x.setText(String.valueOf(global_time_x));
        }
          if(the_id == R.id.b_add_3600) {global_time_x = global_time_x + 3600;
          TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
          tv_time_x.setText(String.valueOf(global_time_x));
        }
          if(the_id == R.id.b_clear) {global_time_x = 3;
          TextView tv_time_x = (TextView) findViewById(R.id.tv_time);
          tv_time_x.setText(String.valueOf(global_time_x));
        }

        //needs to be long ?
        DateUtils.formatElapsedTime(global_time_x);
         TextView tv_hh_mm_ss_x = (TextView) findViewById(R.id.tv_hh_mm_ss);
          tv_hh_mm_ss_x.setText(String.valueOf(DateUtils.formatElapsedTime(global_time_x)));

           sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_10, String.valueOf(global_time_x));editor.apply();

     }

int special_time_x = 5;
//        if(the_id == R.id.b_sub_special_1){Toast.makeText(this, "b_sub_special_1", Toast.LENGTH_SHORT).show();}
//       if(the_id == R.id.b_add_special_1){Toast.makeText(this, "b_add_special_1", Toast.LENGTH_SHORT).show();}
//        if(the_id == R.id.clear_special){Toast.makeText(this, "clear_special", Toast.LENGTH_SHORT).show();}

//          if(the_id == R.id.b_add_special_1) {special_time_x = special_time_x + 1;
//          TextView tv_special_x = (TextView) findViewById(tv_special);
//          tv_special_x.setText(String.valueOf(special_time_x));
//        }

}
