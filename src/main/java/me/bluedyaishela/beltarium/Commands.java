package me.bluedyaishela.beltarium;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) sender.sendMessage("Beltarium Beta");
        switch (args[0])
        {
            case "beltarium_nugget":
                player.getInventory().addItem(ItemManager.BELTARIUM_NUGGET);
                player.sendMessage("Item donné avec succès");
                return true;
            case "beltarium_ingot":
                player.getInventory().addItem(ItemManager.BELTARIUM_INGOT);
                player.sendMessage("Item donné avec succès");
                return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
