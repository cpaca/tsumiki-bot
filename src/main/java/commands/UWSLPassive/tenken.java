package commands.UWSLPassive;

import core.Command;
import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class tenken extends Command {

    private int odds = 1000;

    public tenken(){
        // passive
    }

    @Override
    public void MessageReceived(String message, MessageReceivedEvent event){
        if(Main.rand.nextInt(odds) == 0){
            event.getChannel().sendMessage("Read tenken. (Rare message: 0.1% chance of appearing)").queue();
            return;
        }

        if(!event.getJDA().getSelfUser().getId().equals(event.getAuthor().getId()) && message.equalsIgnoreCase("read tenken")){
            event.getChannel().sendMessage("Read tenken.").queue();
            return;
        }

    }

}
