package uobspe.stonks;

import java.util.HashMap;
import java.util.Map;


public class Trade_Bot {

    private float money;
    private Map<Stonk, Integer> ownedStonks;
    private final Integer id;

    public Trade_Bot(float money, Integer id) {
        this.money = money;
        this.ownedStonks = new HashMap<>();
        this.id = id;
    }

    public Map<Stonk, Integer> getOwnedStonks() {
        return ownedStonks;
    }

    public Integer getName() {
        return id;
    }

    public float getMoney() {
        return money;
    }

    //Add the stonks bought by the bot AND reduces the bot's money (returns false if the bot doesn't have enough money).
    //Creates and adds a recipt to the stock market
    public boolean addNumberOfStonk(Stonk stonk, Integer numberOfStonks, Stonk_Market market){
        float valueOfStonks = numberOfStonks * stonk.getValue();
        if(money < valueOfStonks)
            return false;

        money = money - numberOfStonks * stonk.getValue();
        market.addRecpit(new Recipt(getBotName(), stonk.getName(), numberOfStonks, valueOfStonks, true));

        if(ownedStonks.containsKey(stonk))
            ownedStonks.put(stonk, ownedStonks.get(stonk) + numberOfStonks);
        else
            ownedStonks.put(stonk, numberOfStonks);
        return true;
    }

    //Subtract the stonks sold by the bot (returns false if the bot did not have enough stonks)
    //AND increases the bot's money
    public boolean reduceNumberOfStonks(Stonk stonk, Integer numberOfStonks, Stonk_Market market){
        if(!ownedStonks.containsKey(stonk) || (ownedStonks.get(stonk) < numberOfStonks))
            return false;

        float valueOfStonks = stonk.getValue() * numberOfStonks;
        if(ownedStonks.get(stonk) - numberOfStonks == 0)
            ownedStonks.remove(stonk);
        else
            ownedStonks.put(stonk, ownedStonks.get(stonk) - numberOfStonks);

        money = money + valueOfStonks;
        market.addRecpit(new Recipt(getBotName(), stonk.getName(), numberOfStonks, valueOfStonks, false));
        return true;
    }

    // return the total value of a Trade_Bot (all it's money and the value of all the owned stonks)
    float getTotalValue(){
        float sum = 0;

        for(Map.Entry<Stonk, Integer> x : ownedStonks.entrySet()){
            // If a stonk has 0, or negative value it is not going to be added to the total value
            if(x.getKey().getValue() > 0)
                sum = sum + (x.getValue() * x.getKey().getValue());
        }

        return sum + this.money;
    }

    String getBotName(){
        return "Automatic Trade Bot No." + id.toString();
    }

}
