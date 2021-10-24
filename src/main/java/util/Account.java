package util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public class Account {

    public String name = "";
    public String Discriminator = "-1";
    public String ID = "";

    public Account(String name_, String Discriminator_, String ID_){
        name = name_;
        Discriminator = Discriminator_;
        ID = ID_;
    }

    public Account(User user){
        name = user.getName();
        Discriminator = user.getDiscriminator();
        ID = user.getId();
    }

    public Account(Member mem){
        name = mem.getUser().getName();
        Discriminator = mem.getUser().getDiscriminator();
        ID = mem.getUser().getId();
    }

    public boolean compareAcc(Account Account_){
        String name_ = Account_.name;
        String Discriminator_ = Account_.Discriminator;

        if(name.equals(name_)&& Discriminator.equals(Discriminator_)){
            return true;
        }
        else {
            return false;
        }
    }

    public String accData(){
        return " name " + name + " and Discriminator " + Discriminator;
    }

    public String mention(){
        return "<@!" + ID +">";
    }

}
