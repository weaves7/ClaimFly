package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    private ClaimFly plugin;

    Commands(ClaimFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("claimfly") && sender instanceof Player) {

            Player player = (Player) sender;
            if (args.length >= 1 && player.hasPermission("claimfly.commands.admin")) {

                switch (args[0].toLowerCase()) {

                    case "checkinterval":
                        if (args.length >= 2)
                            try {
                                {
                                    plugin.getConfig().set("check-interval", Integer.parseInt(args[1]));
                                    plugin.saveConfig();
                                    plugin.checkFlyingPlayersTask.cancel();
                                    plugin.startCheckTask(plugin, 20, plugin.config.getInt("check-interval"));
                                    sender.sendMessage(ChatColor.GREEN + "CheckInterval set to " + args[1]);

                                    break;
                                }
                            } catch (NumberFormatException e) {
                                sender.sendMessage(ChatColor.RED + "CheckInterval must be a number");
                                break;
                            }
                        else {
                            sender.sendMessage(ChatColor.RED + "Not enough args");
                            break;
                        }

                    case "debug":
                        if (plugin.getConfig().getBoolean("debug")) {
                            plugin.getConfig().set("debug", false);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.GREEN + "Debug set to false.");
                            break;
                        } else if (!plugin.getConfig().getBoolean("Debug")) {
                            plugin.getConfig().set("debug", true);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.GREEN + "Debug set to true.");
                            break;
                        }

                        break;
                    case "reload":

                        plugin.reloadConfig();
                        plugin.saveConfig();
                        plugin.checkFlyingPlayersTask.cancel();
                        plugin.startCheckTask(plugin, 20, plugin.config.getInt("check-interval"));
                        sender.sendMessage(ChatColor.GREEN + "ClaimFly config reloaded!");
                        break;
                    case "version":
                        sender.sendMessage(ChatColor.GOLD + "ClaimFly version " + plugin.pdfFile.getVersion());
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED + "Invalid arguments!");
                }

            } else if (player.hasPermission("claimfly.command")) { //TODO Add Aviator boundary commands for players

                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.flight-toggle-on")));


                } else  {
                    player.setAllowFlight(false);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.flight-toggle-off")));
                }

            }

            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        plugin.getConfig().getString("message.claimfly-cmd-deny")));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;
        List<String> completions = new ArrayList<>();
        if(args.length < 1){
            completions.add("cfly");
            completions.add("claimfly");
        }

        if (args.length == 1) {
            if (player.hasPermission("claimfly.command")) {
                completions.add("border");
            }

            if (player.hasPermission("claimfly.commands.admin")) {
                completions.add("checkinterval");
                completions.add("debug");
                completions.add("reload");
                completions.add("version");

            }

        }
        return completions;
    }
}
