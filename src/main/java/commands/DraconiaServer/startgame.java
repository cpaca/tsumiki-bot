package commands.DraconiaServer;

import ListenerV2.DraconiaListenerV2;
import core.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class startgame extends Command {

    public startgame(){
        cmd = "startgame";
        help = "Tells everyone that the game has ended. Usage: ``endgame [game name]``";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        Member m = event.getMember();
        String username;
        if(m == null){
            username = event.getAuthor().getName();
        }
        else {
            String nickname = m.getNickname();
            if(nickname == null){
                username = event.getAuthor().getName();
            }
            else{
                username = nickname;
            }
        }
        DraconiaListenerV2.Registered.sendMessage("@here Game started by " + username + ". Game name: " + message).queue();
    }
}
