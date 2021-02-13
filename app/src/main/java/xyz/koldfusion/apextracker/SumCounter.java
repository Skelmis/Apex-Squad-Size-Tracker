package xyz.koldfusion.apextracker;

import java.util.ArrayList;
import java.util.Objects;

public class SumCounter {
    private Integer one_count = 0;
    private Integer two_count = 0;
    private Integer three_count = 0;

    public SumCounter(ArrayList<Integer> inputs){
        for (Integer i : inputs){
            if (i.equals(1)) one_count++;
            else if (i.equals(2)) two_count++;
            else three_count++;
        }
    }

    public ArrayList<Integer> as_array(){
        ArrayList<Integer> return_data = new ArrayList<>();
        for (int i=0; i < one_count; i++){
            return_data.add(1);
        }
        for (int i=0; i < two_count; i++){
            return_data.add(2);
        }
        for (int i=0; i < three_count; i++){
            return_data.add(3);
        }
        return return_data;
    }

    public int size(){
        return one_count + two_count + three_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumCounter that = (SumCounter) o;
        return one_count.equals(that.one_count) &&
                two_count.equals(that.two_count) &&
                three_count.equals(that.three_count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one_count, two_count, three_count);
    }

    @Override
    public String toString() {
        return "SumCounter{" +
                "one_count=" + one_count +
                ", two_count=" + two_count +
                ", three_count=" + three_count +
                '}';
    }
}
