package xyz.koldfusion.apextracker;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tracker {
    public Integer get_current_player_count() {
        return current_player_count;
    }

    private Integer current_player_count;

    public Integer get_current_squad_count() {
        return current_squad_count;
    }

    private Integer current_squad_count;

    public Integer get_players_alive_on_your_squad() {
        return players_alive_on_your_squad;
    }

    private Integer players_alive_on_your_squad;

    public Tracker (){
        // Set some easy defaults
        current_player_count = 15;
        current_squad_count = 5;
        players_alive_on_your_squad = 3;
    }

    public void increment_player_count(){
        current_player_count ++;
    }
    public void decrement_player_count(){
        current_player_count --;
        if (current_player_count < 2){
            // Can only have min 2 players alive before a game ends
            // 1 per squad, 2 squads alive
            current_player_count = 2;
        }
    }

    public void increment_squad_count(){
        current_squad_count ++;
    }
    public void decrement_squad_count(){
        current_squad_count --;
        if (current_squad_count < 2){
            // If this happens you should have won
            current_squad_count = 2;
        }
    }

    public void increment_self_alive_count(){
        players_alive_on_your_squad ++;
        if (players_alive_on_your_squad > 3){
            // Can't have more then 3
            players_alive_on_your_squad = 3;
        }
    }
    public void decrement_self_alive_count(){
        players_alive_on_your_squad --;
        if (players_alive_on_your_squad < 1){
            // Cant have less then 1 alive
            players_alive_on_your_squad = 1;
        }
    }

    /**
     * Maths stuff out and figures out what the best combination
     * of squads to players is before returning a map of
     * expected team compositions
     */
    public ArrayList<SumCounter> get_expected_squad_compositions(){
        Integer player_count = current_player_count;
        Integer squad_count = current_squad_count;

        player_count -= players_alive_on_your_squad;
        squad_count --;

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < squad_count; i++){
            // Add all the options into numbers
            // so we can figure out what adds up
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
        }

        HashSet<SumCounter> answers = sum_up_recursive(numbers, player_count, new ArrayList<Integer>(), new HashSet<SumCounter>());
        ArrayList<SumCounter> valid_counts = new ArrayList<>();
        for (SumCounter counter : answers){
            if (counter.size() == squad_count) {
                valid_counts.add(counter);
            }
        }
        return valid_counts;
    }

    private HashSet<SumCounter> sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial, HashSet<SumCounter> output) {
        int s = 0;
        for (int x : partial)
            s += x;
        if (s == target)
            output.add(new SumCounter(partial));
        if (s >= target)
            return output;
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) remaining.add(numbers.get(j));
            ArrayList<Integer> partial_rec = new ArrayList<>(partial);
            partial_rec.add(n);
            sum_up_recursive(remaining, target, partial_rec, output);
        }
        return output;
    }
}
