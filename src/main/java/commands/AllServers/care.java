package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;


public class care extends CommandProcessor {

    public care(){
        cmd = "care";
        help = "When you really don't care.";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        if(event.getMember() == null){
            event.reply("How did this error happen? Error #AS002").queue();
            return;
        }
        event.reply(event.getMember().getUser().getAsMention() + " cares.\nNow it's your turn to tell a lie!").queue();
    }
}
