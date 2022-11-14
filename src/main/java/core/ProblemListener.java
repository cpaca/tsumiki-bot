package core;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ProblemListener extends CommandProcessor {

    public ProblemListener(){
        AddProblems();
        setCategory("practice"); // practice problems
    }

    protected ArrayList<Problem> problems = new ArrayList<>();
    protected abstract void AddProblems();

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.equals("")){
            StringBuilder out = new StringBuilder("");
            out.append("Add \"rand\" to your message (e.g. \"!CHEM161 rand\") to pick any type of question from this class.\n");
            out.append("Add \"list\" to your message (e.g. \"!CHEM161 list\") to list all question IDs for this class.\n");
            out.append("If you have an ID, add it to your message (e.g. \"!CHEM161 mfppb\") to get the same type of question again.\n");
            // Main.rand.nextInt(sides - 1 + 1) + 1
            // (max - min + 1) + min
            event.getChannel().sendMessage(out).queue();
        }
        else if(message.equals("rand")){
            // max = # problems = problems.length() - 1
            // min = 0
            // (max - min + 1) + min
            // (problems.length() - 1 - 0 + 1) + 0
            // (problems.length() - 1 + 1)
            // problems.length()
            int ProblemID = Main.rand.nextInt(problems.size());
            Problem p = problems.get(ProblemID);
            String text = p.getText();
            List<String> splitText = SplitString(text);
            for(String s:splitText){
                event.getChannel().sendMessage(s).queue();
            }
        }
        else if(message.equals("list")){
            StringBuilder list = new StringBuilder();
            for(Problem p:problems){
                String ID = p.getID();
                String question = p.getExampleQuestion();
                if(question.contains("\n")){
                    question = question.substring(0, question.indexOf("\n"));
                }
                if(question.length() > 60){
                    question = question.substring(0, 50) + "...";
                }
                list.append("**").append(ID).append("**: ").append(question).append("\n");
            }
            List<String> out = SplitString(list.toString());

            event.getChannel().sendMessage("To request a question, put \"ID\" after the class. \n" +
                    "For example, one might use \"!CHEM161 PPM\" to request question ID PPM from class CHEM161").queue();

            for(String s: out){
                event.getChannel().sendMessage(s).queue();
            }
        }
        else{
            Problem toUse = null;
            for(Problem p : problems){
                if(p.getID().equalsIgnoreCase(message)){
                    toUse = p;
                    break;
                }
            }
            if(toUse == null){
                event.getChannel().sendMessage("Invalid question ID!").queue();
            }
            else{
                String text = toUse.getText();
                List<String> splitText = SplitString(text);
                for(String s:splitText){
                    event.getChannel().sendMessage(s).queue();
                }
            }
        }
    }
}
