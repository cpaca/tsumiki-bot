package commands.AllServers;

import core.Command;
import core.Main;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThanosSnap extends Command {

    public ThanosSnap(){
        //passive function
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {

        if(message.equalsIgnoreCase("thanos has joined the game.")){
            if(!event.isFromGuild()){
                return; // not from guild, fail.
            }
            Member m = event.getMember();
            if(m == null)
                return;
            GuildVoiceState gvs = m.getVoiceState();
            if(gvs == null)
                return;
            VoiceChannel channel = gvs.getChannel();
            if(channel == null)
                return;

            List<Role> roles = m.getRoles();
            AtomicBoolean valid = new AtomicBoolean(false);
            roles.forEach((Role r) ->{
                if(r.getName().equalsIgnoreCase("Infinity Gauntlet Holder")){
                    valid.set(true);
                }
            });
            if(!valid.get()){
                if(!isDeveloper(event)){
                    return;
                }
            }

            List<Member> members = channel.getMembers();
            Guild g = event.getGuild();
            members.forEach((member) ->{
                if(Main.rand.nextBoolean()) {
                    g.kickVoiceMember(member).queue();
                }
            });

            event.getChannel().sendMessage("I don't feel so good...").queue();
        }
    }
}
