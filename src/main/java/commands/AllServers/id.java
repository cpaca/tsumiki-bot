package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class id extends CommandProcessor {

    public id(){
        cmd = "id";
        help = "Gives you information on discord's user/server/message ID system";
        setCategory("Info");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        event.reply(
                "DiscordID is an ID which discord automatically gives you upon account creation. \n" +
                        "Every account has a DiscordID. The biggest usage of DiscordID (that I know) \n" +
                        "is that if you put <@! before it and > after it (no spaces), you ping the user with that DiscordID. \n" +
                        "For more information on DiscordID and how to get someone else's, go to \n" +
                        "https://support.discordapp.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID-").queue();
    }
}
