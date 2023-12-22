package commands.AcchiKocchi;

import core.CommandProcessor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.entities.Guild;

public class role extends CommandProcessor {

    public role(){
        cmd = "role";
        help = "Used to add or remove roles from yourself.";
        setCategory("Acchi Kocchi");
        guild = 413108124556328980L;
    }

    @Override
    public CommandData UpdateCommandData(CommandData data){
        SubcommandData add = new SubcommandData("add", "Add a role to yourself")
                .addOption(OptionType.STRING, "target", "The role to add to yourself", true);
        SubcommandData remove = new SubcommandData("remove", "Remove a role from yourself")
                .addOption(OptionType.STRING, "target", "The role to remove from yourself", true);

        data = data.addSubcommands(add, remove);
        return data;
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        String subcommand = event.getSubcommandName();
        OptionMapping commandOption = event.getOption("target");

        if(commandOption == null){
            // it was a required option, this shouldn't happen
            event.reply("Internal error; try again maybe? ID #AK001").queue();
            return;
        }

        String rolename = commandOption.getAsString().toLowerCase();
        Guild guild = event.getGuild();

        if(guild == null){
            // really shouldn't happen
            event.reply("Internal error; try again maybe? ID #AK002").queue();
            return;
        }

        String roleID = getroleinfo.map.get(rolename);
        if(roleID == null){
            // Hmm.
            event.reply("Internal error; try again maybe? ID #AK006").queue();
            return;
        }
        Role role = guild.getRoleById(roleID);

        if(role == null){
            event.reply("Internal error; maybe ping Tsumiki Miniwa. ID #AK003").queue();
            return;
        }

        if(subcommand == null){
            event.reply("Internal error; try again later. ID #AK004").queue();
            return;
        }

        Member caller = event.getMember();
        if(caller == null){
            event.reply("Internal error; try again later. ID #AK005").queue();
            return;
        }

        if(subcommand.equals("add")){
            event.getGuild().addRoleToMember(caller, role).queue();
            event.reply("Added role: " + role.getName()).queue();
        }
        else if(subcommand.equals("remove")){
            event.getGuild().removeRoleFromMember(caller, role).queue();
            event.reply("Removed role: " + role.getName()).queue();
        }
        else{
            event.reply("Apparantly you didn't put either add OR remove. I thought discord would throw an error for that.").queue();
        }
    }
}
