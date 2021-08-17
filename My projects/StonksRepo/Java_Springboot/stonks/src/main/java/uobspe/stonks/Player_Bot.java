package uobspe.stonks;
import uobspe.stonks.Trade_Bot;

// Give a team a sum of money and let them create the number of bots that they want [...]
// [...] as long as the sum of money of all their bots added is == money. (Max 100 bots)

public class Player_Bot extends Trade_Bot{

    private final String botName;
    private final String teamName;
    private final String teamCode;

    public Player_Bot(float money, Integer id, String teamName, String teamCode, String botName, Player_Bot_Management_System system) {
        super(money, id);
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.botName = botName;
        system.pushPlayerBot(this);
    }
    public String getBotName(){ return botName; }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamCode() {
        return teamCode;
    }
}
