package commands.ProblemGenerator;

import commands.ProblemGenerator.CHEM161Problems.MassFromPartsPerBillion;
import core.ProblemListener;

public class CHEM161 extends ProblemListener {

    public CHEM161(){
        cmd = "CHEM161";
        help = "Problems for CHEM161";
    }

    @Override
    protected void AddProblems() {
        problems.add(new MassFromPartsPerBillion());
    }
}
