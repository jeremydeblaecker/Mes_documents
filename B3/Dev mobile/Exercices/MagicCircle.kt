package com.chillcoding.kotlin.model

import com.chillcoding.kotlin.App
import org.jetbrains.anko.withAlpha
import java.util.*

data class MagicCircle(val maxX: Int, val maxY: Int) {

    var cx: Float = (0..maxX).random().toFloat()
    var cy: Float = (0..maxY).random().toFloat()
    val rad: Float = (0..maxX / 5).random().toFloat()
    var delta = (0..15).random()
    var dx = delta
    var dy = delta
    val color = App.sColors[(0..App.sColors.size - 1).random()].withAlpha((0..0xFF).random())

    fun move() {
        when {
            cx !in 0..maxX -> dx = -dx
            cy !in 0..maxY -> dy = -dy
        }
        cx += dx
        cy += dy
    }

    fun move(dx: Int, dy: Int) {
        when {
            cx !in 0..maxX -> {
                if (dx < 0)
                    cx = 0F
                else
                    cx = maxX.toFloat()
            }
            cy !in 0..maxY -> {
                if (dy > 0)
                    cy = 0F
                else
                    cy = maxY.toFloat()
            }
        }
        cx -= dx * delta
        cy += dy * delta
    }

    fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start
}