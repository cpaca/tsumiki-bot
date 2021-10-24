package commands.KaliumListener;

import core.Command;
import core.EmojiCollector;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Kalium extends Command {

    public Kalium(){

    }

    private static boolean running = false;

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("k")) {
            if(!running) {
                running = true;
                new Thread(() -> {
                    event.getMessage().addReaction(EmojiCollector.getEmoji("a")).complete();
                    event.getMessage().addReaction(EmojiCollector.getEmoji("l")).complete();
                    event.getMessage().addReaction(EmojiCollector.getEmoji("i")).complete();
                    event.getMessage().addReaction(EmojiCollector.getEmoji("u")).complete();
                    event.getMessage().addReaction(EmojiCollector.getEmoji("m")).queue();
                    running = false;
                }).start();
            }
        }
    }
}
