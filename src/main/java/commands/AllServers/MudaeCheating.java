package commands.AllServers;

import core.Command;
import core.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MudaeCheating extends Command {

    public MudaeCheating(){
        // passive
    }

    @Override
    public void MessageReceived(String message, MessageReceivedEvent event){
        boolean valid = false;
        // UWSL
        if(event.getGuild().getIdLong() == 289920126457741312L){
            valid = true;
        }

        // dev
        if(event.getGuild().getIdLong() == 387019628204785664L){
            valid = true;
        }

        // This is in all-servers despite using a server filter because
        // if I ever cheat on another server I'll need this code again
        // also it's active in dev server for obvious reasons

        if(!valid){
            return;
        }

        valid = false;
        // if it's not mudae bot then ignore
        if(event.getAuthor().getIdLong() == 432610292342587392L){
            valid = true;
        }

        // and use tsumikiminiwa too just for testing
        if(event.getAuthor().getIdLong() == 205011703891623936L){
            valid = true;
        }

        if(!valid){
            return;
        }

        if(event.getMessage().getMentionedMembers().size() >= 1 && message.contains("Wished by")){
            Member tsumnw = Main.getOwner();
            tsumnw.getUser().openPrivateChannel().queue((channel) ->
            {
                channel.sendMessage("Wished character. \n<" + event.getMessage().getJumpUrl() + ">").queue();
            });
        }

    }

}
