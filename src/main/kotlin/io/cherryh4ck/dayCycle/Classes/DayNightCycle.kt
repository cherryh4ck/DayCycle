package io.cherryh4ck.dayCycle.Classes

import io.cherryh4ck.dayCycle.DayCycle
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

class DayNightCycle(
    private val plugin: DayCycle,
    private val world: World,
) : BukkitRunnable() {
    companion object {
        const val dayTicks = 12000.0
        const val nightTicks = 12000.0
        const val ticksPS = 20.0
    }

    private var accumulator = 0.0

    override fun run() {
        val current = world.time
        val isDay = current < dayTicks
        val ticksPerRealTick = if (isDay) {
            dayTicks / (plugin.dayLength * ticksPS)
        } else {
            nightTicks / (plugin.nightLength * ticksPS)
        }
        accumulator += ticksPerRealTick
        val wholeTicks = accumulator.toLong()
        accumulator -= wholeTicks
        if (wholeTicks > 0) {
            world.time = (current + wholeTicks) % 24000
        }
    }
}