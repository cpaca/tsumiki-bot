package ListenerV2;

import commands.UWSLPassive.OK;
import commands.UWSLPassive.PrayerReact;
import commands.UWSLPassive.tenken;
import core.CommandListener;

public class UWSLListener extends CommandListener {

    private static UWSLListener self = new UWSLListener();

    private UWSLListener(){
        addGuildRestriction("289920126457741312");
    }

    public static UWSLListener getInstance(){
        return self;
    }

    @Override
    public void addCommands(){
        commands.add(new OK());
        commands.add(new PrayerReact());
        commands.add(new tenken());
    }

}
