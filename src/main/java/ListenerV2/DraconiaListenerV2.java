package ListenerV2;

import commands.DraconiaServer.*;
import core.CommandListener;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import util.STATICS;

public class DraconiaListenerV2 extends CommandListener {

    public static MessageChannel Registered = null;
    private static DraconiaListenerV2 self = new DraconiaListenerV2();
    private DraconiaListenerV2(){
        addGuildRestriction("374319848659877890");
    }

    public static DraconiaListenerV2 getInstance() {
        return self;
    }

    @Override
    public void addCommands() {
        commands.add(new designate());
        commands.add(new notacommand());
        commands.add(new intercepted());
        commands.add(new lag());
        commands.add(new genderjai());
        commands.add(new beenacl());
        commands.add(new reposter());
        commands.add(new beebeinganidiot());
        commands.add(new failuretoedit());
        commands.add(new yourecrap());
        commands.add(new heilphoto());
        commands.add(new ifjaibreathes());
        commands.add(new jaionlucioisbullshit());
        commands.add(new spelling());
        commands.add(new piuyjizzed());
        commands.add(new prick());
        commands.add(new ultimatefail());
        commands.add(new startgame());
        commands.add(new endgame());
    }

    @Override
    public void onReady(ReadyEvent event) {
        if(STATICS.is_dev){
            Registered = null;
        }
        else {
            Registered = event.getJDA().getGuildById("374319848659877890").getTextChannelById("376874420083490816");
        }
        super.onReady(event);
    }
}
