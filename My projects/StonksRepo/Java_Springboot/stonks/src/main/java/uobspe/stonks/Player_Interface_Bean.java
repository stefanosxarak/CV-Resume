package uobspe.stonks;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@Controller
@CrossOrigin(origins = "*")
public class Player_Interface_Bean {
    //Set these in the main function
    public static Trade_Bot_Management_System botManager;
    public static Player_Bot_Management_System playerManager;
    public static Stonk_Market stonkMarket;


    //Returns "true" or "false" based on if that bot name is OK for them to use
    //reject if currently in use, contains characters that aren't a-z, 0-9, or space
    //Maybe think about catching swear words or whatever but you don't need to
    @GetMapping("/is_bot_name_valid")
    @ResponseBody
    public String isBotNameIsUse(@RequestParam String BotName){

        if (playerManager.getPlayerNames().contains(BotName)){
            return "false";
        }

        String str = BotName.toLowerCase();
        char[] botToChar = str.toCharArray();
        for (int i = 0; i < botToChar.length; i++) {
            char ch = botToChar[i];
            if (!((ch >= 'a' && ch <= 'z') || ch == ' ' || (Character.getNumericValue(ch) >=0 && Character.getNumericValue(ch) <= 9)))  {
                return "false";
            }
        }
        return "";
    }

    //Add a bot with the given team name, code, etc to bot manager and player manager
    @GetMapping("/add_bot")
    @ResponseBody
    public void addBot(@RequestParam String TeamName, @RequestParam String TeamCode, @RequestParam String BotName){
        Player_Bot bot = new Player_Bot(10000,botManager.getLiveBots().size()+1,TeamName, TeamCode,BotName,playerManager );
        playerManager.pushPlayerBot(bot);
        botManager.addBot(bot);
    }

    //Return information about the bot. How much money to they have? What value do they have?
    //Which stonks and how many? So far in this project, '#' deliniates sections and ',' for seperating values
    //An example return would be "1023.55#1782.98#Apple,4#Samsung,3"
    @GetMapping("/get_bot_info")
    @ResponseBody
    public String getBotInfo(@RequestParam String TeamName, @RequestParam String TeamCode, @RequestParam String botName){
        String Output ="";
        for (Player_Bot bot: playerManager.getPlayers()) {
            if (TeamName.equals(bot.getTeamName()) && TeamCode.equals(bot.getTeamCode()) && botName.equals(bot.getBotName())) {
                Output = String.valueOf(bot.getMoney()) + "#" + String.valueOf(bot.getTotalValue()) + "#";
                for (Map.Entry<Stonk, Integer> entry : bot.getOwnedStonks().entrySet()) {
                    Output = Output + entry.getKey().getName().toString() + "," + entry.getValue().toString() + "#";
                }
            }
        }

        return Output;
    }

    //Also functions for buying and selling
    @GetMapping("/buy_stonks")
    @ResponseBody
    public String buyStonks(@RequestParam String TeamName, @RequestParam String TeamCode, @RequestParam String botName, @RequestParam String stonkName,@RequestParam String stonkAmount){
        for(Player_Bot bot : playerManager.getPlayers()) {
            if (bot.getBotName().equals(botName) && bot.getTeamCode().equals(TeamCode) && bot.getTeamName().equals(TeamName)){
                for(Stonk stonk : stonkMarket.getAllStonks()) {
                    if (stonkName.equals(stonk.getName())) {
                        if (bot.addNumberOfStonk(stonk, Integer.valueOf(stonkAmount), stonkMarket)){
                            return (stonkAmount + " of " + stonkName + " bought");
                        }
                        else{
                            return ("Insufficient Funds");
                        }
                    }
                }
            }

        }
        return "";
    }


    @GetMapping("/sell_stonks")
    @ResponseBody
    public String sellStonks(@RequestParam String TeamName, @RequestParam String TeamCode, @RequestParam String botName, @RequestParam String stonkName,@RequestParam String stonkAmount){
        for(Player_Bot bot : playerManager.getPlayers()) {
            if (bot.getBotName().equals(botName) && bot.getTeamCode().equals(TeamCode) && bot.getTeamName().equals(TeamName)){
                for(Stonk stonk : stonkMarket.getAllStonks()) {
                    if (stonkName.equals(stonk.getName())) {
                        if (bot.reduceNumberOfStonks(stonk, Integer.valueOf(stonkAmount), stonkMarket)){
                            return (stonkAmount + " of " + stonkName + " sold");
                        }
                        else{
                            return ("Insufficient Stonks");
                        }
                    }
                }
            }

        }
        return "";
    }
    //@ResponseBody


    /*
        In terms of testing, once this is all done you can run the Java from springboot, then run index.html from
        the website section by double clicking it. Go to the leaderboard section and you will be able to see the
        current players. By typing requests into your web browser in a new tab (ie "localHost:8080/add_bot?etcetc")
        you should be able to see the new player appear on the website. If you start buying stock, its value should change
        over time based on what you see in the market tab. If you sell that stock, the value should stop changing
     */

    //Returns the price of a single stonk
    @GetMapping("/get_stonk_price")
    @ResponseBody
    public String getStonkPrice(@RequestParam String stonkName){
        Stonk s = stonkMarket.getStonkWithName(stonkName);
        if(s != null) return Float.toString(s.getValue());
        else return "0";
    }

    //Returns a list of past values
    @GetMapping("/get_stonk_history")
    @ResponseBody
    public String getStonkHistory(@RequestParam String stonkName, @RequestParam String historyLength){
        Stonk s = stonkMarket.getStonkWithName(stonkName);
        if(s == null) return "0";

        ArrayList<Float> history = new ArrayList<>(s.getHistory());
        int count = 0;
        int historyLengthRequested = Integer.parseInt(historyLength);
        String toReturn = "";
        if(history.size() > historyLengthRequested)
            count = history.size() - historyLengthRequested;
        for(;count < history.size()-1; count++){
            toReturn += Float.toString(history.get(count)) + ",";
        }
        toReturn += Float.toString(history.get(history.size() - 1));

        return toReturn;
    }

    //Returns a list of all stonks and their current prices
    @GetMapping("/get_stonk_list")
    @ResponseBody
    public String getStonkList(){
        ArrayList<Stonk> stonks = new ArrayList<>(stonkMarket.getAliveStonks());
        String toReturn = "";
        for(Stonk s : stonks){
            toReturn += s.getName() + "," + Float.toString(s.getValue()) + "#";
        }

        //Get rid of that last '#'
        toReturn.stripTrailing();
        return toReturn;
    }
}