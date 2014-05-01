package com.FriedTaco.taco.godPowers.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bukkit.plugin.Plugin;

public class StringHandler {
    private static final String BUNDLE_NAME = "strings";
    private static Plugin plugin;
    private static ResourceBundle RESOURCE_BUNDLE;
    private static ResourceBundle RESOURCE_BUNDLE0;

    public static String GODPOWERS_COMMAND_LIST_HEADER;
    public static String GODPOWERS_NOPERMISSION;
    public static String LIST_BLESS_DESCRIPTION;
    public static String LIST_DEMIGOD_DESCRIPTION;
    public static String LIST_DIE_DESCRIPTION;
    public static String LIST_DUPE_DESCRIPTION;
    public static String LIST_FUSRODAH_DESCRIPTION;
    public static String LIST_GAIA_DESCRIPTION;
    public static String LIST_GODMODE_DESCRIPTION;
    public static String LIST_HADES_DESCRIPTION;
    public static String LIST_HEAL_DESCRIPTION;
    public static String LIST_HERMES_DESCRIPTION;
    public static String LIST_INFERNO_DESCRIPTION;
    public static String LIST_JESUS_DESCRIPTION;
    public static String LIST_MAIM_DESCRIPTION;
    public static String LIST_MEDUSA_DESCRIPTION;
    public static String LIST_PLUTUS_DESCRIPTION;
    public static String LIST_POSEIDON_DESCRIPTION;
    public static String LIST_REPAIR_DESCRIPTION;
    public static String LIST_SLAY_DESCRIPTION;
    public static String LIST_SUPERJUMP_DESCRIPTION;
    public static String LIST_VULCAN_DESCRIPTION;
    public static String LIST_ZEUS_DESCRIPTION;
    public static String BLESS_PLAYER2;
    public static String BLESS_BLESSED;
    public static String BLESS_CANNOTBLESS;
    public static String BLESS_SYNTAXERROR;
    public static String DEMIGOD_REMOVE;
    public static String DEMIGOD_REMOVEOTHER;
    public static String DEMIGOD_SHARED;
    public static String DEMIGOD_ADD;
    public static String DEMIGOD_ADDED;
    public static String DEMIGOD_ADDOTHER;
    public static String DEMIGOD_ERROR;
    public static String DEMIGOD_YOURSELF;
    public static String DEMIGOD_RETURNYOU;
    public static String DIE_CANTDIE;
    public static String DIE_DEATHMSG;
    public static String DIE_DIE;
    public static String DUPE_DUPE;
    public static String DUPE_ERROR;
    public static String DUPE_NOTHING;
    public static String FUSRODAH_SYNTAX;
    public static String FUSRODAH_FUSRODAH;
    public static String FUSRODAH_ERROR;
    public static String GAIA_REMOVE;
    public static String GAIA_ADD;
    public static String GAIA_SYNTAX;
    public static String GODMODE_ALREADY;
    public static String GODMODE_REMOVE;
    public static String GODMODE_REMOVED;
    public static String GODMODE_REMOVEOTHER;
    public static String GODMODE_ALREADYREMOVE;
    public static String GODMODE_ALREADYREMOVEOTHER;
    public static String GODMODE_ADD;
    public static String GODMODE_ADDED;
    public static String GODMODE_ERROR;
    public static String GODMODE_YOURSELF;
    public static String GODMODE_POWEROF;
    public static String GODMODE_POWEROFADDED;
    public static String GODMODE_LOGIN;
    public static String HADES_REMOVE;
    public static String HADES_ADD;
    public static String HADES_SYNTAX;
    public static String HEAL_HEALED;
    public static String HEAL_HEALEDOTHER;
    public static String HEAL_HEALEDYOU;
    public static String HEAL_ERROR;
    public static String HEAL_YOURSELF;
    public static String HERMES_SYNTAX;
    public static String HERMES_REMOVE;
    public static String HERMES_ADD;
    public static String INFERNO_REMOVE;
    public static String INFERNO_ADD;
    public static String JESUS_ADD;
    public static String JESUS_REMOVE;
    public static String MAIM_ERROR;
    public static String MAIM_GOD;
    public static String MAIM_ATTACK;
    public static String MAIM_DAMAGE;
    public static String MAIM_TARGET;
    public static String MAIM_SYNTAX;
    public static String MEDUSA_ADD;
    public static String MEDUSA_DROPHEAD;
    public static String MEDUSA_PICKUPHEAD;
    public static String MEDUSA_REMOVE;
    public static String MEDUSA_SYNTAX;
    public static String MEDUSA_GONE;
    public static String MEDUSA_CURSED;
    public static String MEDUSA_LORE;
    public static String MEDUSA_MOVEATTEMPT;
    public static String MEDUSA_UNCURSED;
    public static String PLUTUS_ADD;
    public static String PLUTUS_SYNTAX;
    public static String PLUTUS_ERROR;
    public static String POSEIDON_REMOVE;
    public static String POSEIDON_ADD;
    public static String POSEIDON_YOURSELF;
    public static String REPAIR_NOTBROKEN;
    public static String REPAIR_REPAIRED;
    public static String REPAIR_NOTHING;
    public static String REPAIR_NOTALLOWED;
    public static String REPAIR_ERROR;
    public static String REPAIR_SYNTAX;
    public static String SLAY_DOESNTEXIST;
    public static String SLAY_GOD;
    public static String SLAY_SLAINOTHER;
    public static String SLAY_WILLOF;
    public static String SLAY_BEENSLAIN;
    public static String SLAY_SLAINARROWS;
    public static String SLAY_SLAINIGNITE;
    public static String SLAY_SLAINIGNITEYOU;
    public static String SLAY_SLAINDROP;
    public static String SLAY_SLAINDROPYOU;
    public static String SLAY_SLAINLIGHTNING;
    public static String SLAY_SLAINCURSE;
    public static String SLAY_SLAINCURSEYOU;
    public static String SLAY_SLAINCURSEEFFECT;
    public static String SLAY_SLAINVOID;
    public static String SLAY_SLAINVOIDYOU;
    public static String SLAY_SYNTAX;
    public static String SUPERJUMP_REMOVE;
    public static String SUPERJUMP_ADD;
    public static String VULCAN_REMOVE;
    public static String VULCAN_ADD;
    public static String VULCAN_SYNTAX;
    public static String ZEUS_REMOVE;
    public static String ZEUS_ADD;
    public static String ZEUS_SYNTAX;

    private StringHandler() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (Exception e) {
            try {
                return RESOURCE_BUNDLE0.getString(key);
            } catch (Exception f) {
                return '!' + key + '!';
            }
        }
    }

    public static void init(Plugin instance) {
        plugin = instance;
        reload();
    }

    public static void reload() {
        String locale = "";
        try {
            RESOURCE_BUNDLE0 = ResourceBundle.getBundle(BUNDLE_NAME, new UTF8Handler());
            locale = RESOURCE_BUNDLE0.getLocale().toString();
            if (!(locale.equals(""))) {
                locale = "_".concat(locale);
            }
        } catch (MissingResourceException e) {
        }

        try {
            URL[] urls = { plugin.getDataFolder().toURI().toURL() };
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new URLClassLoader(urls), new UTF8Handler());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (MissingResourceException e) {
            plugin.saveResource(BUNDLE_NAME.concat(locale).replace('.', '/').concat(".properties"), false);
            try {
                URL[] urls = { plugin.getDataFolder().toURI().toURL() };
                RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new URLClassLoader(urls), new UTF8Handler());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (Field field : StringHandler.class.getFields()) {
            if (field.getType().equals(String.class) && Modifier.isStatic(field.getModifiers())) {
                try {
                    field.set(null, getString(field.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}