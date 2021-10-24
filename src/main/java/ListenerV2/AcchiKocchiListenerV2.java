package ListenerV2;

import commands.AcchiKocchi.getroleinfo;
import commands.AcchiKocchi.role;
import core.CommandListener;

public class AcchiKocchiListenerV2 extends CommandListener {

    private static AcchiKocchiListenerV2 self = new AcchiKocchiListenerV2();

    private AcchiKocchiListenerV2(){
        addGuildRestriction("413108124556328980");
    }

    public static AcchiKocchiListenerV2 getInstance() {
        return self;
    }

    @Override
    public void addCommands() {
        commands.add(new getroleinfo());
        commands.add(new role());
    }
}
