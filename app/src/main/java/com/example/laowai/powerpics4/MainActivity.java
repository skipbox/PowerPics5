package com.example.laowai.powerpics4;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//import com.example.laowai.powerics2.R;

import com.example.laowai.powerpics4.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.PublicKey;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static android.R.attr.id;
import static android.R.drawable.edit_text;
import static android.R.id.edit;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.view.View.GONE;
//import static com.example.laowai.powerics2.R.id.but_iterate;
//import static com.example.laowai.powerics2.R.id.list_id;
////import static com.example.laowai.powerics2.R.id.menu_top_1;
//import static com.example.laowai.powerics2.R.id.seekBar;
//import static com.example.laowai.powerics2.R.id.toggle_on_off;
//import static com.example.laowai.powerpics4.R.id.but_iterate;
import static com.example.laowai.powerpics4.Main2Activity.key_11;
import static com.example.laowai.powerpics4.Main2Activity.key_7;
import static com.example.laowai.powerpics4.R.id.but_prime;
//import static com.example.laowai.powerpics4.R.id.but_sub_count;
import static com.example.laowai.powerpics4.R.id.but_time_short;
import static com.example.laowai.powerpics4.R.id.edit_text_multi;
import static com.example.laowai.powerpics4.R.id.list_id;
//import static com.example.laowai.powerpics4.R.id.toggle_on_off;
//import static com.example.laowai.powerpics4.R.id.tv;
import static com.example.laowai.powerpics4.R.raw.skype_feedback;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.valueOf;
import static java.security.AccessController.getContext;
import static java.util.Locale.ENGLISH;


public class MainActivity extends AppCompatActivity {
    //android:screenOrientation="portrait"  // keeps screen from rotation
    //SharedPreferences========================================================

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String key_1 = "k1";
    public static final String key_2 = "k2";
    public static final String key_3 = "k3";
    public static final String key_4 = "k4";
    public static final String key_5 = "k5";

        public static final String key_10 = "k10";
        public static final String key_11 = "k11";
        public static final String key_12 = "k12"; //x
        public static final String key_13 = "k13"; //x
        public static final String key_14 = "k14"; //x
        public static final String key_size = "key_size"; //x
        public static final String key_buzz = "key_buzz"; //x
    //can also use integers
    SharedPreferences sharedpreferences;
//SharedPreferences========================================================
    ClipboardManager myClipboard;
    ClipData myClip;

    int nnn = 0;
    int my_seconds = 5;// this is global

    TextToSpeech t1;
    //TextToSpeech t2;

    String array_read[]={"000","111"};
    int global_sound_pos = -1;
    //int global_count = -1;

    int g_spaced_rep_time = 1;
    int g_spaced_rep_time_holder = 2;//holds the saved time from the text file

    int int_multiplier = 100;
    MediaPlayer mp;

    String lang_now_x = "ENGLISH";

//    @Override
//    public  void onDestroy() {
//          wl.release();
//    }


    @Override // will disable the back button- might be a better way
    public void onBackPressed(){}

    private PowerManager.WakeLock wl;
//was private


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //stops curser from blinking


        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");


//            int repeatTime = 30;  //Repeat alarm time in seconds
//AlarmManager processTimer = (AlarmManager)getSystemService(ALARM_SERVICE);
//Intent intent = new Intent(this, processTimerReceiver.class);
//PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,  intent, PendingIntent.FLAG_UPDATE_CURRENT);
////Repeat alarm every second
////processTimer.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),repeatTime*1000, pendingIntent);
//processTimer.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),repeatTime*60000, pendingIntent);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //takes the focus off the edit Text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //TextToSpeech t1 //declare uptop
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){if(status != TextToSpeech.ERROR){t1.setLanguage(Locale.UK);
            }}});




        //problem is "if read is error" then program will crash
        //will happen whenenver intalling program without the file named "xxx.txt"
        check_files_exist();
        read_and_display();
        ListView my_list =(ListView)findViewById(list_id); my_list.setVisibility(GONE);

    //begin shared pref
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lang_now_x =  sharedpreferences.getString(key_1,"default");

       //needs to be put here at the bottom to work for some reason
       //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       String global_time_act_1  =  sharedpreferences.getString(key_10,"default");
       Button but_time_short_x = (Button)findViewById(R.id.but_time_short);
       but_time_short_x.setText(global_time_act_1);

       boolean check_box_state = Boolean.parseBoolean(sharedpreferences.getString(key_size,"default"));
       CheckBox check_box_x = (CheckBox) findViewById(R.id.check_size);
       check_box_x.setChecked(check_box_state);

       check_box_state = Boolean.parseBoolean(sharedpreferences.getString(key_buzz,"default"));
       check_box_x = (CheckBox) findViewById(R.id.check_buzz);
       check_box_x.setChecked(check_box_state);

       int show_clock_time = Integer.parseInt(sharedpreferences.getString(key_10,"22")); //default is 22
       //DateUtils.formatElapsedTime(show_clock_time);
       Button but_clock_time_x = (Button) findViewById(R.id.but_clock_time);
       but_clock_time_x.setText(String.valueOf(DateUtils.formatElapsedTime(show_clock_time)));
    //end of shared pref

        //end_of_on_create
        //onReceive();
    }
    //++++++++++++++   ____________   +++++++++++=============end of oncreate

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
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.multiplier_on) {Toast.makeText(this, "multiplier_on", Toast.LENGTH_SHORT).show();
            MediaPlayer.create(getApplicationContext(), R.raw.computer_chime).start();
            int_multiplier = 100;return true;
        }
        if (id == R.id.multiplier_off) {Toast.makeText(this, "multiplier_off", Toast.LENGTH_SHORT).show();
            MediaPlayer.create(getApplicationContext(), R.raw.end_fx).start();
            int_multiplier = 1;return true;
        }
        if (id == R.id.menu_top_bell) {Toast.makeText(this, "bell_and_log", Toast.LENGTH_SHORT).show();
            Intent start_act_log = new Intent(this,Main4Activity.class);
            startActivity(start_act_log);
            if(mp!=null) { mp.release(); }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.meetings);mp.start();
            return true;
        }
        if (id == R.id.menu_top_money) {Toast.makeText(this, "start_act_settings", Toast.LENGTH_SHORT).show();
            Intent start_act_settings = new Intent(this,Main2Activity.class);
            startActivity(start_act_settings);
            return true;
        }
        if (id == R.id.action_chinese) {Toast.makeText(this, "action_chinese", Toast.LENGTH_SHORT).show();
            t1.setLanguage(Locale.CHINESE);
            lang_now_x ="CHINESE";return true;
        }
        if (id == R.id.action_english) {Toast.makeText(this, "action_english", Toast.LENGTH_SHORT).show();
            t1.setLanguage(ENGLISH);
            lang_now_x ="ENGLISH";return true;
        }
        if (id == R.id.action_korean) {Toast.makeText(this, "action_korean", Toast.LENGTH_SHORT).show();
            t1.setLanguage(Locale.KOREAN);
           lang_now_x ="KOREAN";return true;
        }
        if (id == R.id.action_japanese) {Toast.makeText(this, "action_japanese", Toast.LENGTH_SHORT).show();
            t1.setLanguage(Locale.JAPANESE);
        lang_now_x ="JAPANESE";return true;
        }
        if (id == R.id.soft_input) {Toast.makeText(this, "soft_input", Toast.LENGTH_SHORT).show();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            EditText  edit_text_multi_x =(EditText)findViewById(R.id.edit_text_multi);
            //edit_text_multi_x.setCursorVisible(!isKeyboardOpen);
            //edit_text_multi_x.setCursorVisible(isKeyboardOpen);
            edit_text_multi_x.setCursorVisible(false);
            return true;
        }
        if (id == R.id.set_log) {Toast.makeText(this, "hide_save", Toast.LENGTH_SHORT).show();
            Intent start_act_settings = new Intent(this,Main3Activity.class);
            startActivity(start_act_settings);
            return true;
        }
        if (id == R.id.rule_of_3) {Toast.makeText(this, "\nThe Rule of 3 look it up\n\n", Toast.LENGTH_SHORT).show();
            //LinearLayout top_div_obj = (LinearLayout)findViewById(R.id.top_div_but_holder);top_div_obj.setVisibility(View.GONE);
            return true;
        }
         if (id == R.id.start_settings) {Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
            Intent start_act_settings = new Intent(this,Main2Activity.class);
            startActivity(start_act_settings);
            return true;
        }

//        if (id == R.id.menu_log) {Toast.makeText(this, "menu_log", Toast.LENGTH_SHORT).show();
//            //Intent start_act_log = new Intent(this,Main3Activity.class);
//            //startActivity(start_act_log);
//        }



        if (id == R.id.menu_top_step) {Toast.makeText(this, "action_1", Toast.LENGTH_SHORT).show();
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData abc = myClipboard.getPrimaryClip();
            ClipData.Item item_data = abc.getItemAt(0);
            String clip_text = item_data.getText().toString();

            EditText textmsg = (EditText) findViewById(R.id.edit_text_multi);
            textmsg.setText(clip_text);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
//notes
    //if you have 5 settings or less might want to just make 5 different text files
    //to improve this as well you might also want to create one that accesses the SD card
    //so you can share the data with other programs
    //
    //
    //

//SubRoutines===============
     //"Each time you click runs multiple timers\n"+"need to fix this"
//timer
    Boolean is_timer_running = FALSE;
    Boolean pause_play_subcount = true;
    //Button but_sub_count_obj = (Button)findViewById(R.id.but_sub_count);
    public void timer_go(){




       my_seconds  = Integer.parseInt(sharedpreferences.getString(key_10,"default"));
       Button but_time_short_x = (Button)findViewById(R.id.but_time_short);
       but_time_short_x.setText(String.valueOf(my_seconds));

        //alert if timer is already running
        Toast.makeText(MainActivity.this, "timer running = "+is_timer_running, Toast.LENGTH_SHORT).show();
        new CountDownTimer(999999999, 1000){
            public void onTick(long millisUntilFinished) {
                Button my_but=(Button)findViewById(R.id.but_full_count);


                int mills_to_secs = (int) (millisUntilFinished / 1000);
                //mills_to_secs = 60
                my_but.setText(""+mills_to_secs);




                if (pause_play_subcount == true){ //only if pause subcount is == to play
                    g_spaced_rep_time = g_spaced_rep_time -1;
                    if (g_spaced_rep_time <= -1){
                        //g_spaced_rep_time = g_spaced_rep_time_holder;
                        g_spaced_rep_time = my_seconds;
                        //Toast.makeText(MainActivity.this, "count_x = "+count_x, Toast.LENGTH_SHORT).show();
                        //speak_next_phrase();

                        but_count_all_as_sub();

        g_spaced_rep_time_holder = my_seconds;
        Button but_seconds_obj = (Button)findViewById(R.id.but_time_short);
        but_seconds_obj.setText(""+my_seconds);
                    }
                }

                Button but_sub_count_obj = (Button)findViewById(R.id.but_time_short);
                but_sub_count_obj.setText(String.valueOf(g_spaced_rep_time));

                 Button but_clock_time_x = (Button) findViewById(R.id.but_clock_time);
                 but_clock_time_x.setText(""+String.valueOf(DateUtils.formatElapsedTime(g_spaced_rep_time)));
            }
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }}.start();}






////<uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
//public class AlarmReceiver extends BroadcastReceiver {
//    //int interval = 60000; // 1 minute
//        int interval = 1000; // 1 minute 10 seconds
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//        // doYourThing();
//        speak_this("hello");
//        Intent i = new Intent(context, AlarmReceiver.class);
//        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        // You can doYourThing() again after an interval
//        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, PendingIntent.getBroadcast(context, 1, i, PendingIntent.FLAG_UPDATE_CURRENT));
//
//    }
//}
//================
////This is called every second (depends on repeatTime)
//public class processTimerReceiver extends BroadcastReceiver{
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        //Do something every 30 seconds
//        //speak_this("hello");
//        but_count_all_as_sub();
//    }
//}


    static final int READ_BLOCK_SIZE = 100;
//save_stuff("setting_1.txt",last_login.getText().toString());
//save_stuff("setting_2.txt",name.getText().toString());
//save_stuff("setting_3.txt",total_iterations.getText().toString());
// save_stuff("setting41.txt",total_time.getText().toString());

    //save_stuff("my_app_data.txt",textmsg.getText().toString());
    private void save_stuff(String local_file_name, String local_data){
        try{
            FileOutputStream fileOut=openFileOutput(local_file_name,MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.write(local_data);
            outputWriter.close();
            Toast.makeText(this, "File saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){e.printStackTrace();}
    }
    // String whatever_xxx = read_stuff("my_app_data.txt");
// final_text_box.setText(whatever_xxx);
    public String read_stuff(String local_file_name){
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

    public void speak_next_phrase() {
        global_sound_pos = global_sound_pos + 1;
        if (global_sound_pos > (array_read.length - 1)) {
            global_sound_pos = 0;
        }

        Button obj_but_count_all = (Button)findViewById(R.id.but_count_all);
        obj_but_count_all.setText(array_read[global_sound_pos]);
        String temp_text_to_speak = array_read[global_sound_pos];


      //if it has a # then will reduce the time to next phrase to xxx seconds or whatever is selected in shared pref
        if(temp_text_to_speak.contains("#")){
        temp_text_to_speak = temp_text_to_speak.replace("#","");
            //play_sound("end_fx");
            g_spaced_rep_time = 5;
            //g_spaced_rep_time = Integer.parseInt(temp_text_to_speak);
            //Toast.makeText(MainActivity.this, "contains #", Toast.LENGTH_SHORT).show();
            //return; // return will make it exit early
        }
        if(temp_text_to_speak.equals("")){
        temp_text_to_speak = temp_text_to_speak.replace("#","");
        Toast.makeText(MainActivity.this, "could be empty how can I fix this ??", Toast.LENGTH_LONG).show();
            play_sound("end_fx");
        }
       //can add delay here
        temp_text_to_speak = temp_text_to_speak.replace("#","");
        //temp_text_to_speak = temp_text_to_speak.replace("*","");
        speak_this(temp_text_to_speak);
        //speak_this(array_read[global_sound_pos]);


        //Button obj_but_pos_ct = (Button)findViewById(R.id.but_pos_count);//shows it on the button
        // obj_but_pos_ct.setText(String.valueOf(global_sound_pos));
    }

    public void read_saved_list(String passed_data){
        EditText textmsg = (EditText) findViewById(R.id.edit_text_multi);
        textmsg.setText(passed_data);
    }
    //"Each time you click runs multiple timers\n"+"need to fix this"
//timer


    public void read_and_display(){
        my_seconds = Integer.parseInt(read_stuff("time_interval.txt"));
        g_spaced_rep_time_holder = my_seconds;

        //alarm_interval= my_seconds;


       // Button but_seconds_obj = (Button)findViewById(R.id.but_seconds);
       // but_seconds_obj.setText(""+my_seconds);

       // Button but_minutes_obj = (Button)findViewById(R.id.but_minutes);
       // but_minutes_obj.setText(String.valueOf(my_seconds/60));

        // will show erro if there is an error but it is an itteer
        // myContext.deleteFile(fileName);

        String whatever_xxx = read_stuff("power_challenge_list.txt");
        array_read = whatever_xxx.split("\n");
        read_saved_list(whatever_xxx);
        TextView obj_text_multi = (TextView)findViewById(R.id.edit_text_multi);
        obj_text_multi.setText(whatever_xxx);

    }



    public void check_files_exist(){
        String error_yes_no = "0";

        error_yes_no =  read_stuff("my_stats.txt");
        if(error_yes_no.equals("error")){save_stuff("my_stats.txt","1");}

        error_yes_no =  read_stuff("time_interval.txt");
        if(error_yes_no.equals("error")){save_stuff("time_interval.txt","1");}

        error_yes_no =  read_stuff("power_challenge_list.txt");
        if(error_yes_no.equals("error")){save_stuff("power_challenge_list.txt","1");}

        // error_yes_no =  read_stuff("my_stats.txt");
        // if(error_yes_no =="error"){save_stuff("my_stats.txt","1");}
    }


    //button clicks------------------------------------------------------------------
    public void buttonOnClick(View view)
    {
        int the_id = view.getId();
        //if (the_id == R.id.check_size) {
        // Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();}
        if (the_id == R.id.sec_temp) {
         Toast.makeText(this, "sec_temp", Toast.LENGTH_SHORT).show();
        play_sound("end_fx");
        }
        if(the_id == R.id.but_prime){
            Toast.makeText(this, "but_prime", Toast.LENGTH_SHORT).show();
            if(mp!=null) { mp.release(); }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);mp.start();
        }

        if(the_id == R.id.but_save){
            //total_progress_interval = 10;
//save_stuff("my_app_data.txt",textmsg.getText().toString());
            Toast.makeText(this, ""+my_seconds, Toast.LENGTH_SHORT).show();
            save_stuff("time_interval.txt",""+my_seconds);
            //save_stuff("total_progress.txt",""+total_progress_iterations);
            //Toast.makeText(this, ""+total_progress_interval + "---" + total_progress_iterations, Toast.LENGTH_SHORT).show();
            EditText textmsg = (EditText) findViewById(R.id.edit_text_multi);
            save_stuff("power_challenge_list.txt",textmsg.getText().toString());
        }

        if(the_id == R.id.but_read){
            read_and_display();
        }
        //if(the_id == R.id.but_image_think){Toast.makeText(this, "but_image_think", Toast.LENGTH_SHORT).show();}
//        if(the_id == but_sub_count){
//            Button obj_but_sub_count=(Button)findViewById(R.id.but_sub_count);
//        }
        if(the_id == R.id.but_full_count){

            timer_go();
            //wl.acquire();

            Toast.makeText(this, "Button is disabled to prevent multiple timer runs", Toast.LENGTH_SHORT).show();
            Button my_but=(Button)findViewById(R.id.but_full_count);
            my_but.setEnabled(false);//disable button
            my_but.setTextColor(Color.parseColor("#0252d3"));
            //Toast.makeText(this, "****************", Toast.LENGTH_SHORT).show();


        }

        if(the_id == R.id.but_count_all){
            Date d = new Date();
            CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
            save_stuff_append("my_log_now.txt",String.valueOf(s));
          //save_stuff_append("my_log_now.txt","test");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lang_now_x =  sharedpreferences.getString(key_1,"default");
 ///if (some_sting.equals("admin")) { }
        if (lang_now_x.equals("ENGLISH")){t1.setLanguage(ENGLISH);}
        if (lang_now_x.equals("CHINESE")){t1.setLanguage(Locale.CHINESE);}
        if (lang_now_x.equals("JAPANESE")){t1.setLanguage(Locale.JAPANESE);}

        Button my_but=(Button)findViewById(R.id.but_prime);
        my_but.setText(lang_now_x);

            speak_next_phrase();
        }
        if(the_id == R.id.but_time_short){
        Toast.makeText(this, "but_time_short", Toast.LENGTH_SHORT).show();
          pause_play_subcount = false;

       sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       String global_time_act_1  =  sharedpreferences.getString(key_10,"default");
       Button but_time_short_x = (Button)findViewById(R.id.but_time_short);
       //but_minutes_obj.setText(String.valueOf(my_seconds/60));
       but_time_short_x.setText(global_time_act_1);
       }
        if(the_id == R.id.but_repeat) {
            //Toast.makeText(this, "but_repeat", Toast.LENGTH_SHORT).show();
            //speak_this("こんにちは");
            //String but_text_x = ((Button)view).getText().toString();

            Button but_text_x =(Button)findViewById(R.id.but_count_all);
            String but_text_now = but_text_x.getText().toString();
            speak_this(but_text_now);

        }
        if (the_id == R.id.but_rate) {
         Toast.makeText(this, "but_rate", Toast.LENGTH_SHORT).show();
        play_sound("end_fx");
            set_speech_rate();
  //???error here ???????????
            //could do this in the xml to be safe I think
        EditText  edit_text_multi_x =(EditText)findViewById(R.id.edit_text_multi);
        edit_text_multi_x.setFocusable(false);

        }

        if (the_id == R.id.but_clock_time) {
        Toast.makeText(this, "will start", Toast.LENGTH_SHORT).show();
        play_sound("end_fx");
         pause_play_subcount = true;
        }

    }
//end button clicks
    public void speak_this(String words_to_say){
        //if(words_to_say.equals("*")){t1.setLanguage(ENGLISH);}
        if(words_to_say.contains("*")){t1.setLanguage(ENGLISH);}
        words_to_say = words_to_say.replace("*","");
        //words_to_say = String.valueOf((array_read[global_sound_pos].substring(0, 1)));
        t1.speak(words_to_say, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(this, ""+words_to_say, Toast.LENGTH_SHORT).show();
    }

    public void play_sound(String sound_to_play){
        //if (sound_to_play == "just_cant_get_enough"){then play that sound}
        if(mp!=null) { mp.release(); }
           mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);mp.start();
        if(mp!=null) { mp.release(); }
        if(sound_to_play.equals("just_cant_get_enough")){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);mp.start();}
        if(sound_to_play.equals("end_fx")){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.end_fx);mp.start();}
        if(sound_to_play.equals("skype_feedback")){
            mp = MediaPlayer.create(getApplicationContext(), skype_feedback);mp.start();}
        if(sound_to_play.equals("sms_alert_4")){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.sms_alert_4);mp.start();}
    }


    //check_click
        //check_click clicks------------------------------------------------------------------
    public void check_click(View view) {
        int the_id = view.getId();


                if (the_id == R.id.check_lock) {

                    boolean check_state = ((CheckBox) findViewById(R.id.check_lock)).isChecked();
                    Toast.makeText(this, "my_check_box\n"+check_state+"\n", Toast.LENGTH_SHORT).show();
                    if (check_state == true){wl.acquire();}
                   if (check_state == false){wl.release();}

                    boolean check_lock_state = (wl.isHeld());
                    TextView tv_lock_on_off_x = (TextView)findViewById(R.id.tv_lock_on_off);
                    tv_lock_on_off_x.setText(String.valueOf(check_lock_state));
                }



        if (the_id == R.id.check_size) {

            boolean check_state = ((CheckBox) findViewById(R.id.check_size)).isChecked();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_size, String.valueOf(check_state));editor.apply();

            String temp_test = sharedpreferences.getString(key_size,"default");
            Toast.makeText(this, "my_check_box\n"+check_state+"\n"+temp_test, Toast.LENGTH_SHORT).show();

           // Toast.makeText(this, "check_size"+"", Toast.LENGTH_SHORT).show();
            Button but_main = (Button) findViewById(R.id.but_count_all);
            but_main.setTextSize(25);
        }
        if (the_id == R.id.check_buzz) {
            boolean check_state = ((CheckBox) findViewById(R.id.check_buzz)).isChecked();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key_buzz, String.valueOf(check_state));editor.apply();

            Vibrator my_vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            my_vibe.vibrate(100);


            String temp_test = sharedpreferences.getString(key_size,"default");
            Toast.makeText(this, "my_check_box\n"+check_state+"\n"+temp_test, Toast.LENGTH_SHORT).show();
        }
    }
    private void save_stuff_append(String local_file_name, String local_data){
        local_data = local_data + "\n";
        try{
            FileOutputStream fileOut=openFileOutput(local_file_name,MODE_APPEND);//changed this
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.write(local_data);
            outputWriter.close();
            Toast.makeText(this, "File appended successfullly", Toast.LENGTH_SHORT).show();
        } catch (Exception e){e.printStackTrace();}
    }


public void but_count_all_as_sub(){
             Date d = new Date();
            CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
            save_stuff_append("my_log_now.txt",String.valueOf(s));
          //save_stuff_append("my_log_now.txt","test");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lang_now_x =  sharedpreferences.getString(key_1,"default");
 ///if (some_sting.equals("admin")) { }
        if (lang_now_x.equals("ENGLISH")){t1.setLanguage(ENGLISH);}
        if (lang_now_x.equals("CHINESE")){t1.setLanguage(Locale.CHINESE);}
        if (lang_now_x.equals("JAPANESE")){t1.setLanguage(Locale.JAPANESE);}

        Button my_but=(Button)findViewById(R.id.but_prime);
        my_but.setText(lang_now_x);



        speak_next_phrase();
        }
int rate_count = 0;
float sp_rate = (float)0.1;
public void set_speech_rate(){
    rate_count = rate_count + 1;
    if (rate_count >= 4){rate_count = 0;}
    if (rate_count == 1){sp_rate = (float).3;}
    if (rate_count == 2){sp_rate = (float).5;}
    if (rate_count == 3){sp_rate = (float)1;}

    t1.setSpeechRate(sp_rate);

    Button but_rate_x =(Button)findViewById(R.id.but_rate);
    but_rate_x.setText(String.valueOf(sp_rate));

}
}
//end
// Button obj_but_today =(Button)findViewById(R.id.but_today);
// obj_but_today.setText(error_yes_no);





