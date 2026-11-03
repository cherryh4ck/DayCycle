package io.cherryh4ck.dayCycle

import io.cherryh4ck.dayCycle.Classes.DayNightCycle
import org.bukkit.GameRules
import org.bukkit.plugin.java.JavaPlugin
import org.bstats.bukkit.Metrics

class DayCycle : JavaPlugin() {
    var dayLength : Long = 0
    var nightLength : Long = 0

    override fun onEnable() {
        saveDefaultConfig()
        val pluginId = 35099
        val metrics = Metrics(this, pluginId)
        logger.info("bStats initialized.")
        loadSettings()
        server.getWorld("world")?.let { world ->
            world.setGameRule(GameRules.ADVANCE_TIME, false)
            DayNightCycle(this, world)
                .runTaskTimer(this, 0L, 1L)
        }
        logger.info("Plugin started.")
    }

    fun loadSettings() {
        dayLength = config.getLong("day-length")
        nightLength = config.getLong("night-length")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
