package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class intercepted extends CommandProcessor {

    public intercepted(){
        cmd = "intercepted";
        help = "Don't you hate it when you get intercep-";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessageEmbeds(buildImgur("Intercepted.","e14FFPe.png").build()).queue();
        event.getChannel().sendMessageEmbeds(buildImgur("Intercepted.","ZG9pUI5.png").build()).queue();
        event.getChannel().sendMessageEmbeds(buildImgur("Intercepted.","Nm9rr0i.png").build()).queue();
        event.getChannel().sendMessageEmbeds(buildImgur("Intercepted.","AyMYDJn.png").build()).queue();
        event.getChannel().sendMessageEmbeds(buildImgur("Intercepted.","qnpyorA.png").build()).queue();
    }
}
