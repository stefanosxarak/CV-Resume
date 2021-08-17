package uobspe.stonks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StonksApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(StonksApplication.class, args);
		Stonk_Market market = new Stonk_Market();

		market.createStonksFromCSV(new File("data/stonks.csv"));

		Trade_Bot_Management_System bots = new Trade_Bot_Management_System(market);
		bots.addBot(0);
		bots.getLiveBots().get(0).addNumberOfStonk(market.getStonkWithName("Nokia"), 3, market);

		Player_Bot_Management_System playerManager = new Player_Bot_Management_System();
		//Player_Bot playerBot1 = new Player_Bot(10000,1,"Test Team", "asd", "asd");
		Player_Bot playerBot1 = new Player_Bot(10000,1,"Test Team","Code","Testy Lad", playerManager);
		playerManager.pushPlayerBot(playerBot1);

		bots.addBot(playerBot1);
		bots.getLiveBots().get(1).addNumberOfStonk(market.getStonkWithName("Apple"), 5, market);

		int i = 0;

		while(!market.getAliveStonks().isEmpty()){
			for(Stonk x : market.getAllStonks())
				System.out.println(x.toString());
			if(market.getStonkWithName("Nokia") != null){
				System.out.printf("The bot who owns 3 Nokia: ");
				System.out.printf("%.2f == %.2f\n",bots.getLiveBots().get(0).getTotalValue() - bots.getLiveBots().get(0).getMoney(),market.getStonkWithName("Nokia").getValue() * 3);
			}
			if(market.getStonkWithName("Apple") != null){
				System.out.printf("The player who owns 5 Apple: ");
				System.out.printf("%.2f == %.2f\n",bots.getLiveBots().get(1).getTotalValue() - bots.getLiveBots().get(1).getMoney(),market.getStonkWithName("Apple").getValue() * 5);
			}
			if(i > 610) { //Temp to test out lifespan
				TimeUnit.SECONDS.sleep(2);
			}
			if (i%5 == 0)
				market.getRandomChanges();
			market.updateMarket();
			i++;
			System.out.println("\n\n");
		}

	}

}
