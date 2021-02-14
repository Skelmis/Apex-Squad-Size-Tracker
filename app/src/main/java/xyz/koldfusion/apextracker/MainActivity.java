package xyz.koldfusion.apextracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracker = new Tracker();

        update_squad_list();
        update_user_squad_players_alive();
        update_current_player_count();
        update_current_squad_count();
    }

    /**
     * Updates the squad_list ListView
     * with the relevant data from this.tracker
     */
    public void update_squad_list(){
        ListView squad_list_view = (ListView)findViewById(R.id.squad_list);
        TextView text = (TextView)findViewById(R.id.expected_squads_comp_string);
        ArrayList<SumCounter> squad_compositions = tracker.get_expected_squad_compositions();

        if ((int)squad_compositions.size() == 0){
            text.setText(R.string.invalid_numbers);
            squad_list_view.setAdapter(null);
            return;
        }
        else {
            text.setText(R.string.valid_squad_response);
        }

        ArrayList<String> string_squad_compositions = new ArrayList<>();
        for (SumCounter counter : squad_compositions){
            // Make our strings to put on the list view
            string_squad_compositions.add(counter.as_array().toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.list_text_item, string_squad_compositions);
        squad_list_view.setAdapter(arrayAdapter);
    }

    public void update_user_squad_players_alive(){
        Integer players_alive = tracker.get_players_alive_on_your_squad();
        TextView text = (TextView)findViewById(R.id.players_alive_on_squad_text);
        text.setText(players_alive.toString());
    }

    public void update_current_player_count(){
        Integer players_alive = tracker.get_current_player_count();
        TextView text = (TextView)findViewById(R.id.overall_players_alive_text);
        text.setText(players_alive.toString());
    }

    public void update_current_squad_count(){
        Integer squads_alive = tracker.get_current_squad_count();
        TextView text = (TextView)findViewById(R.id.overall_squads_alive_text);
        text.setText(squads_alive.toString());
    }

    // Buttons time!
    public void increment_user_squad_players(View view){
        tracker.increment_self_alive_count();
        update_user_squad_players_alive();
        update_squad_list();
    }
    public void decrement_user_squad_players(View view){
        tracker.decrement_self_alive_count();
        update_user_squad_players_alive();
        update_squad_list();
    }

    public void increment_current_player_count(View view){
        tracker.increment_player_count();
        update_current_player_count();
        update_squad_list();
    }
    public void decrement_current_player_count(View view){
        tracker.decrement_player_count();
        update_current_player_count();
        update_squad_list();
    }

    public void increment_current_squad_count(View view){
        tracker.increment_squad_count();
        update_current_squad_count();
        update_squad_list();
    }
    public void decrement_current_squad_count(View view){
        tracker.decrement_squad_count();
        update_current_squad_count();
        update_squad_list();
    }
}