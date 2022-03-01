package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Calendar;
import java.util.TimeZone;

public class time extends CommandProcessor {

    public time(){
        cmd = "time";
        help = "Tells you the time, in PST.";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String second = "" + c.get(Calendar.SECOND);
        String minute = "" + c.get(Calendar.MINUTE);
        String hour = "" + c.get(Calendar.HOUR);
        String day = "" + c.get(Calendar.DAY_OF_MONTH);
        String date = getDate(c.get(Calendar.DAY_OF_WEEK));
        String month = getMonth(c.get(Calendar.MONTH));
        String year = "" + c.get(Calendar.YEAR);

        second = adjustString(second,2,"0");
        minute = adjustString(minute,2,"0");
        hour = adjustString(hour,2,"0");
        day = adjustString(day,2,"0");

        String msg = "It is " + date + ", " + month + " " + day + ", " + year + ", and the time is " + hour + ":" + minute + ":" + second + " in the UTC timezone.";
        event.getChannel().sendMessage(msg).queue();
    }

    public final  String getDate(int date){
        String[] dates = {"Sunday","Monday","Tuesday","Wedensday","Thursday","Friday","Saturday","Sunday"};
        return dates[date];
    }

    public final String getMonth(int month){
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        return months[month];
    }
}
