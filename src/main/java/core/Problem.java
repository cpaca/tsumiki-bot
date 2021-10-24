package core;

public interface Problem {

    String getID();
    String getQuestion(double[] values);
    String getExampleQuestion(); // example question
    String getAnswer(double[] values);
    String getHowTo(double[] values);
    double[] process(double[] values); // process values

    default String getText(){
        final int arrLength = 10;

        double[] values = new double[arrLength];
        for(int i = 0; i < values.length; i++){
            values[i] = Main.rand.nextDouble();
        }
        values = process(values);

        String ID = "**Question ID:** " + getID();
        String question = "**Question:** " + getQuestion(values);
        String answer = "**Answer:** ||" + getAnswer(values) + "||. (Note: A E+B is A * 10^B. So, 1.234E+05 is 1.234 * 10^5, or 123400)";
        String[] HowTo = getHowTo(values).split("\n");
        for(int i = 0; i < HowTo.length; i++){
            HowTo[i] = "||" + HowTo[i] + "||";
        }
        StringBuilder TotalHowTo = new StringBuilder("**How To Solve:**\n");
        for(String s: HowTo){
            TotalHowTo.append(s);
            TotalHowTo.append("\n");
        }

        return ID + "\n\n" + question + "\n\n" + answer + "\n\n" + TotalHowTo.toString();
    }

    static String toSigFigs(double d){
        return toSigFigs(d, 4);
    }

    static String toSigFigs(double d, int sigFigs){
        return String.format("%."+sigFigs+"G", d);
    }
}
