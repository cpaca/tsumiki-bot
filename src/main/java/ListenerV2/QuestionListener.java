package ListenerV2;

import commands.ProblemGenerator.CHEM161;
import core.CommandListener;

public class QuestionListener extends CommandListener {

    private static QuestionListener instance = new QuestionListener();

    private QuestionListener(){
        /*
        addGuildRestriction("387019628204785664"); // dev server
        /*/
        AllGuilds = true;
        //*/
    }

    public static QuestionListener getInstance() {
        return instance;
    }

    @Override
    public void addCommands() {
        commands.add(new CHEM161());
    }
}
