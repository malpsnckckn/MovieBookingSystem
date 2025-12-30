package com.moviebooking.services;
import com.moviebooking.models.Snack;
import java.util.ArrayList;
import java.util.List;
public class SnackService {
    private List<Snack> snacks;
    public SnackService(){
        snacks = new ArrayList<>();
        initializeSnacks();
    }
    private void initializeSnacks(){
        snacks.add(new Snack("Popcorn (Small)", 4));
        snacks.add(new Snack("Popcorn (Medium", 6));
        snacks.add(new Snack("Popcorn (Large)", 8));
        snacks.add(new Snack("Water", 15));
        snacks.add(new Snack("Cola", 30));
    }
    public void printSnackMenu(){
        System.out.println("====Snack Menu====");
        System.out.println();
        for(int i = 0; i<snacks.size(); i++){
            System.out.println((i+1) + ". " + snacks.get(i));
        }
        System.out.println("0. Finish snack. Selection");
    }
    public Snack getSnackByIndex(int index){
        if (index > 0 && index <= snacks.size()){
            return snacks.get(index - 1);
        }
        return null;
    }
}
