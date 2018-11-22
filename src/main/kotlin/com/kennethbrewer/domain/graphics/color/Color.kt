package com.kennethbrewer.domain.graphics.color

import java.util.regex.Pattern

/**
 * Represents a color in the RGB color space. Provides utilities to convert to and from the web
 * hexadecimal strings.
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
data class Color (
    private val _red: Int = 0,
    private val _green: Int = 0,
    private val _blue: Int = 0,
    private val _alpha: Int = 255
) {

    companion object {
        private const val MINIMUM_INT = 0
        private const val MAXIMUM_INT = 255

        /**
         * Convert a hexadecimal String to a Color. If the provided String is invalid
         * then a Color with all values set to zero will be returned.
         *
         * @param hexString the hexadecimal String in the format #fff, #ffffff, or #ffffffff
         *
         * @return a Color that matches the values of the hexadecimal values provided
         */
        @JvmStatic fun parseHexString(hexString: String): Color {
            val hexRadix = 16
            val shortHexColor = Pattern.compile("^#([A-Fa-f0-9]{3})$")
            val hexColorNoAlpha = Pattern.compile("^#([A-Fa-f0-9]{6})$")
            val hexColorAlpha = Pattern.compile("^#([A-Fa-f0-9]{8})$")

            if (shortHexColor.matcher(hexString).matches()) {
                val scalar = 17
                val r = Integer.parseInt(hexString.substring(1, 2), hexRadix) * scalar
                val g = Integer.parseInt(hexString.substring(2, 3), hexRadix) * scalar
                val b = Integer.parseInt(hexString.substring(3, 4), hexRadix) * scalar
                return Color(r, g, b, 255)
            } else if (hexColorNoAlpha.matcher(hexString).matches()) {
                val r = Integer.parseInt(hexString.substring(1, 3), hexRadix)
                val g = Integer.parseInt(hexString.substring(3, 5), hexRadix)
                val b = Integer.parseInt(hexString.substring(5, 7), hexRadix)
                return Color(r, g, b, 255)
            } else if (hexColorAlpha.matcher(hexString).matches()) {
                val r = Integer.parseInt(hexString.substring(1, 3), hexRadix)
                val g = Integer.parseInt(hexString.substring(3, 5), hexRadix)
                val b = Integer.parseInt(hexString.substring(5, 7), hexRadix)
                val a = Integer.parseInt(hexString.substring(7, 9), hexRadix)
                return Color(r, g, b, a)
            }

            return Color()
        }
    }

    val red = _red.coerceIn(
        MINIMUM_INT,
        MAXIMUM_INT
    )
    val green = _green.coerceIn(
        MINIMUM_INT,
        MAXIMUM_INT
    )
    val blue = _blue.coerceIn(
        MINIMUM_INT,
        MAXIMUM_INT
    )
    val alpha = _alpha.coerceIn(
        MINIMUM_INT,
        MAXIMUM_INT
    )

    /**
     * @return the hexadecimal value of this color in the format #FFFFFF or if there is an _alpha value #FFFFFFFF
     */
    fun toHexString(): String {
        val red = getHexValue(red)
        val green = getHexValue(green)
        val blue = getHexValue(blue)
        val alpha = if (alpha < 255) getHexValue(alpha) else ""

        return "#$red$green$blue$alpha"
    }

    private fun getHexValue(component: Int): String {
        val prefix = if (component <= 15) "0" else ""
        val postFix = Integer.toHexString(component)
        return "$prefix$postFix".toUpperCase()
    }

    /**
     * @return the index of this color in the RGB color space as an integer
     */
    fun getRGBIndex(): Int {
        return (red * 65536) + (green * 256) + blue
    }
}