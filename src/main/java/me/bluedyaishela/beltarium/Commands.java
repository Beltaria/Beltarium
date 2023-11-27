package me.bluedyaishela.beltarium;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) sender.sendMessage("Beltarium Beta");
        switch (args[0])
        {
            case "give":
                if (args.length == 1) return false;
                String argument = args[1].toUpperCase();
                try {
                    ItemStack itemStack = (ItemStack) ItemManager.class.getField(argument).get(null);
                    player.getInventory().addItem(itemStack);
                    player.sendMessage("Item donné avec succès");
                    return true;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    player.sendMessage("Cet objet n'existe pas");
                    return false;
                }
            case "reload":
                return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> availableCommands = new ArrayList<>();

        if(args.length == 1)
        {
            availableCommands.addAll(Arrays.asList("give", "reload"));
            return this.getArgsComplete(args, availableCommands, 0);
        }

        switch (args[0])
        {
            case "give":
                availableCommands.addAll(Tools.getAllDeclaredFields);
                return this.getArgsComplete(args, availableCommands, 1);
            case "reload":
                return null;
        }
        return null;
    }

    public List<String> getArgsComplete(String[] args, List<String> availableCommands, int argsIndex) {
        List<String> completions = new ArrayList<>();
        String input = args[argsIndex].toLowerCase();

        for (String commandOption : availableCommands) {
            if (commandOption.startsWith(input)) {
                completions.add(commandOption);
            }
        }

        return completions;
    }
}
