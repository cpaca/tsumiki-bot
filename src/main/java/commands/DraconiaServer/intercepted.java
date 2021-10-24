package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class intercepted extends Command {

    public intercepted(){
        cmd = "intercepted";
        help = "Don't you hate it when you get intercep-";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(buildImgur("Intercepted.","e14FFPe.png").build()).queue();
        event.getChannel().sendMessage(buildImgur("Intercepted.","ZG9pUI5.png").build()).queue();
        event.getChannel().sendMessage(buildImgur("Intercepted.","Nm9rr0i.png").build()).queue();
        event.getChannel().sendMessage(buildImgur("Intercepted.","AyMYDJn.png").build()).queue();
        event.getChannel().sendMessage(buildImgur("Intercepted.","qnpyorA.png").build()).queue();
    }
}
