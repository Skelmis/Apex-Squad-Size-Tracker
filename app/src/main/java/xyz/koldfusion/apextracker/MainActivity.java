package xyz.koldfusion.apextracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracker = new Tracker();
        
        TextView text = (TextView)findViewById(R.id.content_title);
        text.setText("L");

        Log.d("OUTPUTFROMTHIGN","Hi");
        tracker.get_expected_squad_compositions();
        Log.d("OUTPUTFROMTHIGN","Bye");
    }

    /**
     * Updates the squad_list ListView
     * with the relevant data from this.tracker
     */
    public void update_squad_list(){

    }
}