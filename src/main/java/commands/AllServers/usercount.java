package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class usercount extends Command {

    public usercount(){
        cmd = "usercount";
        help = "Tells you the # of players in the server.";
        setCategory("Info");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage("There are " + event.getGuild().getMembers().size() + " users in the guild.").queue();
    }
}
