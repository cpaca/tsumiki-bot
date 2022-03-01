package commands.AcchiKocchi;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class getroleinfo extends CommandProcessor {

    static final Map<String,String> map = new LinkedHashMap<>();

    public getroleinfo(){
        map.put("tsumiki","413213389180108812");
        map.put("io","413213963874992129");
        map.put("mayoi","413214098914934785");
        map.put("hime","413214258822643722");
        map.put("sakaki","413214407770767360");
        map.put("miiko","450311767881416714");
        map.put("saki","450312803652468749");
        map.put("kikue","450311593104769024");

        cmd = "getroleinfo";
        help = "Get info on roles accessible for free";
        setCategory("Acchi Kocchi");
        guild = 413108124556328980L;
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        StringBuilder out = new StringBuilder();
        for(String s:map.keySet()){
            s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            out.append(" [");
            out.append(s);
            out.append("] ");
        }
        String send = "**Role Transformation Information**\n" +
               "Doing \"!role add [name]\" for any of these roles" +
                "Will automatically give you the role. \n" +
                "The same works for \"!role remove [name]\"" +
                "For example: Doing \"!role add Tsumiki\" gives you the Tsumiki role.\n" +
                "**List of valid roles, contained within []'s:**\n" +
                out.toString();
        event.reply(send).queue();
    }
}
