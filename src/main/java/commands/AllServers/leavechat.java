package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class leavechat extends CommandProcessor {

    public leavechat(){
        cmd = "leavechat";
        help = "<Assistant has left the chatroom>";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        event.reply(event.getUser().getAsMention() + " has left the chatroom.").queue();
    }
}
