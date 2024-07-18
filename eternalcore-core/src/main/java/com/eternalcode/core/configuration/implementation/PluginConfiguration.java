package com.eternalcode.core.configuration.implementation;

import com.eternalcode.core.configuration.ReloadableConfig;
import com.eternalcode.core.database.DatabaseType;
import com.eternalcode.core.delay.DelaySettings;
import com.eternalcode.core.feature.helpop.HelpOpSettings;
import com.eternalcode.core.feature.spawn.SpawnSettings;
import com.eternalcode.core.injector.annotations.component.ConfigurationFile;
import com.eternalcode.core.feature.teleportrequest.TeleportRequestSettings;
import java.util.LinkedHashMap;
import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;
import net.dzikoysk.cdn.entity.Exclude;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.io.File;
import java.time.Duration;
import java.util.Map;

@ConfigurationFile
public class PluginConfiguration implements ReloadableConfig {

    @Description({
        "#",
        "# This is the main configuration file for EternalCore.",
        "#",
        "# If you need help with the configuration or have any questions related to EternalCore, join us in our discord, or create an issue on our GitHub.",
        "#",
        "# Issues: https://github.com/EternalCodeTeam/EternalCore/issues",
        "# Discord: https://discord.gg/FQ7jmGBd6c",
        "# Website: https://eternalcode.pl/",
        "# Source Code: https://github.com/EternalCodeTeam/EternalCore",
        "#",
    })

    @Description("# Whether the player should receive information about new plugin updates upon joining the server")
    public boolean shouldReceivePluginUpdates = true;

    @Description({ " ", "# Database Section" })
    public Database database = new Database();

    @Contextual
    public static class Database {
        @Description({
            "# SQL Drivers and ports:",
            "# MySQL (3306), MariaDB (3306), PostgresQL (5432)",
            "# SQLite, H2"
        })
        public DatabaseType databaseType = DatabaseType.SQLITE;

        public String hostname = "127.0.0.1";
        public String database = "database";
        public String username = "root";
        public String password = "U5eStr0ngP4ssw0rd";
        public int port = 3306;
    }

    @Description({ "", "# Join settings" })
    public Join join = new Join();

    @Contextual
    public static class Join {

        @Description("# Teleport to spawn on first join")
        public boolean teleportToSpawnOnFirstJoin = true;

        @Description("# Teleport to spawn on join")
        public boolean teleportToSpawnOnJoin = false;

    }

    @Description({ " ", "# Teleport request section" })
    public TeleportAsk teleportAsk = new TeleportAsk();

    @Contextual
    public static class TeleportAsk implements TeleportRequestSettings {
        @Description("# Time of tpa requests expire")

        @Description({ " ", "# Time of tpa requests expire" })
        public Duration tpaRequestExpire = Duration.ofSeconds(80);

        @Description({ " ", "# Time of teleportation time in /tpa commands" })
        public Duration tpaTimer = Duration.ofSeconds(10);

        @Override
        public Duration teleportExpire() {
            return this.tpaRequestExpire;
        }

        @Override
        public Duration teleportTime() {
            return this.tpaTimer;
        }
    }

    @Description({ " ", "# Teleport section" })
    public Teleport teleport = new Teleport();

    @Contextual
    public static class Teleport implements SpawnSettings {
        @Description("# Teleports the player to spawn after death")
        public boolean teleportToSpawnOnDeath = true;

        @Description("# Teleports the player to respawn point after death")
        public boolean teleportToRespawnPoint = true;

        @Description("# Time of teleportation to spawn")
        public Duration teleportTimeToSpawn = Duration.ofSeconds(5);

        @Description("# Include players with op in teleport to random player")
        public boolean includeOpPlayersInRandomTeleport = false;

        @Override
        public Duration teleportationTimeToSpawn() {
            return this.teleportTimeToSpawn;
        }
    }

    @Description({ " ", "# Homes Section" })
    public Homes homes = new Homes();

    @Contextual
    public static class Homes {
        @Description("# Default home name")
        public String defaultHomeName = "home";

        @Description("# Time of teleportation to homes")
        public Duration teleportTimeToHomes = Duration.ofSeconds(5);

        @Description("# Max homes per permission")
        public Map<String, Integer> maxHomes = new LinkedHashMap<>() {
            {
                put("eternalcore.home.default", 1);
                put("eternalcore.home.vip", 2);
                put("eternalcore.home.premium", 3);
            }
        };
    }

    @Description({ " ", "# Awesome sounds" })
    public Sounds sound = new Sounds();

    @Contextual
    public static class Sounds {
        @Description("# Do you want to enable sound after player join to server?")
        public boolean enabledAfterJoin = true;
        public Sound afterJoin = Sound.BLOCK_NOTE_BLOCK_PLING;
        public float afterJoinVolume = 1.8F;
        public float afterJoinPitch = 1F;

        @Description({ " ", "# Do you want to enable sound after player quit server?" })
        public boolean enableAfterQuit = true;
        public Sound afterQuit = Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
        public float afterQuitVolume = 1.8F;
        public float afterQuitPitch = 1F;

        @Description({ " ", "# Do you want to enable sound after player send message on chat server?" })
        public boolean enableAfterChatMessage = true;
        public Sound afterChatMessage = Sound.ENTITY_ITEM_PICKUP;
        public float afterChatMessageVolume = 1.8F;
        public float afterChatMessagePitch = 1F;

    }

    @Description({ " ", "# HelpOp Section" })
    public HelpOp helpOp = new HelpOp();

    @Contextual
    public static class HelpOp implements HelpOpSettings {

        @Description("# Delay to send the next message under /helpop")
        public Duration helpOpDelay = Duration.ofSeconds(60);

        @Override
        public Duration getHelpOpDelay() {
            return this.helpOpDelay;
        }
    }

    @Description({ " ", "# Repair Section" })
    public Repair repair = new Repair();

    @Contextual
    public static class Repair implements DelaySettings {

        @Description({ " ", "# Repair command cooldown" })
        public Duration repairDelay = Duration.ofSeconds(5);

        @Override
        public Duration delay() {
            return this.repairDelay;
        }
    }

    @Description({ " ", "# Additional formatting options" })
    public Format format = new Format();

    @Contextual
    public static class Format {
        public String separator = "&7, ";
    }

    @Description({ " ", "# Items" })
    public Items items = new Items();

    @Contextual
    public static class Items {
        @Description("# Use unsafe enchantments? Allows you to apply custom enchants to various items")
        public boolean unsafeEnchantments = true;

        @Description({ " ", "# The default item give amount, when no amount is specified in the command." })
        public int defaultGiveAmount = 1;
    }

    @Description({ " ", "# Warp Section" })
    public Warp warp = new Warp();

    @Contextual
    public static class Warp {
        @Description("# Time of teleportation to warp")
        public Duration teleportTimeToWarp = Duration.ofSeconds(5);

        @Description("# Warp inventory should be enabled?")
        public boolean inventoryEnabled = true;

        @Description("# Warp inventory auto add new warps")
        public boolean autoAddNewWarps = true;

        @Description({"# Options below allow you to customize item representing warp added to GUI, ",
            "you can change almost everything inside langueage files, after the warp has been added to the inventory."})
        public  String itemNamePrefix = "&8» &6Warp: &f";

        public String itemLore = "&7Click to teleport!";

        public Material itemMaterial = Material.PLAYER_HEAD;

        @Description("# Texture of the item (only for PLAYER_HEAD material)")
        public String itemTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk4ODVlODIzZmYxNTkyNjdjYmU4MDkwOTNlMzNhNDc2ZTI3NDliNjU5OGNhNGEyYTgxZWU2OTczODAzZmI2NiJ9fX0=";

    }

    @Description({ " ", "# Butcher" })
    public Butcher butcher = new Butcher();

    @Contextual
    public static class Butcher {
        @Description("# Safe number of chunks for command execution (above this number it will not be possible to execute the command)")
        public int safeChunkNumber = 5;
    }

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "config.yml");
    }
}
