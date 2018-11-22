package com.kennethbrewer.domain.graphics.color

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Unit tests to test the [Color] class.
 *
 * Copyright (C) 2018
 * @author Kenneth Brewer III
 * kennethbrewer3@gmail.com
 * https://www.kennethbrewer.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
class ColorTest {

    private val UNDER_MINIMUM = -1000
    private val OVER_MAXIMUM = 1000
    private val FULL_ON = 255
    private val FULL_OFF = 0

    @Test
    fun testToHexString() {
        assertEquals("#000000", WebSafeColor.BLACK.toColor().toHexString())
        assertEquals("#0000FF", WebSafeColor.BLUE.toColor().toHexString())
        assertEquals("#00FFFF", WebSafeColor.CYAN.toColor().toHexString())
        assertEquals("#A9A9A9", WebSafeColor.DARKGRAY.toColor().toHexString())
        assertEquals("#808080", WebSafeColor.GRAY.toColor().toHexString())
        assertEquals("#008000", WebSafeColor.GREEN.toColor().toHexString())
        assertEquals("#D3D3D3", WebSafeColor.LIGHTGRAY.toColor().toHexString())
        assertEquals("#FF00FF", WebSafeColor.MAGENTA.toColor().toHexString())
        assertEquals("#FFA500", WebSafeColor.ORANGE.toColor().toHexString())
        assertEquals("#FFC0CB", WebSafeColor.PINK.toColor().toHexString())
        assertEquals("#FF0000", WebSafeColor.RED.toColor().toHexString())
        assertEquals("#FFFFFF", WebSafeColor.WHITE.toColor().toHexString())
        assertEquals("#FFFF00", WebSafeColor.YELLOW.toColor().toHexString())
    }

    @Test
    fun testParseHexString() {
        assertEquals(Color.parseHexString("#FFF"), WebSafeColor.WHITE.toColor())
        assertEquals(Color.parseHexString("#FFFFFF"), WebSafeColor.WHITE.toColor())

        val whiteWithAlpha = Color(255, 255, 255, 255)
        assertEquals(Color.parseHexString("#FFFFFFFF"), whiteWithAlpha)

        assertEquals(Color.parseHexString("Invalid color"), Color())
    }

    @Test
    fun testRedUnderMinimum() {
        val noRed = Color(UNDER_MINIMUM, FULL_ON, FULL_ON, FULL_ON)

        assertEquals(noRed.red.toLong(), FULL_OFF.toLong())
        assertEquals(noRed.green.toLong(), FULL_ON.toLong())
        assertEquals(noRed.blue.toLong(), FULL_ON.toLong())
        assertEquals(noRed.alpha.toLong(), FULL_ON.toLong())
    }

    @Test
    fun testGreenUnderMinimum() {
        val noGreen = Color(FULL_ON, UNDER_MINIMUM, FULL_ON, FULL_ON)

        assertEquals(noGreen.red.toLong(), FULL_ON.toLong())
        assertEquals(noGreen.green.toLong(), FULL_OFF.toLong())
        assertEquals(noGreen.blue.toLong(), FULL_ON.toLong())
        assertEquals(noGreen.alpha.toLong(), FULL_ON.toLong())
    }

    @Test
    fun testBlueUnderMinimum() {
        val noBlue = Color(FULL_ON, FULL_ON, UNDER_MINIMUM, FULL_ON)

        assertEquals(noBlue.red.toLong(), FULL_ON.toLong())
        assertEquals(noBlue.green.toLong(), FULL_ON.toLong())
        assertEquals(noBlue.blue.toLong(), FULL_OFF.toLong())
        assertEquals(noBlue.alpha.toLong(), FULL_ON.toLong())
    }

    @Test
    fun testAlphaUnderMinimum() {
        val noAlpha = Color(FULL_ON, FULL_ON, FULL_ON, UNDER_MINIMUM)

        assertEquals(noAlpha.red.toLong(), FULL_ON.toLong())
        assertEquals(noAlpha.green.toLong(), FULL_ON.toLong())
        assertEquals(noAlpha.blue.toLong(), FULL_ON.toLong())
        assertEquals(noAlpha.alpha.toLong(), FULL_OFF.toLong())
    }

    @Test
    fun testRedOverMaximum() {
        val overRed = Color(OVER_MAXIMUM, FULL_OFF, FULL_OFF, FULL_OFF)

        assertEquals(overRed.red.toLong(), FULL_ON.toLong())
        assertEquals(overRed.green.toLong(), FULL_OFF.toLong())
        assertEquals(overRed.blue.toLong(), FULL_OFF.toLong())
        assertEquals(overRed.alpha.toLong(), FULL_OFF.toLong())
    }

    @Test
    fun testGreenOverMaximum() {
        val overGreen = Color(FULL_OFF, OVER_MAXIMUM, FULL_OFF, FULL_OFF)

        assertEquals(overGreen.red.toLong(), FULL_OFF.toLong())
        assertEquals(overGreen.green.toLong(), FULL_ON.toLong())
        assertEquals(overGreen.blue.toLong(), FULL_OFF.toLong())
        assertEquals(overGreen.alpha.toLong(), FULL_OFF.toLong())
    }

    @Test
    fun testBlueOverMaximum() {
        val overBlue = Color(FULL_OFF, FULL_OFF, OVER_MAXIMUM, FULL_OFF)

        assertEquals(overBlue.red.toLong(), FULL_OFF.toLong())
        assertEquals(overBlue.green.toLong(), FULL_OFF.toLong())
        assertEquals(overBlue.blue.toLong(), FULL_ON.toLong())
        assertEquals(overBlue.alpha.toLong(), FULL_OFF.toLong())
    }

    @Test
    fun testAlphaOverMaximum() {
        val overAlpha = Color(FULL_OFF, FULL_OFF, FULL_OFF, OVER_MAXIMUM)

        assertEquals(overAlpha.red.toLong(), FULL_OFF.toLong())
        assertEquals(overAlpha.green.toLong(), FULL_OFF.toLong())
        assertEquals(overAlpha.blue.toLong(), FULL_OFF.toLong())
        assertEquals(overAlpha.alpha.toLong(), FULL_ON.toLong())
    }

    @Test
    fun testEquals() {
        val black = WebSafeColor.BLACK.toColor()
        val white = WebSafeColor.WHITE.toColor()
        val aliceblue = WebSafeColor.ALICEBLUE.toColor()

        assertEquals(black, Color(0, 0, 0))
        assertEquals(white, Color(255, 255, 255))
        assertEquals(aliceblue, Color(240, 248, 255))
    }

    @Test
    fun testGetRgbValue() {
        val black = WebSafeColor.BLACK.toColor()
        val white = WebSafeColor.WHITE.toColor()
        val pink = WebSafeColor.PINK.toColor()

        val blackRgb = 0
        val whiteRgb = 16_777_215
        val pinkRgb  = 16_761_035

        assertEquals(blackRgb, black.getRGBIndex())
        assertEquals(whiteRgb, white.getRGBIndex())
        assertEquals(pinkRgb, pink.getRGBIndex())
    }
}