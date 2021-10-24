package ListenerV2;

import commands.KaliumListener.Kalium;
import core.CommandListener;

public class KaliumListener extends CommandListener {

    private static KaliumListener self = new KaliumListener();

    private KaliumListener(){
        //addGuildRestriction("387019628204785664");
        addGuildRestriction("601420037445648385");
    }

    public static KaliumListener getInstance() {
        return self;
    }

    @Override
    public void addCommands() {
        commands.add(new Kalium());

    }
}
