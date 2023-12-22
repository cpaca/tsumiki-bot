package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.util.HashMap;
import java.util.Map;

public class blackboardBold extends CommandProcessor {

    // apparantly the blackboard boldface versions count as 2 characters
    Map<String, String> transformations = new HashMap<>();

    public blackboardBold(){
        cmd = "blackboardbold";
        help = "Turns a string into blackboard boldface";
        setCategory("TextIO");

        String[] from = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split("");
        int index = 0;

        char firstChar = '\uD835';
        final int firstLetterNum = 56632;
        final int letters = 26 + 26; // 26 letters in the alphabet + 26 lowercase
        int num = firstLetterNum;
        for(int i = 0; i < letters; i++){
            char secondChar = (char) num;
            num++;
            String letter = "" + firstChar + secondChar;
            transformations.put(from[index], letter);
            index++;
        }

        final int firstNumberNum = 57304;
        final int numbers = 10;
        num = firstNumberNum;
        for(int i = 0; i < numbers; i++){
            char secondChar = (char) num;
            num++;
            String letter = "" + firstChar + secondChar;
            transformations.put(from[index], letter);
            index++;
        }

        String[] finalfrom = "[ ] C H N P Q R Z".split(" ");
        String[] finalto = "『 』 ℂ ℍ ℕ ℙ ℚ ℝ ℤ".split(" ");
        for(int i = 0; i< finalfrom.length; i++){
            transformations.put(finalfrom[i], finalto[i]);
        }

        // Testing to make sure it works
        /*
        transformations.forEach((f,t) -> System.out.println("" + f + " " + t));
        //*/

    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        data.addOption(OptionType.STRING,"text", "blackboard bold-ify");

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        OptionMapping option = event.getOption("text");
        if(option == null){
            // shouldn't happen, but
            event.reply("How did this error happen? Error code #AS001").queue();
            return;
        }
        String text = option.getAsString();

        final String[] data = new String[] {text};
        // big brain! <--- Probably 2018 me.
        //
        // Next, 2022 me:
        // What the fuck was I doing?
        // Oh, the data here needs to be final or effectively final. I see.
        // This certainly is the big brain way to do it.
        transformations.forEach((f, t) -> data[0] = data[0].replace(f,t));

        event.reply(data[0]).queue();
    }
}
