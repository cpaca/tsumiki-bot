package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class mentionme extends CommandProcessor {

    public mentionme(){
        cmd = "mentionme";
        help = "Mentions the person who runs the command";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        event.reply("You wanted to be mentioned, " + event.getUser().getAsMention()).queue();
    }
}
