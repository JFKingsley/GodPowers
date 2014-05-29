package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import com.FriedTaco.taco.godPowers.util.Updater;
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
                player.sendMessage(ChatColor.GOLD + "by " + ChatColor.BLUE + "FriedTaco" + ChatColor.GOLD + ". It is currently developed by " + ChatColor.BLUE + "Swift" + ChatColor.GOLD + " & " + ChatColor.BLUE + "Zbob750" + ChatColor.GOLD + ".");
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
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODPOWERS_COMMAND_LIST_HEADER);
                        if (player.hasPermission("godpowers.bless") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/bless " + ChatColor.GREEN + StringHandler.LIST_BLESS_DESCRIPTION);
                        if (player.hasPermission("godpowers.demigod") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/demigod " + ChatColor.GREEN + StringHandler.LIST_DEMIGOD_DESCRIPTION);
                        if (player.hasPermission("godpowers.die") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/die " + ChatColor.GREEN + StringHandler.LIST_DIE_DESCRIPTION);
                        if (player.hasPermission("godpowers.dupe") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/dupe " + ChatColor.GREEN + StringHandler.LIST_DUPE_DESCRIPTION);
                        if (player.hasPermission("godpowers.fusrodah") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/fusrodah " + ChatColor.GREEN + StringHandler.LIST_FUSRODAH_DESCRIPTION);
                        if (player.hasPermission("godpowers.gaia") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/gaia " + ChatColor.GREEN + StringHandler.LIST_GAIA_DESCRIPTION);
                        if (player.hasPermission("godpowers.godmode") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/godmode " + ChatColor.GREEN + StringHandler.LIST_GODMODE_DESCRIPTION);
                        player.sendMessage(ChatColor.RED + "/godpowers " + ChatColor.GREEN + "Show plugin information!");
                        if (player.hasPermission("godpowers.hades") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hades " + ChatColor.GREEN + StringHandler.LIST_HADES_DESCRIPTION);
                        if (player.hasPermission("godpowers.heal") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/heal " + ChatColor.GREEN + StringHandler.LIST_HEAL_DESCRIPTION);
                        if (player.hasPermission("godpowers.hermes") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hermes " + ChatColor.GREEN + StringHandler.LIST_HERMES_DESCRIPTION);
                        if (player.hasPermission("godpowers.inferno") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/inferno " + ChatColor.GREEN + StringHandler.LIST_INFERNO_DESCRIPTION);
                        if (player.hasPermission("godpowers.jesus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/jesus " + ChatColor.GREEN + StringHandler.LIST_JESUS_DESCRIPTION);
                        if (player.hasPermission("godpowers.maim") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/maim " + ChatColor.GREEN + StringHandler.LIST_MAIM_DESCRIPTION);
                        if (player.hasPermission("godpowers.medusa") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/medusa " + ChatColor.GREEN + StringHandler.LIST_MEDUSA_DESCRIPTION);
                        if (player.hasPermission("godpowers.plutus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/plutus " + ChatColor.GREEN + StringHandler.LIST_PLUTUS_DESCRIPTION);
                        if (player.hasPermission("godpowers.poseidon") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/poseidon " + ChatColor.GREEN + StringHandler.LIST_POSEIDON_DESCRIPTION);
                        if (player.hasPermission("godpowers.repair") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/repair " + ChatColor.GREEN + StringHandler.LIST_REPAIR_DESCRIPTION);
                        if (player.hasPermission("godpowers.slay") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/slay " + ChatColor.GREEN + StringHandler.LIST_SLAY_DESCRIPTION);
                        if (player.hasPermission("godpowers.superjump") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/superjump " + ChatColor.GREEN + StringHandler.LIST_SUPERJUMP_DESCRIPTION);
                        if (player.hasPermission("godpowers.vulcan") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/vulcan " + ChatColor.GREEN + StringHandler.LIST_VULCAN_DESCRIPTION);
                        if (player.hasPermission("godpowers.zeus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/zeus " + ChatColor.GREEN + StringHandler.LIST_ZEUS_DESCRIPTION);
                    } else {
                        player.sendMessage(StringHandler.GODPOWERS_NOPERMISSION);
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
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "Cookiem42" + ChatColor.BLUE + " (Medusa Head Drop)");
                }
                if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
                    String plVer = plugin.getDescription().getVersion();
                    String plHash = plugin.getDescription().getDescription(); // Yep we're storing the commit hash in the description, deal with it
                    player.sendMessage(ChatColor.DARK_AQUA + "You're running GodPowers version " + ChatColor.GOLD + plVer);
                    player.sendMessage(ChatColor.DARK_AQUA + "This plugin is based on the tree from " + ChatColor.GOLD + plHash);
                }
            } else {
                player.sendMessage("Incorrect syntax. Use '/godpowers [commands/update]'");
            }
            return true;
        }
        return false;
    }
}
