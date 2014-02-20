package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.Updater;
import com.FriedTaco.taco.godPowers.godPowers;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodPowersCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public GodPowersCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.DARK_AQUA + "About the " + ChatColor.DARK_GREEN + "godPowers" + ChatColor.DARK_AQUA + " Project:");
                player.sendMessage(ChatColor.GOLD + "godPowers is an " + ChatColor.RED + "Open Source" + ChatColor.GOLD + " plugin created in December 2011");
                player.sendMessage(ChatColor.GOLD + "by " + ChatColor.BLUE + "FriedTaco" + ChatColor.GOLD + ". It is currently developed by " + ChatColor.BLUE + "Swift" + ChatColor.GOLD + "&" + ChatColor.BLUE + "Zbob750" + ChatColor.GOLD + ".");
                player.sendMessage(ChatColor.GOLD + "The goal is to provide a set of commands to give users powers based on greek and roman mythology.");
                player.sendMessage(ChatColor.DARK_AQUA + "Tips:");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "Use " + ChatColor.RED + "/godpowers commands" + ChatColor.GREEN + " to see all commands");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "Use " + ChatColor.RED + "/godpowers update" + ChatColor.GREEN + " to manually install updates");
                player.sendMessage(ChatColor.DARK_AQUA + "Developers:");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "SwiftDev" + ChatColor.BLUE + " (Developer)");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "Zbob750" + ChatColor.BLUE + " (Developer)");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "FriedTaco" + ChatColor.BLUE + " (Former Developer)");
                player.sendMessage(ChatColor.DARK_AQUA + "Useful Links:");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "#godpowers @ irc.esper.net" + ChatColor.GOLD + " IRC Chat");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "bit.ly/GodPowers" + ChatColor.GOLD + " Bukkit Plugin Page");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "jfkingsley.co.uk/godpowers" + ChatColor.GOLD + " Server list");
                player.sendMessage(ChatColor.DARK_AQUA + "Donation Info:");
                player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "goldgamermc@gmail.com" + ChatColor.GOLD + " Paypal");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("commands")) {
                    if (player.hasPermission("godpowers.commands") | player.isOp()) {
                        player.sendMessage(ChatColor.BLUE + "-- godPowers Commands List --");
                        if (player.hasPermission("godpowers.bless") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/bless " + ChatColor.GREEN + "Enchants all items in your inventory!");
                        if (player.hasPermission("godpowers.demigod") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/demigod " + ChatColor.GREEN + "Reduces all damage you take!");
                        if (player.hasPermission("godpowers.die") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/die " + ChatColor.GREEN + "Kills yourself!");
                        if (player.hasPermission("godpowers.dupe") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/dupe " + ChatColor.GREEN + "Dupes the item you're holding!");
                        if (player.hasPermission("godpowers.fusrodah") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/fusrodah " + ChatColor.GREEN + "Puts knockback level 10 on the item in your hand!");
                        if (player.hasPermission("godpowers.gaia") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/gaia " + ChatColor.GREEN + "Beautify the earth with every step you take!");
                        if (player.hasPermission("godpowers.godmode") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/godmode " + ChatColor.GREEN + "Makes you invincible to all damage!");
                        player.sendMessage(ChatColor.RED + "/godpowers " + ChatColor.GREEN + "Show plugin information!");
                        if (player.hasPermission("godpowers.hades") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hades " + ChatColor.GREEN + "Corrups the world beneath your feet!");
                        if (player.hasPermission("godpowers.heal") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/heal " + ChatColor.GREEN + "Heals you or the player you specify!");
                        if (player.hasPermission("godpowers.hermes") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hermes " + ChatColor.GREEN + "Makes you run as fast as hermes!");
                        if (player.hasPermission("godpowers.inferno") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/inferno " + ChatColor.GREEN + "Burn the ground beneath you in your fiery rage!");
                        if (player.hasPermission("godpowers.jesus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/jesus " + ChatColor.GREEN + "Grants you the ability to walk on water and lava!");
                        if (player.hasPermission("godpowers.maim") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/maim " + ChatColor.GREEN + "Nearly beat a specified player to death!");
                        if (player.hasPermission("godpowers.medusa") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/medusa " + ChatColor.GREEN + "Curses you with the ability to turn people to stone!");
                        if (player.hasPermission("godpowers.plutus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/plutus " + ChatColor.GREEN + "Puts fortune level 10 on the item in your hand!");
                        if (player.hasPermission("godpowers.poseidon") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/poseidon " + ChatColor.GREEN + "Grants you poseidon's powers in water!");
                        if (player.hasPermission("godpowers.slay") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/slay " + ChatColor.GREEN + "Kills the specified player!");
                        if (player.hasPermission("godpowers.superjump") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/superjump " + ChatColor.GREEN + "Leap tall structures in a single bound!");
                        if (player.hasPermission("godpowers.vulcan") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/vulcan " + ChatColor.GREEN + "Shoot fireballs with a swing of your hand!");
                        if (player.hasPermission("godpowers.zeus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/zeus " + ChatColor.GREEN + "Strike lightning wherever you look!");
                    } else {
                        player.sendMessage("The gods prevent you from using this command.");
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("update")) {
                    Updater updater = new Updater(plugin, 33866, plugin.file, Updater.UpdateType.NO_DOWNLOAD, false);
                    if (updater.getResult().equals(Updater.UpdateResult.NO_UPDATE)) {
                        player.sendMessage(ChatColor.GREEN + "godPowers: " + ChatColor.GOLD + "There are currently no updates available.");
                    } else if (updater.getResult().equals(Updater.UpdateResult.UPDATE_AVAILABLE)) {
                        player.sendMessage(ChatColor.GREEN + "godPowers: " + ChatColor.GOLD + "There is currently an update available, downloading now...");
                        new Updater(plugin, 33866, plugin.file, Updater.UpdateType.NO_VERSION_CHECK, true);
                    }
                }
                if (args[0].equalsIgnoreCase("credits")) {
                    player.sendMessage(ChatColor.DARK_AQUA + "Credits:");
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "UnceCrafter" + ChatColor.BLUE + " (/poseidon)");
                }
            } else {
                player.sendMessage("Incorrect syntax. Use '/godpowers [commands/update]'");
            }
            return true;
        }
        return false;
    }
}
