package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class guildcount extends CommandProcessor {

    public guildcount(){
        cmd = "guildcount";
        help = "Counts how many guilds which use this bot.";
        setCategory("Developer");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(event.getMember().getUser().getId().equals("205011703891623936")){
            event.getChannel().sendMessage("Bot is running on " + event.getJDA().getGuilds().size() + " guilds.").queue();
            return;
        }
        event.getChannel().sendMessage("Bot is running on [REDACTED] guilds.").queue();
    }
}
