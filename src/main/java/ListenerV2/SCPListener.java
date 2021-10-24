package ListenerV2;

import commands.SCP.*;
import core.CommandListener;

public class SCPListener extends CommandListener {

    private static final SCPListener self = new SCPListener();

    private SCPListener(){
        addGuildRestriction("569363224009637920");

        //dev server
        addGuildRestriction("387019628204785664");
    }

    public static SCPListener getInstance() {
        return self;
    }

    @Override
    public void addCommands() {
        commands.add(new Basic914Test());
        commands.add(new get914test());
        commands.add(new download914test());
    }
}
