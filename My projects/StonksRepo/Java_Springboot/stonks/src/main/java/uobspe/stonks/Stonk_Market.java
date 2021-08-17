package uobspe.stonks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Stonk_Market {
    private List<Stonk> aliveStonks;
    private List<Stonk> deletedStonks; // Might have some functionality later
    private List<Recipt> transactionHistory;

    public Stonk_Market() {
        aliveStonks = new ArrayList<>();
        transactionHistory = new ArrayList<>();
        deletedStonks = new ArrayList<>();
        Website_Interface_Bean.stonkMarket = this;
        Player_Interface_Bean.stonkMarket = this;
    }

    public List<Stonk> getAliveStonks() {
        return aliveStonks;
    }

    public List<Stonk> getDeletedStonks() {
        return deletedStonks;
    }

    public List<Stonk> getAllStonks(){
        List<Stonk> allStonks = new ArrayList<>(aliveStonks);
        allStonks.addAll(deletedStonks);
        return allStonks;
    }

    public List<Recipt> getTransactionHistory() {
        return transactionHistory;
    }
    public void addRecpit(Recipt transaction){
        transactionHistory.add(transaction);
    }

    // Reades a CSV file and creates the stonks that are in it.
    // The CSV file should have the following format: Stonk_Name;Stonk_Value\n
    public void createStonksFromCSV(File fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                List<String> attr = Arrays.asList(linie.split(";"));
                aliveStonks.add(new Stonk(attr.get(0), Float.parseFloat(attr.get(1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns a random change (with random length and value)
    public void getRandomChanges(){
        Random rand = new Random();
        for(Stonk x : aliveStonks){
            x.queueChange(rand.nextInt(10) + 1, (float) (Math.random() * (201 - (-200) + 1) + (-200)));
        }
    }

    public Stonk getAliveStonkWithName(String name){
        for(Stonk x : aliveStonks){
            if(x.getName().equals(name))
                return x;
        }
        return null;
    }

    public Stonk getStonkWithName(String name){
        for(Stonk x : aliveStonks){
            if(x.getName().equals(name))
                return x;
        }
        for(Stonk x : deletedStonks){
            if(x.getName().equals(name))
                return x;
        }
        return null;
    }

    // Makes one change for all the stonks (if there is a change avalaible)
    public void updateMarket(){
        List<Stonk> toDelete = new ArrayList<>();
        for(Stonk x : aliveStonks){
            if(x.getValue()<=0)
                toDelete.add(x);
            x.applyChanges();
        }

        // deletes all the stonks with negative values
        for(Stonk x : toDelete){
            x.setValue(0);
            aliveStonks.remove(x);
            if(!deletedStonks.contains(x)) // adds the deleted stonks to the deleted array for later use
                deletedStonks.add(x);
        }
    }

}
