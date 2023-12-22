package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class expand extends CommandProcessor {

    public expand(){
        cmd = "expand";
        help = "e x p a n d s a messge";
        setCategory("TextIO");
    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        data.addOption(OptionType.STRING,"text", "text to E X P A N D");

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

        if(text.length() == 0)
            return;
        StringBuilder out = new StringBuilder();
        for(char c:text.toCharArray()){
            out.append(c);
            out.append(" ");
        }
        if(out.length() > 1900){
            event.getChannel().sendMessage("Message too l o n g!").queue();
            return;
        }
        event.reply(out.toString()).queue();
    }
}
