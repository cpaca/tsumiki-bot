package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class startgame extends CommandProcessor {

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

    }
}
