package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class suckup extends CommandProcessor {

    public suckup(){
        cmd = "suckup";
        help = "Tells the person above that they __suck__";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        event.reply(":arrow_double_up: The last person to send a message sucks! :arrow_double_up:").queue();
    }

}
