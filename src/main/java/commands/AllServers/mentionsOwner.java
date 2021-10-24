package commands.AllServers;

import core.Command;
import core.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;

public class mentionsOwner extends Command {

    public mentionsOwner(){

    }

    @Override
    protected void MessageReceived(String message, @Nonnull MessageReceivedEvent event) {
        Member owner = Main.getOwner();
        if(event.getAuthor().isBot())
            return;
        if(owner == null){
            return;
        }
        String id = owner.getId();
        if(event.isFromGuild()) {
            if (event.getGuild().getMemberById(id) == null) {
                // mnw is not in this server
                // stopping
                return;
            }
        }
        else{
            // private DMs?
            return;
        }
        if(event.getMessage().getMentionedMembers().contains(owner)){
            owner.getUser().openPrivateChannel().queue((channel) ->
            {
                channel.sendMessage("User " + mention(event.getAuthor()) + " has mentioned you.").queue();
            });
        }
    }
}
