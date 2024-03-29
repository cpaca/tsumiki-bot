package commands.AllServers;

import core.CommandProcessor;
import core.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.*;

public class roll extends CommandProcessor {

    private static final String sortString = "sorted";
    private static final String compactsorted = "compactsorted";
    private static final String compactsorted2 = "cs";

    private static final int MaxDiceSides = 100; // max 99 sided dice, having 999 sided dice made... problems
    private static final int MaxDiceRollable = 1000; // max 999 dice at one time
    private static final int MaxDiceSorted = 1000; // max 1000 sorted dice
    private static final int MaxDiceUnsorted = 100; // max 100 unsorted dice

    private static final long[] ValidChannelIDs = {654597291818221568L, 690814432225525800L, 654597402291994628L};

    public roll(){
        cmd = "roll";
        help = "Rolls dice. Do \"/roll help\" for more detailed information.";
        setCategory("TextIO");
    }

    @Override
    public boolean canUseCommand(MessageReceivedEvent event) {
        long id = event.getChannel().getIdLong();
        for(long validID : ValidChannelIDs){
            if(validID == id){
                return true;
            }
        }
        return super.canUseCommand(event);
    }

    @Override
    protected CommandData UpdateCommandData(CommandData data) {
        data.addOption(OptionType.STRING,"text", "dice to roll");

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        OptionMapping option = event.getOption("text");
        if(option == null){
            event.reply("How did this error happen?").queue();
            return;
        }
        String text = option.getAsString();

        if(text.equals("help")){
            String out = "Rolls dice. \"roll AdB\" will roll A copies of B-sided dice. \"roll AdB,CdD\" will roll A copies of B-sided dice, then C copies of D-sided dice.\n" +
                    "put \"sorted\" before the dice to sort the dice to have sorted output.\n" +
                    "put \"compactsorted\" (or \"cs\") for the same effect, but more compact.";
            event.reply(out).queue();
            return;
        }

        text = text.toLowerCase();
        boolean sorted = false;
        String sortedtext = " copy(ies) of ";
        text = text.replace(" ","");
        if(text.startsWith(sortString)){
            text = text.substring(sortString.length());
            sorted = true;
        }
        if(text.startsWith(compactsorted)){
            text = text.substring(compactsorted.length());
            sorted = true;
            sortedtext = " x ";
        }
        if(text.startsWith(compactsorted2)){
            text = text.substring(compactsorted2.length());
            sorted = true;
            sortedtext = " x ";
        }
        List<Dice> dice = new ArrayList<>();
        int numdice = 0;
        int numsides = 0;
        boolean countingNumDice = true;
        for(char letter : text.toCharArray()){
            int num = Character.getNumericValue(letter);
            if(num >= 0 && num < 10){
                if(countingNumDice){
                    numdice *= 10;
                    numdice += num;
                    if(numdice > MaxDiceRollable){
                        event.reply("You might hurt yourself rolling that many dice!").queue();
                        return;
                    }
                }
                else{
                    numsides *= 10;
                    numsides += num;
                    if(numsides > MaxDiceSides){
                        event.reply("Rolling a dice with that many sides is like rolling a ball!").queue();
                        return;
                    }
                }
            }
            else if (letter == 'd') {
                if(numdice == 0){
                    numdice = 1;
                }

                if (countingNumDice) {
                    countingNumDice = false;
                } else {
                    event.reply("Invalid dice expression!").queue();
                    return;
                }
            } else if (letter == ',') {
                if(numdice == 0){
                    numdice = 1;
                }
                if(numsides == 0){
                    event.reply("Invalid dice expression!").queue();
                    return;
                }
                countingNumDice = true;
                dice.add(new Dice(numsides, numdice));

                numdice = 0;
                numsides = 0;
            } else if (letter == ' ') {
                //ignore
            } else {
                event.reply("Invalid dice expresion!").queue();
                return;
            }

        }

        if(numdice != 0) {
            if(numsides == 0){
                event.reply("Invalid dice expression!").queue();
                return;
            }
            // dicenum = true;
            dice.add(new Dice(numsides, numdice));

            // numdice = 0;
            // numsides = 0;
        }

        int totaldice = 0;
        for ( Dice d : dice ) {
            totaldice += d.count;
        }

        if(sorted){
            if(totaldice > MaxDiceSorted){
                event.reply("You might hurt yourself rolling that many dice!").queue();
                return;
            }
        }
        else{
            if(totaldice > MaxDiceUnsorted){
                event.reply("You might hurt yourself rolling that many dice!").queue();
                return;
            }
        }

        if(totaldice == 0){
            event.reply("Invalid Dice Expression!").queue();
            return;
        }

        if(sorted){
            Map<Integer,Integer> rolls = new HashMap<>();

            dice.forEach(d -> d.rollsorted(rolls));

            long total = 0;
            StringBuilder out = new StringBuilder(event.getUser().getAsMention() + " **Rolled:** | ");
            for ( Integer roll : rolls.keySet() ) {
                if(out.length() > 1000){
                    event.reply(out.toString()).queue();
                    out = new StringBuilder(event.getUser().getAsMention() + " **Also rolled:** | ");
                }
                out.append(rolls.get(roll));
                out.append(sortedtext);
                out.append(roll);
                out.append(" | ");
                total += rolls.get(roll) * roll;
            }
            out.append(" **Sum of all rolls:** ");
            out.append(total);
            event.reply(out.toString()).queue();
        }
        else{
            List<Integer> rolls = new ArrayList<>();

            dice.forEach(d -> d.roll(rolls));

            StringBuilder out = new StringBuilder(event.getUser().getAsMention() + " **Rolled:** | ");
            for(int roll:rolls){
                if(out.length() > 1000){
                    event.reply(out.toString()).queue();
                    out = new StringBuilder(event.getUser().getAsMention() + " **Also rolled:** | ");
                }
                out.append(roll);
                out.append(" | ");
            }
            event.reply(out.toString()).queue();
        }
    }

    private static class Dice{
        int sides;
        int count;

        Dice(int sides, int count){
            this.sides = sides;
            this.count = count;
        }

        @Override
        public String toString() {
            return count + " " + sides + "-sided dice.";
        }

        void roll(List<Integer> rolls){

            if(sides == 0){
                for(int i = 0;i < count; i++){
                    rolls.add(1);
                }
                return;
            }
            for(int i = 0; i < count; i++) {
                rolls.add(Main.rand.nextInt(sides - 1 + 1) + 1);
                //                                   max  - min + 1  + min
            }
        }

        void rollsorted(Map<Integer,Integer> rolls)
        {
            if(sides == 0){
                if(rolls.containsKey(1)){
                    rolls.put(1,rolls.get(1) + count);
                }
                else{
                    rolls.put(1,count);
                }
                return;
            }
            for(int i = 0; i < count; i++){
                int roll = Main.rand.nextInt(sides - 1 + 1) + 1;
                //                                    max  - min + 1  + min
                if(rolls.containsKey(roll)){
                    rolls.put(roll,rolls.get(roll) + 1);
                }
                else{
                    rolls.put(roll,1);
                }
            }
        }
    }
}
