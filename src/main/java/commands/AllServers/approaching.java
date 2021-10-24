package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class approaching extends Command {

    public approaching(){

    }

    byte diostate = 0;
    byte jotarostate = 0;

    String[] DioText = {"Oh? you're approaching me?",
            "Instead of running away, you're coming straight to me?",
            "Even though your grandfather, Joseph, told you the secret of the world like an exam student finishing the problems on an exam before the chime?",
            "Oh ho! Then come as close as you like."};

    String[] JotaroText = {"<start>",
            "I can't beat the shit out of you without getting closer."};

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(event == null)
            return;
        if(event.getMember() == null)
            return;
        if(event.getMember().getUser() == null)
            return;
        if(event.getMember().getUser().getId() == null)
            return;
        if(!event.getMember().getUser().getId().equals("205011703891623936"))
            return;

        if(diostate >= DioText.length)
            diostate = 0;
        if(jotarostate >= JotaroText.length)
            jotarostate = 0;
        if(message.equals(DioText[diostate])){
            diostate++;
        }
        else{
            diostate = 0;
        }
        if(message.equals(JotaroText[jotarostate])){
            jotarostate++;
        }
        else{
            jotarostate = 0;
        }
        respond(event);
    }

    private void respond(MessageReceivedEvent event){
        if(diostate == 3){
            event.getChannel().sendMessage(JotaroText[1]).queue();
        }
        if(diostate == 4){
            // after "oh ho!"
            event.getChannel().sendMessage("<Debug message>").queue();
        }

        if(jotarostate == 1){
            new Thread(() -> {
                event.getChannel().sendMessage(DioText[0]).complete();
                event.getChannel().sendMessage(DioText[1]).complete();
                event.getChannel().sendMessage(DioText[2]).queue();
            }).start();
        }
        if(jotarostate == 2){
            event.getChannel().sendMessage(DioText[3]).queue();
        }
    }
}
