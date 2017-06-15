package com.example.laowai.powerpics4;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static android.R.attr.password;
import static android.R.id.message;
import static android.text.TextUtils.substring;
import static com.example.laowai.powerpics4.R.raw.skype_feedback;


public class Main4Activity extends AppCompatActivity {
//internet permissions
private WebView wv1;
String url_1 = "http://dreamgoals.info/team_power/self_talk.php";
String url_2 = "http://dreamgoals.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv_from_web_x= (TextView) findViewById(R.id.tv_from_web);
        tv_from_web_x.setMovementMethod(new ScrollingMovementMethod());


        //web
//internet permissions
//private WebView wv1;
//String url_1 = "http://dreamgoals.info";
//String url_2 = "http://dreamgoals.info";
        wv1=(WebView)findViewById(R.id.web_view_4);
//wv1.setWebViewClient(new MyBrowser()); ?????????????????????????
        ///!@#$%
        wv1.setWebChromeClient(new WebChromeClient());
        wv1.getSettings().setJavaScriptEnabled(true);
		wv1.loadUrl(url_1);
        wv1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                do_when_page_loaded();
                //super.onPageFinished(view, url);
            }
        });
//----------------



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//button clicks------------------------------------------------------------------
    public void but_click_act_4(View view) {
        int the_id = view.getId();
        //if (the_id == R.id.check_size) {
        // Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();}
        if (the_id == R.id.but_req) {
            Toast.makeText(this, "but_req", Toast.LENGTH_SHORT).show();
            play_sound("end_fx");
        }
        if (the_id == R.id.but_load) {
        Toast.makeText(this, "but_load", Toast.LENGTH_SHORT).show();
        play_sound("door_bell");
            load_data_to_tv();
        }
        if (the_id == R.id.but_save_act_4) {
            Toast.makeText(this, "but_save_act_4", Toast.LENGTH_SHORT).show();
            play_sound("sms_alert_4");
                        TextView tv_from_web_x = (TextView) findViewById(R.id.tv_from_web);
            save_stuff("power_challenge_list.txt",tv_from_web_x.getText().toString());
        }
    }
    //end button clicks===================
    static final int READ_BLOCK_SIZE = 100;
//save_stuff("setting_1.txt",last_login.getText().toString());
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




  //=============
      public void do_when_page_loaded() {
          String web_url = wv1.getUrl();
          Toast.makeText(this, web_url+"\n IS LOADED NOW!!!!", Toast.LENGTH_SHORT).show();

          //new post_to_log().execute();
          //Toast.makeText(this, web_url+"\n IS LOADED NOW!!!!", Toast.LENGTH_SHORT).show();

      }
//-----------
//var str = document.body.textContent

public void load_data_to_tv(){
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          wv1.evaluateJavascript(
              //"(function() { return (document.getElementById(\"span_top\").innerHTML); })();",
             "(function() { return (document.getElementById(\"textarea_1\").innerHTML); })();",
               new ValueCallback<String>() {
            @Override public void onReceiveValue(String html_from_page) {

     html_from_page = html_from_page.replace("\\n", "\n"); // The first backslash is doubled to find actual backslashes
     html_from_page= html_from_page.substring(0, (html_from_page.length() - 1));
     html_from_page = html_from_page.substring(1);

                TextView tv_from_web_x = (TextView)findViewById(R.id.tv_from_web);
            tv_from_web_x.setText(""+html_from_page);
            }
          });
        }
        }

MediaPlayer mp;
    public void play_sound (String sound_to_play){
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
        if(sound_to_play.equals("door_bell")){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.door_bell);mp.start();}
    }
}
