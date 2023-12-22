package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class leavechat extends CommandProcessor {

    public leavechat(){
        cmd = "leavechat";
        help = "<Assistant has left the chatroom>";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        event.reply(event.getUser().getAsMention() + " has left the chatroom.").queue();
    }
}
