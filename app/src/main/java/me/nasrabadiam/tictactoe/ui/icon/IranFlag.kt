package me.nasrabadiam.tictactoe.ui.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

val Icons.IranFlag: ImageVector
    get() {
        val current = _iranFlag
        if (current != null) return current

        return ImageVector.Builder(
            name = "com.nasrabadiam.tictactoe.IranFlag",
            defaultWidth = 630.0.dp,
            defaultHeight = 360.0.dp,
            viewportWidth = 630.0f,
            viewportHeight = 360.0f,
        ).apply {
            // M0 0 H630 V360 H0z
            path(
                fill = SolidColor(Color(0xFFDA0000)),
            ) {
                // M 0 0
                moveTo(x = 0.0f, y = 0.0f)
                // H 630
                horizontalLineTo(x = 630.0f)
                // V 360
                verticalLineTo(y = 360.0f)
                // H 0z
                horizontalLineTo(x = 0.0f)
                close()
            }
            // M0 0 H630 V240 H0z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 0 0
                moveTo(x = 0.0f, y = 0.0f)
                // H 630
                horizontalLineTo(x = 630.0f)
                // V 240
                verticalLineTo(y = 240.0f)
                // H 0z
                horizontalLineTo(x = 0.0f)
                close()
            }
            // M0 0 H630 V120 H0z
            path(
                fill = SolidColor(Color(0xFF239F40)),
            ) {
                // M 0 0
                moveTo(x = 0.0f, y = 0.0f)
                // H 630
                horizontalLineTo(x = 630.0f)
                // V 120
                verticalLineTo(y = 120.0f)
                // H 0z
                horizontalLineTo(x = 0.0f)
                close()
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 8.4 101.8
                moveTo(x = 8.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 9.799999 114.4
                moveTo(x = 9.799999f, y = 114.4f)
                // L 9.799999 107.4
                lineTo(x = 9.799999f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 32.199997 107.4
                lineTo(x = 32.199997f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 14 113
                moveTo(x = 14.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 37.8 107.4
                lineTo(x = 37.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 60.199997 100.4
                lineTo(x = 60.199997f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 8.4 120
                moveTo(x = 8.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 8.4 240
                moveTo(x = 8.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 8.4 247
                moveTo(x = 8.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 9.799999 259.6
                moveTo(x = 9.799999f, y = 259.6f)
                // L 9.799999 252.6
                lineTo(x = 9.799999f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 32.199997 252.6
                lineTo(x = 32.199997f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 14 258.2
                moveTo(x = 14.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 37.8 252.6
                lineTo(x = 37.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 60.199997 245.6
                lineTo(x = 60.199997f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 64.4 101.8
                moveTo(x = 64.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 65.8 114.4
                moveTo(x = 65.8f, y = 114.4f)
                // L 65.8 107.4
                lineTo(x = 65.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 88.2 107.4
                lineTo(x = 88.2f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 70 113
                moveTo(x = 70.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 93.8 107.4
                lineTo(x = 93.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 116.2 100.4
                lineTo(x = 116.2f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 64.4 120
                moveTo(x = 64.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 64.4 240
                moveTo(x = 64.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 64.4 247
                moveTo(x = 64.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 65.8 259.6
                moveTo(x = 65.8f, y = 259.6f)
                // L 65.8 252.6
                lineTo(x = 65.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 88.2 252.6
                lineTo(x = 88.2f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 70 258.2
                moveTo(x = 70.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 93.8 252.6
                lineTo(x = 93.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 116.2 245.6
                lineTo(x = 116.2f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 120.4 101.8
                moveTo(x = 120.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 121.8 114.4
                moveTo(x = 121.8f, y = 114.4f)
                // L 121.8 107.4
                lineTo(x = 121.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 144.2 107.4
                lineTo(x = 144.2f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 126 113
                moveTo(x = 126.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 149.8 107.4
                lineTo(x = 149.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 172.2 100.4
                lineTo(x = 172.2f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 120.4 120
                moveTo(x = 120.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 120.4 240
                moveTo(x = 120.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 120.4 247
                moveTo(x = 120.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 121.8 259.6
                moveTo(x = 121.8f, y = 259.6f)
                // L 121.8 252.6
                lineTo(x = 121.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 144.2 252.6
                lineTo(x = 144.2f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 126 258.2
                moveTo(x = 126.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 149.8 252.6
                lineTo(x = 149.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 172.2 245.6
                lineTo(x = 172.2f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 176.4 101.8
                moveTo(x = 176.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 177.79999 114.4
                moveTo(x = 177.79999f, y = 114.4f)
                // L 177.79999 107.4
                lineTo(x = 177.79999f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 200.2 107.4
                lineTo(x = 200.2f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 182 113
                moveTo(x = 182.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 205.79999 107.4
                lineTo(x = 205.79999f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 228.2 100.4
                lineTo(x = 228.2f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 176.4 120
                moveTo(x = 176.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 176.4 240
                moveTo(x = 176.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 176.4 247
                moveTo(x = 176.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 177.79999 259.6
                moveTo(x = 177.79999f, y = 259.6f)
                // L 177.79999 252.6
                lineTo(x = 177.79999f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 200.2 252.6
                lineTo(x = 200.2f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 182 258.2
                moveTo(x = 182.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 205.79999 252.6
                lineTo(x = 205.79999f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 228.2 245.6
                lineTo(x = 228.2f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 232.4 101.8
                moveTo(x = 232.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 233.79999 114.4
                moveTo(x = 233.79999f, y = 114.4f)
                // L 233.79999 107.4
                lineTo(x = 233.79999f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 256.19998 107.4
                lineTo(x = 256.19998f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 238 113
                moveTo(x = 238.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 261.8 107.4
                lineTo(x = 261.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 284.19998 100.4
                lineTo(x = 284.19998f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 232.4 120
                moveTo(x = 232.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 232.4 240
                moveTo(x = 232.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 232.4 247
                moveTo(x = 232.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 233.79999 259.6
                moveTo(x = 233.79999f, y = 259.6f)
                // L 233.79999 252.6
                lineTo(x = 233.79999f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 256.19998 252.6
                lineTo(x = 256.19998f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 238 258.2
                moveTo(x = 238.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 261.8 252.6
                lineTo(x = 261.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 284.19998 245.6
                lineTo(x = 284.19998f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 288.4 101.8
                moveTo(x = 288.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 289.8 114.4
                moveTo(x = 289.8f, y = 114.4f)
                // L 289.8 107.4
                lineTo(x = 289.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 312.19998 107.4
                lineTo(x = 312.19998f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 294 113
                moveTo(x = 294.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 317.8 107.4
                lineTo(x = 317.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 340.19998 100.4
                lineTo(x = 340.19998f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 288.4 120
                moveTo(x = 288.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 288.4 240
                moveTo(x = 288.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 288.4 247
                moveTo(x = 288.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 289.8 259.6
                moveTo(x = 289.8f, y = 259.6f)
                // L 289.8 252.6
                lineTo(x = 289.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 312.19998 252.6
                lineTo(x = 312.19998f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 294 258.2
                moveTo(x = 294.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 317.8 252.6
                lineTo(x = 317.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 340.19998 245.6
                lineTo(x = 340.19998f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 344.4 101.8
                moveTo(x = 344.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 345.8 114.4
                moveTo(x = 345.8f, y = 114.4f)
                // L 345.8 107.4
                lineTo(x = 345.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 368.19998 107.4
                lineTo(x = 368.19998f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 350 113
                moveTo(x = 350.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 373.8 107.4
                lineTo(x = 373.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 396.19998 100.4
                lineTo(x = 396.19998f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 344.4 120
                moveTo(x = 344.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 344.4 240
                moveTo(x = 344.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 344.4 247
                moveTo(x = 344.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 345.8 259.6
                moveTo(x = 345.8f, y = 259.6f)
                // L 345.8 252.6
                lineTo(x = 345.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 368.19998 252.6
                lineTo(x = 368.19998f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 350 258.2
                moveTo(x = 350.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 373.8 252.6
                lineTo(x = 373.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 396.19998 245.6
                lineTo(x = 396.19998f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 400.4 101.8
                moveTo(x = 400.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 401.8 114.4
                moveTo(x = 401.8f, y = 114.4f)
                // L 401.8 107.4
                lineTo(x = 401.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 424.19998 107.4
                lineTo(x = 424.19998f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 406 113
                moveTo(x = 406.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 429.8 107.4
                lineTo(x = 429.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 452.19998 100.4
                lineTo(x = 452.19998f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 400.4 120
                moveTo(x = 400.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 400.4 240
                moveTo(x = 400.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 400.4 247
                moveTo(x = 400.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 401.8 259.6
                moveTo(x = 401.8f, y = 259.6f)
                // L 401.8 252.6
                lineTo(x = 401.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 424.19998 252.6
                lineTo(x = 424.19998f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 406 258.2
                moveTo(x = 406.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 429.8 252.6
                lineTo(x = 429.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 452.19998 245.6
                lineTo(x = 452.19998f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 456.4 101.8
                moveTo(x = 456.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 457.8 114.4
                moveTo(x = 457.8f, y = 114.4f)
                // L 457.8 107.4
                lineTo(x = 457.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 480.19998 107.4
                lineTo(x = 480.19998f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 462 113
                moveTo(x = 462.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 485.8 107.4
                lineTo(x = 485.8f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 508.19998 100.4
                lineTo(x = 508.19998f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 456.4 120
                moveTo(x = 456.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 456.4 240
                moveTo(x = 456.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 456.4 247
                moveTo(x = 456.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 457.8 259.6
                moveTo(x = 457.8f, y = 259.6f)
                // L 457.8 252.6
                lineTo(x = 457.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 480.19998 252.6
                lineTo(x = 480.19998f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 462 258.2
                moveTo(x = 462.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 485.8 252.6
                lineTo(x = 485.8f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 508.19998 245.6
                lineTo(x = 508.19998f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 512.4 101.8
                moveTo(x = 512.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 513.80005 114.4
                moveTo(x = 513.80005f, y = 114.4f)
                // L 513.80005 107.4
                lineTo(x = 513.80005f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 536.2 107.4
                lineTo(x = 536.2f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 518 113
                moveTo(x = 518.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 541.80005 107.4
                lineTo(x = 541.80005f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 564.2 100.4
                lineTo(x = 564.2f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 512.4 120
                moveTo(x = 512.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 512.4 240
                moveTo(x = 512.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 512.4 247
                moveTo(x = 512.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 513.80005 259.6
                moveTo(x = 513.80005f, y = 259.6f)
                // L 513.80005 252.6
                lineTo(x = 513.80005f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 536.2 252.6
                lineTo(x = 536.2f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 518 258.2
                moveTo(x = 518.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 541.80005 252.6
                lineTo(x = 541.80005f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 564.2 245.6
                lineTo(x = 564.2f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 568.4 101.8
                moveTo(x = 568.4f, y = 101.8f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 569.80005 114.4
                moveTo(x = 569.80005f, y = 114.4f)
                // L 569.80005 107.4
                lineTo(x = 569.80005f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 592.2 107.4
                lineTo(x = 592.2f, y = 107.4f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 574 113
                moveTo(x = 574.0f, y = 113.0f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 597.80005 107.4
                lineTo(x = 597.80005f, y = 107.4f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 620.2 100.4
                lineTo(x = 620.2f, y = 100.4f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 568.4 120
                moveTo(x = 568.4f, y = 120.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 7 h9 m1 0 h9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 568.4 240
                moveTo(x = 568.4f, y = 240.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
                // m 2.8 0
                moveToRelative(dx = 2.8f, dy = 0.0f)
                // l 25.199999 0
                lineToRelative(dx = 25.199999f, dy = 0.0f)
            }
            // M0 1 h26 M1 10 V5 h8 v4 h8 V5 h-5 M4 9 h2 m20 0 h-5 V5 h8 m0 -5 v9 h8 V0 m-4 0 v9
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
            ) {
                // M 568.4 247
                moveTo(x = 568.4f, y = 247.0f)
                // l 36.399998 0
                lineToRelative(dx = 36.399998f, dy = 0.0f)
                // M 569.80005 259.6
                moveTo(x = 569.80005f, y = 259.6f)
                // L 569.80005 252.6
                lineTo(x = 569.80005f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // l 0 5.6
                lineToRelative(dx = 0.0f, dy = 5.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 592.2 252.6
                lineTo(x = 592.2f, y = 252.6f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // M 574 258.2
                moveTo(x = 574.0f, y = 258.2f)
                // l 2.8 0
                lineToRelative(dx = 2.8f, dy = 0.0f)
                // m 28 0
                moveToRelative(dx = 28.0f, dy = 0.0f)
                // l -7 0
                lineToRelative(dx = -7.0f, dy = 0.0f)
                // L 597.80005 252.6
                lineTo(x = 597.80005f, y = 252.6f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // m 0 -7
                moveToRelative(dx = 0.0f, dy = -7.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
                // l 11.2 0
                lineToRelative(dx = 11.2f, dy = 0.0f)
                // L 620.2 245.6
                lineTo(x = 620.2f, y = 245.6f)
                // m -5.6 0
                moveToRelative(dx = -5.6f, dy = 0.0f)
                // l 0 12.599999
                lineToRelative(dx = 0.0f, dy = 12.599999f)
            }
            // M-.55 .84 a.91 .91 0 0 0 .88 -1.56 1 1 0 0 1 -.88 1.56
            path(
                fill = SolidColor(Color(0xFFDA0000)),
            ) {
                // M 290.25 217.8
                moveTo(x = 290.25f, y = 217.8f)
                // a 40.95 40.95 0 0 0 39.6 -70.2
                arcToRelative(
                    a = 40.95f,
                    b = 40.95f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 39.6f,
                    dy1 = -70.2f,
                )
                // a 45 45 0 0 1 -39.6 70.2
                arcToRelative(
                    a = 45.0f,
                    b = 45.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -39.6f,
                    dy1 = 70.2f,
                )
            }
            // M.62 .66 a.76 .76 0 0 0 -.2 -1.4 1 1 0 0 1 .2 1.4 M0 1 l-.05 -1 L0 -.79 a.3 .3 0 0 0 .12 .1 v.59 l-.04 1z m-.02 -1.85 L0 -.83 a.14 .14 0 0 0 .25 -.14 A.14 .14 0 0 1 0 -.92
            path(
                fill = SolidColor(Color(0xFFDA0000)),
            ) {
                // M 342.9 209.7
                moveTo(x = 342.9f, y = 209.7f)
                // a 34.2 34.2 0 0 0 -9 -63
                arcToRelative(
                    a = 34.2f,
                    b = 34.2f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = -9.0f,
                    dy1 = -63.0f,
                )
                // a 45 45 0 0 1 9 63
                arcToRelative(
                    a = 45.0f,
                    b = 45.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = 9.0f,
                    dy1 = 63.0f,
                )
                // M 315 225
                moveTo(x = 315.0f, y = 225.0f)
                // l -2.25 -45
                lineToRelative(dx = -2.25f, dy = -45.0f)
                // L 315 144.45
                lineTo(x = 315.0f, y = 144.45f)
                // a 13.500001 13.500001 0 0 0 5.4 4.5
                arcToRelative(
                    a = 13.500001f,
                    b = 13.500001f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 5.4f,
                    dy1 = 4.5f,
                )
                // l 0 26.55
                lineToRelative(dx = 0.0f, dy = 26.55f)
                // l -1.8 45z
                lineToRelative(dx = -1.8f, dy = 45.0f)
                close()
                // m -0.9 -83.25
                moveToRelative(dx = -0.9f, dy = -83.25f)
                // L 315 142.65
                lineTo(x = 315.0f, y = 142.65f)
                // a 6.4469476 6.4469476 0 0 0 11.25 -6.3
                arcToRelative(
                    a = 6.4469476f,
                    b = 6.4469476f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 11.25f,
                    dy1 = -6.3f,
                )
                // A 6.3 6.3 0 0 1 315 138.6
                arcTo(
                    horizontalEllipseRadius = 6.3f,
                    verticalEllipseRadius = 6.3f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    x1 = 315.0f,
                    y1 = 138.6f,
                )
            }
            // M-.55 .84 a.91 .91 0 0 0 .88 -1.56 1 1 0 0 1 -.88 1.56
            path(
                fill = SolidColor(Color(0xFFDA0000)),
            ) {
                // M 339.75 217.8
                moveTo(x = 339.75f, y = 217.8f)
                // a 40.95 40.95 0 0 1 -39.6 -70.2
                arcToRelative(
                    a = 40.95f,
                    b = 40.95f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -39.6f,
                    dy1 = -70.2f,
                )
                // a 45 45 0 0 0 39.6 70.2
                arcToRelative(
                    a = 45.0f,
                    b = 45.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 39.6f,
                    dy1 = 70.2f,
                )
            }
            // M.62 .66 a.76 .76 0 0 0 -.2 -1.4 1 1 0 0 1 .2 1.4 M0 1 l-.05 -1 L0 -.79 a.3 .3 0 0 0 .12 .1 v.59 l-.04 1z m-.02 -1.85 L0 -.83 a.14 .14 0 0 0 .25 -.14 A.14 .14 0 0 1 0 -.92
            path(
                fill = SolidColor(Color(0xFFDA0000)),
            ) {
                // M 287.1 209.7
                moveTo(x = 287.1f, y = 209.7f)
                // a 34.2 34.2 0 0 1 9 -63
                arcToRelative(
                    a = 34.2f,
                    b = 34.2f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = 9.0f,
                    dy1 = -63.0f,
                )
                // a 45 45 0 0 0 -9 63
                arcToRelative(
                    a = 45.0f,
                    b = 45.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = -9.0f,
                    dy1 = 63.0f,
                )
                // M 315 225
                moveTo(x = 315.0f, y = 225.0f)
                // l 2.25 -45
                lineToRelative(dx = 2.25f, dy = -45.0f)
                // L 315 144.45
                lineTo(x = 315.0f, y = 144.45f)
                // a 13.500001 13.500001 0 0 1 -5.4 4.5
                arcToRelative(
                    a = 13.500001f,
                    b = 13.500001f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -5.4f,
                    dy1 = 4.5f,
                )
                // l 0 26.55
                lineToRelative(dx = 0.0f, dy = 26.55f)
                // l 1.8 45z
                lineToRelative(dx = 1.8f, dy = 45.0f)
                close()
                // m 0.9 -83.25
                moveToRelative(dx = 0.9f, dy = -83.25f)
                // L 315 142.65
                lineTo(x = 315.0f, y = 142.65f)
                // a 6.4469476 6.4469476 0 0 1 -11.25 -6.3
                arcToRelative(
                    a = 6.4469476f,
                    b = 6.4469476f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -11.25f,
                    dy1 = -6.3f,
                )
                // A 6.3 6.3 0 0 0 315 138.6
                arcTo(
                    horizontalEllipseRadius = 6.3f,
                    verticalEllipseRadius = 6.3f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    x1 = 315.0f,
                    y1 = 138.6f,
                )
            }
        }.build().also { _iranFlag = it }
    }

@Preview
@Composable
private fun IconPreview() {
    TicTacToeTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = Icons.IranFlag,
                contentDescription = null,
                modifier = Modifier
                    .width((630.0).dp)
                    .height((360.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _iranFlag: ImageVector? = null
