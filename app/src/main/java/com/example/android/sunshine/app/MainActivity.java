package com.example.android.sunshine.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String LOG_TAG = MainActivity.class.getSimpleName();

    private void showMap(Uri zipCode){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(zipCode);

        if(intent.resolveActivity(this.getPackageManager()) != null){
            startActivity(intent);
        }else{
            View view = getCurrentFocus();
            Snackbar.make(view, "No Map application found", Snackbar.LENGTH_SHORT).setAction("", null).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.v(LOG_TAG, "onCreate()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(LOG_TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "onPause()");
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
//        int duration = Toast.LENGTH_SHORT;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Toast toast = Toast.makeText(context, "Settings Pressed", duration);
//            toast.show();

            Intent intent = new Intent(context, SettingsActivity.class);
            startActivity(intent);

            return true;
        }else if(id == R.id.action_map_view){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
            String zipCode = preferences.getString(getString(
                    R.string.pref_location_key),
                    getString(R.string.pref_location_default));


            Uri geolocation = Uri.parse("geo:0,0?").buildUpon()
                    .appendQueryParameter("q", zipCode)
                    .build();

            showMap(geolocation);
        }

        return super.onOptionsItemSelected(item);
    }
}
