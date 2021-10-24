package commands.ProblemGenerator.CHEM161Problems;

import core.Problem;

import java.util.ArrayList;

public class MassFromPartsPerBillion implements Problem {
    // PPB => mass

    @Override
    public String getID() {
        return "mfppb";
    }

    @Override
    public String getQuestion(double[] values) {
        return "In water with a lead concentration of " + Problem.toSigFigs(values[0]) + " ppb, how much lead is in " + Problem.toSigFigs(values[1]) + " liters of water?\n" +
                "Assume the water is a perfect 1.000 g/mL. **Answer in milligrams.**";
    }

    @Override
    public String getExampleQuestion() {
        return "In water with a lead concentration of XXX ppb, how much lead is in YYY liters of water?\n" +
                "Assume the water is a perfect 1.000 g/mL. **Answer in milligrams.**";
    }

    @Override
    public String getAnswer(double[] values) {
        double ppb = values[0];
        double waterInL = values[1];
        double waterInmL = waterInL * 1000;
        double waterInGrams = waterInmL;
        double billionths = waterInGrams/1000000000;
        double gramsOfLead = billionths * ppb;
        double LeadInmg = gramsOfLead * 1000;
        return Problem.toSigFigs(LeadInmg) + "mg";
    }

    @Override
    public String getHowTo(double[] values) {
        double ppb = values[0];
        double waterInL = values[1];
        double waterInmL = waterInL * 1000;
        double waterInGrams = waterInmL;
        double billionths = waterInGrams/1000000000;
        double gramsOfLead = billionths * ppb;
        double LeadInmg = gramsOfLead * 1000;
        ArrayList<String> steps = new ArrayList<>();
        steps.add("First, we must turn water from Liters into mL. ");
        steps.add(Problem.toSigFigs(waterInL) + "L = " + Problem.toSigFigs(waterInmL) + "mL");
        steps.add("Next, we turn water from milliliters into grams.");
        steps.add(Problem.toSigFigs(waterInmL) + "mL = " + Problem.toSigFigs(waterInGrams) + "g");
        steps.add("Next, we must get how many \"billionths\" of water there are in " + waterInGrams + " grams of water.");
        steps.add(Problem.toSigFigs(waterInGrams) + "g = " + Problem.toSigFigs(billionths) + " \"billionths\" of a gram");
        steps.add("Next, now that the value is in billionths, and our given value is " + ppb + " parts per BILLION, we can multiply them.");
        steps.add(Problem.toSigFigs(billionths) + " * " + Problem.toSigFigs(ppb) + " = " + Problem.toSigFigs(gramsOfLead) + "g Lead");
        steps.add("Finally, we must turn it into milligrams.");
        steps.add(Problem.toSigFigs(gramsOfLead) + "g = " + Problem.toSigFigs(LeadInmg) + "mg");

        StringBuilder out = new StringBuilder();
        for(String s: steps){
            out.append(s).append("\n");
        }

        return out.toString();
    }

    public double[] process(double[] values){
        values[0] = values[0] * 10 + 10; // from 10ppb to 20ppb
        values[1] = values[1] * 35 + 15; // from 15 to 50

        return values;
    }
}
