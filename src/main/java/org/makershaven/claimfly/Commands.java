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
    private PlayerTracker playerTracker;

    Commands(ClaimFly plugin) {
        this.plugin = plugin;
        this.playerTracker = plugin.playerTracker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("claimfly") && sender instanceof Player) {

            Player player = (Player) sender;
            if (args.length >= 1) {

                switch (args[0].toLowerCase()) {

                    case "checkinterval":
                        if (player.hasPermission("claimfly.commands.admin")) {
                            if (args.length >= 2)
                                try {
                                    {
                                        plugin.getConfig().set("check-interval", Integer.parseInt(args[1]));
                                        plugin.saveConfig();
                                        plugin.checkFlyingPlayersTask.cancel();
                                        plugin.startCheckTask(plugin, 20, plugin.config.getInt("check-interval"));
                                        player.sendMessage(ChatColor.GREEN + "CheckInterval set to " + args[1]);

                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    player.sendMessage(ChatColor.RED + "CheckInterval must be a number");
                                    break;
                                }
                            else {
                                player.sendMessage(ChatColor.RED + "Not enough args");
                                break;
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + " You do not have permission to use this command.");
                            break;
                        }


                    case "reload":

                        if (player.hasPermission("claimfly.commands.admin")) {
                            plugin.reloadConfig();
                            plugin.saveConfig();
                            plugin.checkFlyingPlayersTask.cancel();
                            plugin.startCheckTask(plugin, 20, plugin.config.getInt("check-interval"));
                            player.sendMessage(ChatColor.GREEN + "ClaimFly config reloaded!");
                            break;
                        }
                        else {
                            player.sendMessage(ChatColor.RED + " You do not have permission to use this command.");
                            break;
                        }
                    case "version":
                        if (player.hasPermission("claimfly.commands.admin")) {
                            player.sendMessage(ChatColor.GOLD + "ClaimFly version " + plugin.pdfFile.getVersion());
                            break;
                        }
                        else {
                            player.sendMessage(ChatColor.RED + " You do not have permission to use this command.");
                            break;
                        }
                    case "boundary":
                        if(args.length >= 2 && player.hasPermission("claimfly.commands.boundary.set")){
                            try {
                                int newDistance = Integer.parseInt(args[1]);
                                Aviator aviator = playerTracker.getAviator(player);
                                aviator.setBoundaryDistance(newDistance);
                                player.sendMessage(ChatColor.GREEN + "Boundary show distance set to " + newDistance+".");

                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.RED + "Boundary show distance must be a number.");

                            }
                            break;
                        }
                        else if (args.length == 1 &&player.hasPermission("claimfly.commands.boundary.toggle")){
                            Aviator aviator = playerTracker.getAviator(player);
                            if(aviator.showBoundaries()){
                                aviator.setShowBoundaries(false);
                                player.sendMessage(ChatColor.GREEN + "Boundary will not be shown.");
                            }
                            else if(!aviator.showBoundaries()) {
                                aviator.setShowBoundaries(true);
                                player.sendMessage(ChatColor.GREEN + "Boundary will be shown.");
                            }
                            break;
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                            break;
                        }

                    default:
                        player.sendMessage(ChatColor.RED + "Invalid arguments!");
                }

            }

            else if (player.hasPermission("claimfly.command")) {

                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.flight-toggle-on")));


                }
                else {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.flight-toggle-off")));
                }

            }

            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
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
        if (args.length < 1) {
            completions.add("cfly");
            completions.add("claimfly");
        }

        if (args.length == 1) {
            if (player.hasPermission("claimfly.commands.boundary.toggle")) {
                completions.add("boundary");
            }

            if (player.hasPermission("claimfly.commands.admin")) {
                completions.add("checkinterval");
                completions.add("reload");
                completions.add("version");
            }
        }

        if (args.length == 2 && args[0].equals("boundary") && player.hasPermission("claimfly.commands.boundary.set")){
            completions.add("10");
        }
        return completions;
    }
}
