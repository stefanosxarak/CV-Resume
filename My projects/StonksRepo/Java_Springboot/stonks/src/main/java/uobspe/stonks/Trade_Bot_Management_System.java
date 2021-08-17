package uobspe.stonks;
import uobspe.stonks.Trade_Bot;

import java.util.ArrayList;
import java.util.List;

public class Trade_Bot_Management_System {

    private final Integer defaultMoney = 13000;
    private List<Trade_Bot> liveBots;
    private Stonk_Market market;

    public List<Trade_Bot> getLiveBots() {
        return liveBots;
    }

    public Trade_Bot_Management_System(Stonk_Market market) {
        this.market = market;
        Player_Interface_Bean.botManager = this;
        liveBots = new ArrayList<>();
    }

    //Add a new bot to the system
    //@param id is the id to access this bot in the future
    //@param money is the starting amount of money the bot will have
    void addBot(float money, Integer id){
        Trade_Bot botToAdd = new Trade_Bot(money, id);
        liveBots.add(botToAdd);
    }

    //Add a new bot to the system with the default starting money
    //@param id is the id to access this bot in the future
    void addBot(Integer id){
        Trade_Bot botToAdd = new Trade_Bot(defaultMoney, id);
        liveBots.add(botToAdd);
    }

    //@param bot will be added to the system
    void addBot(Trade_Bot bot){
        liveBots.add(bot);
    }

}