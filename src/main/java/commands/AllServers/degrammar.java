package commands.AllServers;

import core.CommandProcessor;
import core.Main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class degrammar extends CommandProcessor {

    public degrammar(){
        cmd = "degrammar";
        help = "Makes the grammar in your sentence *worse*.";
        setCategory("TextIO");
    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        data.addOption(OptionType.STRING,"text", "text to ungrammar");

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        OptionMapping option = event.getOption("text");
        if(option == null){
            // shouldn't happen, but
            event.reply("How did this error happen? Error code #AS001").queue();
            return;
        }
        String text = option.getAsString();

        StringBuilder out = new StringBuilder();
        boolean first = true;
        for(String word : text.split(" ")) {
            if(first){
                first = false;
            }
            else{
                out.append(" ");
            }
            out.append(scrambleWord(word));
        }
        event.getChannel().sendMessage(out.toString()).queue();
    }

    public String scrambleWord(String word){
        String originWord = word;
        word = word.replace("'","");
        word = word.replace(",","");
        word = word.replace(".","");
        word = word.replace("!","");
        word = word.replace("?","");
        if (originWord.equals(word) && word.length() > 1) {
            int num = word.length() - 1;
            num = Main.rand.nextInt(num);
            String before = word.substring(num, num+1);
            String after = word.substring(num+1, num+2);
            word = word.substring(0, num) + after + before + word.substring(num+2);
        }
        return word;
    }

}
