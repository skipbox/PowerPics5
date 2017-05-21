package com.example.laowai.powerpics4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.Locale;
import java.util.Random;

import static android.R.attr.id;
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
import static com.example.laowai.powerpics4.R.id.but_prime;
import static com.example.laowai.powerpics4.R.id.but_sub_count;
import static com.example.laowai.powerpics4.R.id.list_id;
import static com.example.laowai.powerpics4.R.id.toggle_on_off;
//import static com.example.laowai.powerpics4.R.id.tv;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.valueOf;
import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    //android:screenOrientation="portrait"  // keeps screen from rotation
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
    @Override // will disable the back button- might be a better way
    public void onBackPressed(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextToSpeech t1 //declare uptop
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){if(status != TextToSpeech.ERROR){t1.setLanguage(Locale.UK);
            }}});



        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(600);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);
        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                // TextView tv = (TextView)findViewById(tv); // should change this
                // tv.setText("Seconds:" +"\n"+ newVal +"\n"+"Minutes:"+"\n"+ (newVal/ 60) );
                my_seconds = newVal;

                Button but_seconds_obj = (Button)findViewById(R.id.but_seconds);
                but_seconds_obj.setText(""+my_seconds);

                Button but_minutes_obj = (Button)findViewById(R.id.but_minutes);
                but_minutes_obj.setText(String.valueOf(my_seconds/60));




                Toast.makeText(MainActivity.this, ""+my_seconds, Toast.LENGTH_SHORT).show();
            }
        });


        //problem is "if read is error" then program will crash
        //will happen whenenver intalling program without the file named "xxx.txt"
        check_files_exist();
        read_and_display();
        ListView my_list =(ListView)findViewById(list_id); my_list.setVisibility(GONE);
    }
    //++++++++++++++   ____________   +++++++++++=============end of oncreate
//    set_adapter_2(array_x); // new method is you pass it the array
    public void set_adapter_2(String passed_x[]) {
        ListView my_listview = (ListView) findViewById(list_id);
        ArrayAdapter<String> my_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, passed_x);
        my_listview.setAdapter(my_adapter);
        my_listview.setOnItemClickListener(this);
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
        if (id == R.id.menu_top_bell) {Toast.makeText(this, "bell", Toast.LENGTH_SHORT).show();
            if(mp!=null) { mp.release(); }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.meetings);mp.start();
            return true;
        }
        if (id == R.id.menu_top_money) {Toast.makeText(this, "menu_top_money", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_chinese) {Toast.makeText(this, "action_chinese", Toast.LENGTH_SHORT).show();
            lang_now ="CHINESE";return true;
        }
        if (id == R.id.action_english) {Toast.makeText(this, "action_english", Toast.LENGTH_SHORT).show();
            lang_now ="ENGLISH";return true;
        }
        if (id == R.id.action_korean) {Toast.makeText(this, "action_korean", Toast.LENGTH_SHORT).show();
            lang_now ="KOREAN";return true;
        }
        if (id == R.id.show_save) {Toast.makeText(this, "show_save", Toast.LENGTH_SHORT).show();
            // LinearLayout top_div_obj = (LinearLayout)findViewById(R.id.top_div_but_holder);top_div_obj.setVisibility(View.VISIBLE);
            return true;
        }
        if (id == R.id.hide_save) {Toast.makeText(this, "hide_save", Toast.LENGTH_SHORT).show();
            //LinearLayout top_div_obj = (LinearLayout)findViewById(R.id.top_div_but_holder);top_div_obj.setVisibility(View.GONE);
            return true;
        }
        if (id == R.id.rule_of_3) {Toast.makeText(this, "\nThe Rule of 3 look it up\n\n", Toast.LENGTH_SHORT).show();
            //LinearLayout top_div_obj = (LinearLayout)findViewById(R.id.top_div_but_holder);top_div_obj.setVisibility(View.GONE);
            return true;
        }
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

    //SubRoutines
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
        //Toast.makeText(MainActivity.this, "array_read.length = " + array_read.length, Toast.LENGTH_SHORT).show();
        //speak_this(array_read[global_sound_pos]);
        speak_this(array_read[global_sound_pos]);
        Button obj_but_count_all = (Button)findViewById(R.id.but_count_all);
        obj_but_count_all.setText(array_read[global_sound_pos]);

        //Button obj_but_pos_ct = (Button)findViewById(R.id.but_pos_count);//shows it on the button
        // obj_but_pos_ct.setText(String.valueOf(global_sound_pos));
    }

    public void read_saved_list(String passed_data){

        EditText textmsg = (EditText) findViewById(R.id.edit_text_multi);
        textmsg.setText(passed_data);
    }
    //"Each time you click runs multiple timers\n"+"need to fix this"
//timer
    Boolean is_timer_running = FALSE;
    Boolean pause_play_subcount = true;
    //Button but_sub_count_obj = (Button)findViewById(R.id.but_sub_count);
    public void timer_go(){
        //alert if timer is already running
        Toast.makeText(MainActivity.this, "timer running = "+is_timer_running, Toast.LENGTH_SHORT).show();
        new CountDownTimer(999999999, 1000){
            public void onTick(long millisUntilFinished) {
                Button my_but=(Button)findViewById(R.id.but_full_count);
                my_but.setText(""+millisUntilFinished / 1000);
                if (pause_play_subcount == true){ //only if pause subcount is == to play
                    Button but_sub_count_obj = (Button)findViewById(but_sub_count);
                    but_sub_count_obj.setBackgroundColor(Color.GREEN);

                    //global_spaced_repetition_time
                    g_spaced_rep_time = g_spaced_rep_time -1;
                    if (g_spaced_rep_time <= -1){
                        //g_spaced_rep_time = g_spaced_rep_time_holder;
                        g_spaced_rep_time = my_seconds;
                        //Toast.makeText(MainActivity.this, "count_x = "+count_x, Toast.LENGTH_SHORT).show();
                        speak_next_phrase();
                        //iteration_control();
                        add_to_statistics();


                        if(mp!=null) { mp.release(); }
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.silence_8m);mp.start();

                        //if(mp!=null) { mp.release(); }
                       // mp = MediaPlayer.create(getApplicationContext(), R.raw.sms_alert_4);mp.start();



                       //keeps playing silence !!! another sound played will kill this so be careful

                    }
                }
                if (pause_play_subcount == false){
                    Button but_sub_count_obj = (Button)findViewById(but_sub_count);
                    but_sub_count_obj.setBackgroundColor(Color.GRAY);}
//
                Button but_sub_count_obj = (Button)findViewById(but_sub_count);
                but_sub_count_obj.setText(String.valueOf(g_spaced_rep_time));
            }
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }}.start();}

    public void read_and_display(){
        my_seconds = Integer.parseInt(read_stuff("time_interval.txt"));
        g_spaced_rep_time_holder = my_seconds;

        //alarm_interval= my_seconds;


        Button but_seconds_obj = (Button)findViewById(R.id.but_seconds);
        but_seconds_obj.setText(""+my_seconds);

        Button but_minutes_obj = (Button)findViewById(R.id.but_minutes);
        but_minutes_obj.setText(String.valueOf(my_seconds/60));

        // will show erro if there is an error but it is an itteer
        // myContext.deleteFile(fileName);

        String whatever_xxx = read_stuff("power_challenge_list.txt");
        array_read = whatever_xxx.split("\n");
        set_adapter_2(array_read);
        read_saved_list(whatever_xxx);
        TextView obj_text_multi = (TextView)findViewById(R.id.edit_text_multi);
        obj_text_multi.setText(whatever_xxx);

    }

    public void add_to_statistics(){
        //read file // add 1 // write file //Display it on but_all_time
        String first_time_crisis = read_stuff("my_stats.txt");
        //IMPORTANT

        if(first_time_crisis == "error" ){
            Toast.makeText(this, "bad sad = "+first_time_crisis, Toast.LENGTH_SHORT).show();
            save_stuff("my_stats.txt","1");// dont make it a 0 lol
            first_time_crisis = "1";
        }
        int temp_stats = Integer.parseInt(first_time_crisis);
        temp_stats = temp_stats +1;
        save_stuff("my_stats.txt",String.valueOf(temp_stats));
        Button obj_but_all_time =(Button)findViewById(R.id.but_all_time);
        obj_but_all_time.setText(String.valueOf(temp_stats));
    }

    public void check_files_exist(){
        String error_yes_no = "0";

        error_yes_no =  read_stuff("my_stats.txt");
        if(error_yes_no =="error"){save_stuff("my_stats.txt","1");}

        error_yes_no =  read_stuff("time_interval.txt");
        if(error_yes_no =="error"){save_stuff("time_interval.txt","1");}

        error_yes_no =  read_stuff("power_challenge_list.txt");
        if(error_yes_no =="error"){save_stuff("power_challenge_list.txt","1");}

        // error_yes_no =  read_stuff("my_stats.txt");
        // if(error_yes_no =="error"){save_stuff("my_stats.txt","1");}
    }


    //button clicks------------------------------------------------------------------
    public void buttonOnClick(View view)
    {
        int the_id = view.getId();
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
        if(the_id == but_sub_count){
            //Toast.makeText(this, "but_sub_count", Toast.LENGTH_SHORT).show();
            //pause_play_subcount =! pause_play_subcount;
            Button obj_but_sub_count=(Button)findViewById(R.id.but_sub_count);
            //alarm_on_off =! alarm_on_off;
            pause_play_subcount =! pause_play_subcount;

        }
        if(the_id == R.id.but_full_count){
            Toast.makeText(this, "Button is disabled to prevent multiple timer runs", Toast.LENGTH_SHORT).show();
            //CountDownTimer.cancel();
            // myButton.setEnabled(false)
            Button my_but=(Button)findViewById(R.id.but_full_count);
            my_but.setEnabled(false);//disable button
            my_but.setTextColor(Color.parseColor("#0252d3"));
            timer_go();
            Toast.makeText(this, "****************", Toast.LENGTH_SHORT).show();
        }

        if(the_id == R.id.but_count_all){
            //Toast.makeText(this, "but_count_all", Toast.LENGTH_SHORT).show();


            t1.setLanguage(Locale.CHINESE);
            speak_next_phrase();
        }

        if(the_id == toggle_on_off){
            // Toast.makeText(this, "toggle_on_off", Toast.LENGTH_SHORT).show();
            ToggleButton my_toggle = (ToggleButton) findViewById(toggle_on_off);
            if (!my_toggle.isChecked()){
                Toast.makeText(this, ""+"toggle is on", Toast.LENGTH_SHORT).show();
                ListView my_list =(ListView)findViewById(list_id); my_list.setVisibility(GONE);
                //Button toggleButton_obj = (Button)findViewById(R.id.but_full_count);toggleButton_obj.setVisibility(GONE);

                //SeekBar obj_seekBar = (SeekBar) findViewById(R.id.seekBar); obj_seekBar.setVisibility(GONE);
                //SeekBar obj_seekBar2 = (SeekBar) findViewById(R.id.seekBar2); obj_seekBar2.setVisibility(GONE);
                TextView obj_text_multi = (TextView)findViewById(R.id.edit_text_multi);obj_text_multi.setVisibility(View.VISIBLE);
            }
            if (my_toggle.isChecked()){
                Toast.makeText(this, ""+"toggle is off", Toast.LENGTH_SHORT).show();
                ListView my_list =(ListView)findViewById(list_id); my_list.setVisibility(View.VISIBLE);
                //  SeekBar obj_seekBar = (SeekBar) findViewById(R.id.seekBar); obj_seekBar.setVisibility(View.VISIBLE);
                // SeekBar obj_seekBar2 = (SeekBar) findViewById(R.id.seekBar2); obj_seekBar2.setVisibility(View.VISIBLE);

                TextView obj_text_multi = (TextView)findViewById(R.id.edit_text_multi);obj_text_multi.setVisibility(GONE);
            }
        }
//        if(the_id == but_iterate){
//            Toast.makeText(this, "but_iterate", Toast.LENGTH_SHORT).show();
//
////        ClipboardManager myClipboard;
////        ClipData myClip;
//            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
//            ClipData abc = myClipboard.getPrimaryClip();
//            ClipData.Item item = abc.getItemAt(0);
//            String clip_text = item.getText().toString();
//            Toast.makeText(this, ""+clip_text, Toast.LENGTH_SHORT).show();
//        }



//    if(the_id == R.id.xxx){Toast.makeText(this, "write_txt_file", Toast.LENGTH_SHORT).show();}
//    if(the_id == R.id.xxx){Toast.makeText(this, "write_txt_file", Toast.LENGTH_SHORT).show();}
//    if(the_id == R.id.xxx){Toast.makeText(this, "write_txt_file", Toast.LENGTH_SHORT).show();}
    }
    String lang_now = "ENGLISH";
    public void speak_this(String words_to_say){
        if (lang_now == "ENGLISH") {t1.setLanguage(Locale.ENGLISH);}
        if (lang_now == "CHINESE") {t1.setLanguage(Locale.CHINESE);}
        if (lang_now == "KOREAN") {t1.setLanguage(Locale.KOREAN);}

        t1.speak(words_to_say, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(this, ""+words_to_say, Toast.LENGTH_SHORT).show();

    }
    //public void speak_chinese(String words_to_say){t2.speak(words_to_say, TextToSpeech.QUEUE_FLUSH, null);
    //
    //Switch switch_x = (Switch) findViewById(R.id.switch1);
    //switch_x.setOnChage

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position_x, long id) {
        //Toast.makeText(this, array_x[position_x], Toast.LENGTH_SHORT).show();
        String item_text=(String)parent.getItemAtPosition(position_x);
        Toast.makeText(this, ""+item_text, Toast.LENGTH_SHORT).show();

        //String send_to_speak_this = ed1.getText().toString();
        speak_this(item_text);

        if (position_x == 0) {
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
        }
//        if (i == 1) {
//            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
//end

//    public void iteration_control(){
//
//        //Random my_random = new Random();
//        //int int_rnd= my_random.nextInt(100);
//        //int g_iteration_count = 1;
//        //int g_iteration_count_holder;
//        g_iteration_count = g_iteration_count -1;
//        if (g_iteration_count <= -1){
//            g_iteration_count = g_iteration_count_holder;
//            Toast.makeText(this, "resetting !!", Toast.LENGTH_SHORT).show();
//            pause_play_subcount = false;
//            MediaPlayer.create(getApplicationContext(), R.raw.door_bell).start();
//            MediaPlayer.create(getApplicationContext(), R.raw.door_chime).start();
//        }
//
//
//        Button obj_but_iterate = (Button)findViewById(but_iterate);
//       // obj_but_iterate.setText(String.valueOf(g_iteration_count));
//
////         if (global_cycle_count <= -1){
////             //stop counter
////             pause_play_subcount = false;
////         }
////
////     }
//    }

    int abc = 0;
    public void do_x(){
        abc = abc +1;
        Button obj_b1= (Button)findViewById(R.id.but_sub_count);
        obj_b1.setText(String.valueOf(abc));
        speak_this("3 things");

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 300 milliseconds
        v.vibrate(100);

        //if(mp!=null) { mp.release(); }
        // mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);
        // mp.start();
    }

    public void play_sound(String sound_to_play){
        //if (sound_to_play == "just_cant_get_enough"){then play that sound}
        if(mp!=null) { mp.release(); }
        mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);mp.start();

        if(mp!=null) { mp.release(); }
        if(sound_to_play == "just_cant_get_enough"){
        mp = MediaPlayer.create(getApplicationContext(), R.raw.just_cant_get_enough);mp.start();}
        if(sound_to_play == "end_fx"){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.end_fx);mp.start();}
        if(sound_to_play == "skype_feedback"){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.skype_feedback);mp.start();}
        if(sound_to_play == "sms_alert_4"){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.sms_alert_4);mp.start();}

    }

}
//end
// Button obj_but_today =(Button)findViewById(R.id.but_today);
// obj_but_today.setText(error_yes_no);





