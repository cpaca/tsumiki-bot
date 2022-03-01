package commands.AllServers;

import Filehandling.Data;
import Filehandling.Date;
import Filehandling.Filehandler;
import core.Command;
import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Month;
import java.time.Year;

public class coingame extends Command {

    private int[] fibNums = new int[48];
    // 0th index is 0 heads, 1st index is 1 head, etc.

    private static final int gamecost = 1;

    public coingame(){
        cmd = "coingame";
        help = "Coin game! The score is the x-th fibonacchi sequence, subtracted by " + gamecost + ".\n" +
                "Use ``!coingame get data`` to get statistics about your coingames (e.g. best score today)";
        setCategory("TextIO");

        fibNums[1] = 1;
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.equalsIgnoreCase("get data")){
            Data d = Filehandler.getUserData(event.getAuthor());

            int PB = Integer.parseInt(d.getData("coin game - personal best"));
            int YearlyBest = Integer.parseInt(d.getData("coin game - yearly best"));
            int MonthlyBest = Integer.parseInt(d.getData("coin game - monthly best"));
            int DailyBest = Integer.parseInt(d.getData("coin game - daily best"));

            String out = "";
            out += "Your personal best is " + PB + "\n";
            out += "Your best score this year is " + YearlyBest + "\n";
            out += "Your best score this month is " + MonthlyBest + "\n";
            out += "Your best score today is " + DailyBest + "\n";
            event.getChannel().sendMessage(out).queue();
            return;
        }

        int heads = 0;
        while(Main.rand.nextInt(2 - 1 + 1) + 1 != 1){
            // max is 2, min is 1
            heads++;
        }
        // data stuff
        String out = "";

        if(isOptedIn(event.getAuthor(), event.getChannel())){
            Data d = Filehandler.getUserData(event.getAuthor());
            // coingame-last playe
            Date lastGameDay = new Date(d.getData("coin game - last played day"));
            Date today = new Date();

            int PB = Integer.parseInt(d.getData("coin game - personal best"));
            int YearlyBest = Integer.parseInt(d.getData("coin game - yearly best"));
            int MonthlyBest = Integer.parseInt(d.getData("coin game - monthly best"));
            int DailyBest = Integer.parseInt(d.getData("coin game - daily best"));

            if(lastGameDay.isDefault()){
                // First ever roll, do not give them alerts.
                PB = heads;
                YearlyBest = heads;
                MonthlyBest = heads;
                DailyBest = heads;
            }
            else if(heads > PB){
                out += "**__New Personal best!__** Previous best score: " + PB + " heads\n";
                PB = heads;
                YearlyBest = heads;
                MonthlyBest = heads;
                DailyBest = heads;
            }
            else{
                if(lastGameDay.sameYear(today)){
                    // same year
                    if(heads > YearlyBest){
                        out += "**Yearly PB!** Previous best score of the year: " + YearlyBest + " heads\n";
                        YearlyBest = heads;
                        MonthlyBest = heads;
                        DailyBest = heads;
                    }
                    else{
                        if(lastGameDay.sameMonth(today)){
                            // same month
                            if(heads > MonthlyBest){
                                // best score of the month
                                out += "Best score of the month! Previous best score of the month: " + MonthlyBest + " heads\n";
                                MonthlyBest = heads;
                                DailyBest = heads;
                            }
                            else{
                                // not best score of the month
                                if(lastGameDay.sameDay(today)){
                                    // today
                                    if(heads > DailyBest){
                                        // best score of the day
                                        out += "Best score today! Previous best score today: " + DailyBest + " heads\n";
                                        DailyBest = heads;
                                    }
                                }
                                // No message for new day.
                            }
                        }
                        else{
                            // different month
                            out += "New month. Previous month's best score: " + MonthlyBest + " heads\n";
                            MonthlyBest = heads;
                            DailyBest = heads;
                        }
                    }
                }
                else{
                    // different year
                    out += "New year. Previous year's best score: " + YearlyBest + " heads\n";
                    YearlyBest = heads;
                    MonthlyBest = heads;
                    DailyBest = heads;
                }
            }

            d.setData("coin game - last played day", today.toString());
            d.setData("coin game - personal best", PB);
            d.setData("coin game - yearly best", YearlyBest);
            d.setData("coin game - monthly best", MonthlyBest);
            d.setData("coin game - daily best", DailyBest);
            Filehandler.saveData(d);
        }

        // winnings & telling user
        int winnings = fib(heads);
        if(heads >= 47){
            event.getChannel().sendMessage(out + "You got 47 heads in a row. Or more. ***I'm not designed for that many heads in a row to happen.***").queue();
            return;
        }
        winnings -= gamecost;
        event.getChannel().sendMessage(out + "You got " + heads + " heads in a row, giving you a score of " + winnings).queue();
    }

    private int fib(int heads){
        if(heads >= 47){
            return Integer.MAX_VALUE;
        }
        if(heads == 0)
            return 0;
        if(fibNums[heads] == 0){
            if(heads == 1) {
                System.out.println("Unexpected fibonacchi sequence error inside command CoinGame, of All Server Listener.");
            }
            fibNums[heads] = fib(heads-1) + fib(heads-2);
        }
        return fibNums[heads];
    }
}
