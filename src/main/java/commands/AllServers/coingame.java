package commands.AllServers;

import Filehandling.Data;
import Filehandling.Date;
import Filehandling.Filehandler;
import core.CommandProcessor;
import core.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class coingame extends CommandProcessor {

    private int[] fibNums = new int[48];
    // 0th index is 0 heads, 1st index is 1 head, etc.

    private static final int gamecost = 1;

    public coingame(){
        cmd = "coingame";
        help = "Coin game! The score is the x-th fibonacchi number, minus " + gamecost + ".\n";
        setCategory("TextIO");

        fibNums[1] = 1;
    }

    @Override
    protected CommandData UpdateCommandData(CommandData data) {
        data.addOption(OptionType.INTEGER, "data",
                "Set to 1 to get coingame data about yourself", false);

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        OptionMapping optionMapping = event.getOption("data");
        long getData;

        if(optionMapping == null){
            getData = 0;
        }
        else{
            getData = optionMapping.getAsLong();
        }

        int optin = isOptedIn(event.getUser());

        if(getData > 0){
            if(optin == 0){
                String out = "";
                out += "Please consult /data to opt in or out of storing data.\n";
                out += "For now, you've been automatically opted out.\n";
                event.reply(out).queue();
                return;
            } else if(optin == -1){
                event.reply("You're opted out, we cannot access data.").queue();
                return;
            }

            Data d = Filehandler.getUserData(event.getUser());

            int PB = Integer.parseInt(d.getData("coin game - personal best"));
            int YearlyBest = Integer.parseInt(d.getData("coin game - yearly best"));
            int MonthlyBest = Integer.parseInt(d.getData("coin game - monthly best"));
            int DailyBest = Integer.parseInt(d.getData("coin game - daily best"));

            String out = "";
            out += "Your personal best is " + PB + "\n";
            out += "Your best score this year is " + YearlyBest + "\n";
            out += "Your best score this month is " + MonthlyBest + "\n";
            out += "Your best score today is " + DailyBest + "\n";
            event.reply(out).queue();
            return;
        }

        String out = "";

        if(optin == 0){
            out += "Sorry to interrupt your coingame, but you haven't opted in or out of saving data.\n";
            out += "For now, I'm automatically opting you out of saving data. Opt in using \"/data optin\"";
            event.reply(out).queue();
            return;
        }

        int heads = 0;
        while(Main.rand.nextInt(2 - 1 + 1) + 1 != 1){
            // max is 2, min is 1
            heads++;
        }

        // data stuff
        if(optin == 1){
            Data d = Filehandler.getUserData(event.getUser());
            // coingame-last played
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
                out += "See all your best scores with /coingame 1\n";
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
                        out += "See all your best scores with /coingame 1\n";
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
                                out += "See all your best scores with /coingame 1\n";
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
                                        out += "See all your best scores with /coingame 1\n";
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
            event.reply(out + "You got 47 heads in a row. Or more. ***I'm not designed for that many heads in a row to happen.***").queue();
            return;
        }
        winnings -= gamecost;
        out += "You got " + heads + " heads in a row, giving you a score of " + winnings + "\n";
        // zero heads is 1/2, one is 1/4, two is 1/8, three is 1/16, four is 1/32
        // five is 1/64, six is 1/128, seven is 1/256, eight is 1/512, nine is 1/1024
        if(heads >= 7 && optin == -1){
            out += "Wow! Six heads is a 1/128 chance, and you got " + heads + " heads!\n";
            out += "If you want me to save this data (next time), use /data optin.\n";
        }
        event.reply(out).queue();
    }

    private int fib(int heads){
        if(heads >= 47){
            return Integer.MAX_VALUE;
        }
        if(heads <= 0)
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
