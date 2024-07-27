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

val Icons.USFlag: ImageVector
    get() {
        val current = _usFlag
        if (current != null) return current

        return ImageVector.Builder(
            name = "com.nasrabadiam.tictactoe.USFlag",
            defaultWidth = 1235.0.dp,
            defaultHeight = 650.0.dp,
            viewportWidth = 7410.0f,
            viewportHeight = 3900.0f,
        ).apply {
            // <rect width="7410" height="3900" fill="#b22234" />
            path(
                fill = SolidColor(Color(0xFFB22234)),
            ) {
                // M 0 0
                moveTo(x = 0.0f, y = 0.0f)
                // h 7410
                horizontalLineToRelative(dx = 7410.0f)
                // v 3900
                verticalLineToRelative(dy = 3900.0f)
                // h -7410z
                horizontalLineToRelative(dx = -7410.0f)
                close()
            }
            // M0 450 H7410 m0 600 H0 m0 600 H7410 m0 600 H0 m0 600 H7410 m0 600 H0
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 300.0f,
            ) {
                // M 0 450
                moveTo(x = 0.0f, y = 450.0f)
                // H 7410
                horizontalLineTo(x = 7410.0f)
                // m 0 600
                moveToRelative(dx = 0.0f, dy = 600.0f)
                // H 0
                horizontalLineTo(x = 0.0f)
                // m 0 600
                moveToRelative(dx = 0.0f, dy = 600.0f)
                // H 7410
                horizontalLineTo(x = 7410.0f)
                // m 0 600
                moveToRelative(dx = 0.0f, dy = 600.0f)
                // H 0
                horizontalLineTo(x = 0.0f)
                // m 0 600
                moveToRelative(dx = 0.0f, dy = 600.0f)
                // H 7410
                horizontalLineTo(x = 7410.0f)
                // m 0 600
                moveToRelative(dx = 0.0f, dy = 600.0f)
                // H 0
                horizontalLineTo(x = 0.0f)
            }
            // <rect width="2964" height="2100" fill="#3c3b6e" />
            path(
                fill = SolidColor(Color(0xFF3C3B6E)),
            ) {
                // M 0 0
                moveTo(x = 0.0f, y = 0.0f)
                // h 2964
                horizontalLineToRelative(dx = 2964.0f)
                // v 2100
                verticalLineToRelative(dy = 2100.0f)
                // h -2964z
                horizontalLineToRelative(dx = -2964.0f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 247 90
                moveTo(x = 247.0f, y = 90.0f)
                // L 317.53424 307.08203
                lineTo(x = 317.53424f, y = 307.08203f)
                // L 132.87321 172.91795
                lineTo(x = 132.87321f, y = 172.91795f)
                // H 361.12677
                horizontalLineTo(x = 361.12677f)
                // L 176.46577 307.08203z
                lineTo(x = 176.46577f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 247 510
                moveTo(x = 247.0f, y = 510.0f)
                // L 317.53424 727.08203
                lineTo(x = 317.53424f, y = 727.08203f)
                // L 132.87321 592.91797
                lineTo(x = 132.87321f, y = 592.91797f)
                // L 361.12677 592.91797
                lineTo(x = 361.12677f, y = 592.91797f)
                // L 176.46577 727.08203z
                lineTo(x = 176.46577f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 247 930
                moveTo(x = 247.0f, y = 930.0f)
                // L 317.53424 1147.082
                lineTo(x = 317.53424f, y = 1147.082f)
                // L 132.87321 1012.91797
                lineTo(x = 132.87321f, y = 1012.91797f)
                // L 361.12677 1012.91797
                lineTo(x = 361.12677f, y = 1012.91797f)
                // L 176.46577 1147.082z
                lineTo(x = 176.46577f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 247 1350
                moveTo(x = 247.0f, y = 1350.0f)
                // L 317.53424 1567.082
                lineTo(x = 317.53424f, y = 1567.082f)
                // L 132.87321 1432.918
                lineTo(x = 132.87321f, y = 1432.918f)
                // L 361.12677 1432.918
                lineTo(x = 361.12677f, y = 1432.918f)
                // L 176.46577 1567.082z
                lineTo(x = 176.46577f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 247 1770
                moveTo(x = 247.0f, y = 1770.0f)
                // L 317.53424 1987.082
                lineTo(x = 317.53424f, y = 1987.082f)
                // L 132.87321 1852.918
                lineTo(x = 132.87321f, y = 1852.918f)
                // L 361.12677 1852.918
                lineTo(x = 361.12677f, y = 1852.918f)
                // L 176.46577 1987.082z
                lineTo(x = 176.46577f, y = 1987.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 494 300
                moveTo(x = 494.0f, y = 300.0f)
                // L 564.53424 517.08203
                lineTo(x = 564.53424f, y = 517.08203f)
                // L 379.87323 382.91797
                lineTo(x = 379.87323f, y = 382.91797f)
                // L 608.1268 382.91797
                lineTo(x = 608.1268f, y = 382.91797f)
                // L 423.46576 517.08203z
                lineTo(x = 423.46576f, y = 517.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 494 720
                moveTo(x = 494.0f, y = 720.0f)
                // L 564.53424 937.08203
                lineTo(x = 564.53424f, y = 937.08203f)
                // L 379.87323 802.91797
                lineTo(x = 379.87323f, y = 802.91797f)
                // L 608.1268 802.91797
                lineTo(x = 608.1268f, y = 802.91797f)
                // L 423.46576 937.08203z
                lineTo(x = 423.46576f, y = 937.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 494 1140
                moveTo(x = 494.0f, y = 1140.0f)
                // L 564.53424 1357.082
                lineTo(x = 564.53424f, y = 1357.082f)
                // L 379.87323 1222.918
                lineTo(x = 379.87323f, y = 1222.918f)
                // L 608.1268 1222.918
                lineTo(x = 608.1268f, y = 1222.918f)
                // L 423.46576 1357.082z
                lineTo(x = 423.46576f, y = 1357.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 494 1560
                moveTo(x = 494.0f, y = 1560.0f)
                // L 564.53424 1777.082
                lineTo(x = 564.53424f, y = 1777.082f)
                // L 379.87323 1642.918
                lineTo(x = 379.87323f, y = 1642.918f)
                // L 608.1268 1642.918
                lineTo(x = 608.1268f, y = 1642.918f)
                // L 423.46576 1777.082z
                lineTo(x = 423.46576f, y = 1777.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 741 90
                moveTo(x = 741.0f, y = 90.0f)
                // L 811.53424 307.08203
                lineTo(x = 811.53424f, y = 307.08203f)
                // L 626.8732 172.91795
                lineTo(x = 626.8732f, y = 172.91795f)
                // L 855.1268 172.91795
                lineTo(x = 855.1268f, y = 172.91795f)
                // L 670.46576 307.08203z
                lineTo(x = 670.46576f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 741 510
                moveTo(x = 741.0f, y = 510.0f)
                // L 811.53424 727.08203
                lineTo(x = 811.53424f, y = 727.08203f)
                // L 626.8732 592.91797
                lineTo(x = 626.8732f, y = 592.91797f)
                // L 855.1268 592.91797
                lineTo(x = 855.1268f, y = 592.91797f)
                // L 670.46576 727.08203z
                lineTo(x = 670.46576f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 741 930
                moveTo(x = 741.0f, y = 930.0f)
                // L 811.53424 1147.082
                lineTo(x = 811.53424f, y = 1147.082f)
                // L 626.8732 1012.91797
                lineTo(x = 626.8732f, y = 1012.91797f)
                // L 855.1268 1012.91797
                lineTo(x = 855.1268f, y = 1012.91797f)
                // L 670.46576 1147.082z
                lineTo(x = 670.46576f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 741 1350
                moveTo(x = 741.0f, y = 1350.0f)
                // L 811.53424 1567.082
                lineTo(x = 811.53424f, y = 1567.082f)
                // L 626.8732 1432.918
                lineTo(x = 626.8732f, y = 1432.918f)
                // L 855.1268 1432.918
                lineTo(x = 855.1268f, y = 1432.918f)
                // L 670.46576 1567.082z
                lineTo(x = 670.46576f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 741 1770
                moveTo(x = 741.0f, y = 1770.0f)
                // L 811.53424 1987.082
                lineTo(x = 811.53424f, y = 1987.082f)
                // L 626.8732 1852.918
                lineTo(x = 626.8732f, y = 1852.918f)
                // L 855.1268 1852.918
                lineTo(x = 855.1268f, y = 1852.918f)
                // L 670.46576 1987.082z
                lineTo(x = 670.46576f, y = 1987.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 988 300
                moveTo(x = 988.0f, y = 300.0f)
                // L 1058.5342 517.08203
                lineTo(x = 1058.5342f, y = 517.08203f)
                // L 873.8732 382.91797
                lineTo(x = 873.8732f, y = 382.91797f)
                // L 1102.1267 382.91797
                lineTo(x = 1102.1267f, y = 382.91797f)
                // L 917.46576 517.08203z
                lineTo(x = 917.46576f, y = 517.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 988 720
                moveTo(x = 988.0f, y = 720.0f)
                // L 1058.5342 937.08203
                lineTo(x = 1058.5342f, y = 937.08203f)
                // L 873.8732 802.91797
                lineTo(x = 873.8732f, y = 802.91797f)
                // L 1102.1267 802.91797
                lineTo(x = 1102.1267f, y = 802.91797f)
                // L 917.46576 937.08203z
                lineTo(x = 917.46576f, y = 937.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 988 1140
                moveTo(x = 988.0f, y = 1140.0f)
                // L 1058.5342 1357.082
                lineTo(x = 1058.5342f, y = 1357.082f)
                // L 873.8732 1222.918
                lineTo(x = 873.8732f, y = 1222.918f)
                // L 1102.1267 1222.918
                lineTo(x = 1102.1267f, y = 1222.918f)
                // L 917.46576 1357.082z
                lineTo(x = 917.46576f, y = 1357.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 988 1560
                moveTo(x = 988.0f, y = 1560.0f)
                // L 1058.5342 1777.082
                lineTo(x = 1058.5342f, y = 1777.082f)
                // L 873.8732 1642.918
                lineTo(x = 873.8732f, y = 1642.918f)
                // L 1102.1267 1642.918
                lineTo(x = 1102.1267f, y = 1642.918f)
                // L 917.46576 1777.082z
                lineTo(x = 917.46576f, y = 1777.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1235 90
                moveTo(x = 1235.0f, y = 90.0f)
                // L 1305.5342 307.08203
                lineTo(x = 1305.5342f, y = 307.08203f)
                // L 1120.8732 172.91795
                lineTo(x = 1120.8732f, y = 172.91795f)
                // L 1349.1267 172.91795
                lineTo(x = 1349.1267f, y = 172.91795f)
                // L 1164.4658 307.08203z
                lineTo(x = 1164.4658f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1235 510
                moveTo(x = 1235.0f, y = 510.0f)
                // L 1305.5342 727.08203
                lineTo(x = 1305.5342f, y = 727.08203f)
                // L 1120.8732 592.91797
                lineTo(x = 1120.8732f, y = 592.91797f)
                // L 1349.1267 592.91797
                lineTo(x = 1349.1267f, y = 592.91797f)
                // L 1164.4658 727.08203z
                lineTo(x = 1164.4658f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1235 930
                moveTo(x = 1235.0f, y = 930.0f)
                // L 1305.5342 1147.082
                lineTo(x = 1305.5342f, y = 1147.082f)
                // L 1120.8732 1012.91797
                lineTo(x = 1120.8732f, y = 1012.91797f)
                // L 1349.1267 1012.91797
                lineTo(x = 1349.1267f, y = 1012.91797f)
                // L 1164.4658 1147.082z
                lineTo(x = 1164.4658f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1235 1350
                moveTo(x = 1235.0f, y = 1350.0f)
                // L 1305.5342 1567.082
                lineTo(x = 1305.5342f, y = 1567.082f)
                // L 1120.8732 1432.918
                lineTo(x = 1120.8732f, y = 1432.918f)
                // L 1349.1267 1432.918
                lineTo(x = 1349.1267f, y = 1432.918f)
                // L 1164.4658 1567.082z
                lineTo(x = 1164.4658f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1235 1770
                moveTo(x = 1235.0f, y = 1770.0f)
                // L 1305.5342 1987.082
                lineTo(x = 1305.5342f, y = 1987.082f)
                // L 1120.8732 1852.918
                lineTo(x = 1120.8732f, y = 1852.918f)
                // L 1349.1267 1852.918
                lineTo(x = 1349.1267f, y = 1852.918f)
                // L 1164.4658 1987.082z
                lineTo(x = 1164.4658f, y = 1987.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1482 300
                moveTo(x = 1482.0f, y = 300.0f)
                // L 1552.5342 517.08203
                lineTo(x = 1552.5342f, y = 517.08203f)
                // L 1367.8732 382.91797
                lineTo(x = 1367.8732f, y = 382.91797f)
                // L 1596.1267 382.91797
                lineTo(x = 1596.1267f, y = 382.91797f)
                // L 1411.4658 517.08203z
                lineTo(x = 1411.4658f, y = 517.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1482 720
                moveTo(x = 1482.0f, y = 720.0f)
                // L 1552.5342 937.08203
                lineTo(x = 1552.5342f, y = 937.08203f)
                // L 1367.8732 802.91797
                lineTo(x = 1367.8732f, y = 802.91797f)
                // L 1596.1267 802.91797
                lineTo(x = 1596.1267f, y = 802.91797f)
                // L 1411.4658 937.08203z
                lineTo(x = 1411.4658f, y = 937.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1482 1140
                moveTo(x = 1482.0f, y = 1140.0f)
                // L 1552.5342 1357.082
                lineTo(x = 1552.5342f, y = 1357.082f)
                // L 1367.8732 1222.918
                lineTo(x = 1367.8732f, y = 1222.918f)
                // L 1596.1267 1222.918
                lineTo(x = 1596.1267f, y = 1222.918f)
                // L 1411.4658 1357.082z
                lineTo(x = 1411.4658f, y = 1357.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1482 1560
                moveTo(x = 1482.0f, y = 1560.0f)
                // L 1552.5342 1777.082
                lineTo(x = 1552.5342f, y = 1777.082f)
                // L 1367.8732 1642.918
                lineTo(x = 1367.8732f, y = 1642.918f)
                // L 1596.1267 1642.918
                lineTo(x = 1596.1267f, y = 1642.918f)
                // L 1411.4658 1777.082z
                lineTo(x = 1411.4658f, y = 1777.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1729 90
                moveTo(x = 1729.0f, y = 90.0f)
                // L 1799.5342 307.08203
                lineTo(x = 1799.5342f, y = 307.08203f)
                // L 1614.8732 172.91795
                lineTo(x = 1614.8732f, y = 172.91795f)
                // L 1843.1267 172.91795
                lineTo(x = 1843.1267f, y = 172.91795f)
                // L 1658.4658 307.08203z
                lineTo(x = 1658.4658f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1729 510
                moveTo(x = 1729.0f, y = 510.0f)
                // L 1799.5342 727.08203
                lineTo(x = 1799.5342f, y = 727.08203f)
                // L 1614.8732 592.91797
                lineTo(x = 1614.8732f, y = 592.91797f)
                // L 1843.1267 592.91797
                lineTo(x = 1843.1267f, y = 592.91797f)
                // L 1658.4658 727.08203z
                lineTo(x = 1658.4658f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1729 930
                moveTo(x = 1729.0f, y = 930.0f)
                // L 1799.5342 1147.082
                lineTo(x = 1799.5342f, y = 1147.082f)
                // L 1614.8732 1012.91797
                lineTo(x = 1614.8732f, y = 1012.91797f)
                // L 1843.1267 1012.91797
                lineTo(x = 1843.1267f, y = 1012.91797f)
                // L 1658.4658 1147.082z
                lineTo(x = 1658.4658f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1729 1350
                moveTo(x = 1729.0f, y = 1350.0f)
                // L 1799.5342 1567.082
                lineTo(x = 1799.5342f, y = 1567.082f)
                // L 1614.8732 1432.918
                lineTo(x = 1614.8732f, y = 1432.918f)
                // L 1843.1267 1432.918
                lineTo(x = 1843.1267f, y = 1432.918f)
                // L 1658.4658 1567.082z
                lineTo(x = 1658.4658f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1729 1770
                moveTo(x = 1729.0f, y = 1770.0f)
                // L 1799.5342 1987.082
                lineTo(x = 1799.5342f, y = 1987.082f)
                // L 1614.8732 1852.918
                lineTo(x = 1614.8732f, y = 1852.918f)
                // L 1843.1267 1852.918
                lineTo(x = 1843.1267f, y = 1852.918f)
                // L 1658.4658 1987.082z
                lineTo(x = 1658.4658f, y = 1987.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1976 300
                moveTo(x = 1976.0f, y = 300.0f)
                // L 2046.5342 517.08203
                lineTo(x = 2046.5342f, y = 517.08203f)
                // L 1861.8732 382.91797
                lineTo(x = 1861.8732f, y = 382.91797f)
                // L 2090.1267 382.91797
                lineTo(x = 2090.1267f, y = 382.91797f)
                // L 1905.4658 517.08203z
                lineTo(x = 1905.4658f, y = 517.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1976 720
                moveTo(x = 1976.0f, y = 720.0f)
                // L 2046.5342 937.08203
                lineTo(x = 2046.5342f, y = 937.08203f)
                // L 1861.8732 802.91797
                lineTo(x = 1861.8732f, y = 802.91797f)
                // L 2090.1267 802.91797
                lineTo(x = 2090.1267f, y = 802.91797f)
                // L 1905.4658 937.08203z
                lineTo(x = 1905.4658f, y = 937.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1976 1140
                moveTo(x = 1976.0f, y = 1140.0f)
                // L 2046.5342 1357.082
                lineTo(x = 2046.5342f, y = 1357.082f)
                // L 1861.8732 1222.918
                lineTo(x = 1861.8732f, y = 1222.918f)
                // L 2090.1267 1222.918
                lineTo(x = 2090.1267f, y = 1222.918f)
                // L 1905.4658 1357.082z
                lineTo(x = 1905.4658f, y = 1357.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 1976 1560
                moveTo(x = 1976.0f, y = 1560.0f)
                // L 2046.5342 1777.082
                lineTo(x = 2046.5342f, y = 1777.082f)
                // L 1861.8732 1642.918
                lineTo(x = 1861.8732f, y = 1642.918f)
                // L 2090.1267 1642.918
                lineTo(x = 2090.1267f, y = 1642.918f)
                // L 1905.4658 1777.082z
                lineTo(x = 1905.4658f, y = 1777.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2223 90
                moveTo(x = 2223.0f, y = 90.0f)
                // L 2293.5342 307.08203
                lineTo(x = 2293.5342f, y = 307.08203f)
                // L 2108.8733 172.91795
                lineTo(x = 2108.8733f, y = 172.91795f)
                // L 2337.1267 172.91795
                lineTo(x = 2337.1267f, y = 172.91795f)
                // L 2152.4658 307.08203z
                lineTo(x = 2152.4658f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2223 510
                moveTo(x = 2223.0f, y = 510.0f)
                // L 2293.5342 727.08203
                lineTo(x = 2293.5342f, y = 727.08203f)
                // L 2108.8733 592.91797
                lineTo(x = 2108.8733f, y = 592.91797f)
                // L 2337.1267 592.91797
                lineTo(x = 2337.1267f, y = 592.91797f)
                // L 2152.4658 727.08203z
                lineTo(x = 2152.4658f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2223 930
                moveTo(x = 2223.0f, y = 930.0f)
                // L 2293.5342 1147.082
                lineTo(x = 2293.5342f, y = 1147.082f)
                // L 2108.8733 1012.91797
                lineTo(x = 2108.8733f, y = 1012.91797f)
                // L 2337.1267 1012.91797
                lineTo(x = 2337.1267f, y = 1012.91797f)
                // L 2152.4658 1147.082z
                lineTo(x = 2152.4658f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2223 1350
                moveTo(x = 2223.0f, y = 1350.0f)
                // L 2293.5342 1567.082
                lineTo(x = 2293.5342f, y = 1567.082f)
                // L 2108.8733 1432.918
                lineTo(x = 2108.8733f, y = 1432.918f)
                // L 2337.1267 1432.918
                lineTo(x = 2337.1267f, y = 1432.918f)
                // L 2152.4658 1567.082z
                lineTo(x = 2152.4658f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2223 1770
                moveTo(x = 2223.0f, y = 1770.0f)
                // L 2293.5342 1987.082
                lineTo(x = 2293.5342f, y = 1987.082f)
                // L 2108.8733 1852.918
                lineTo(x = 2108.8733f, y = 1852.918f)
                // L 2337.1267 1852.918
                lineTo(x = 2337.1267f, y = 1852.918f)
                // L 2152.4658 1987.082z
                lineTo(x = 2152.4658f, y = 1987.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2470 300
                moveTo(x = 2470.0f, y = 300.0f)
                // L 2540.5342 517.08203
                lineTo(x = 2540.5342f, y = 517.08203f)
                // L 2355.8733 382.91797
                lineTo(x = 2355.8733f, y = 382.91797f)
                // L 2584.1267 382.91797
                lineTo(x = 2584.1267f, y = 382.91797f)
                // L 2399.4658 517.08203z
                lineTo(x = 2399.4658f, y = 517.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2470 720
                moveTo(x = 2470.0f, y = 720.0f)
                // L 2540.5342 937.08203
                lineTo(x = 2540.5342f, y = 937.08203f)
                // L 2355.8733 802.91797
                lineTo(x = 2355.8733f, y = 802.91797f)
                // L 2584.1267 802.91797
                lineTo(x = 2584.1267f, y = 802.91797f)
                // L 2399.4658 937.08203z
                lineTo(x = 2399.4658f, y = 937.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2470 1140
                moveTo(x = 2470.0f, y = 1140.0f)
                // L 2540.5342 1357.082
                lineTo(x = 2540.5342f, y = 1357.082f)
                // L 2355.8733 1222.918
                lineTo(x = 2355.8733f, y = 1222.918f)
                // L 2584.1267 1222.918
                lineTo(x = 2584.1267f, y = 1222.918f)
                // L 2399.4658 1357.082z
                lineTo(x = 2399.4658f, y = 1357.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2470 1560
                moveTo(x = 2470.0f, y = 1560.0f)
                // L 2540.5342 1777.082
                lineTo(x = 2540.5342f, y = 1777.082f)
                // L 2355.8733 1642.918
                lineTo(x = 2355.8733f, y = 1642.918f)
                // L 2584.1267 1642.918
                lineTo(x = 2584.1267f, y = 1642.918f)
                // L 2399.4658 1777.082z
                lineTo(x = 2399.4658f, y = 1777.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2717 90
                moveTo(x = 2717.0f, y = 90.0f)
                // L 2787.5342 307.08203
                lineTo(x = 2787.5342f, y = 307.08203f)
                // L 2602.8733 172.91795
                lineTo(x = 2602.8733f, y = 172.91795f)
                // L 2831.1267 172.91795
                lineTo(x = 2831.1267f, y = 172.91795f)
                // L 2646.4658 307.08203z
                lineTo(x = 2646.4658f, y = 307.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2717 510
                moveTo(x = 2717.0f, y = 510.0f)
                // L 2787.5342 727.08203
                lineTo(x = 2787.5342f, y = 727.08203f)
                // L 2602.8733 592.91797
                lineTo(x = 2602.8733f, y = 592.91797f)
                // L 2831.1267 592.91797
                lineTo(x = 2831.1267f, y = 592.91797f)
                // L 2646.4658 727.08203z
                lineTo(x = 2646.4658f, y = 727.08203f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2717 930
                moveTo(x = 2717.0f, y = 930.0f)
                // L 2787.5342 1147.082
                lineTo(x = 2787.5342f, y = 1147.082f)
                // L 2602.8733 1012.91797
                lineTo(x = 2602.8733f, y = 1012.91797f)
                // L 2831.1267 1012.91797
                lineTo(x = 2831.1267f, y = 1012.91797f)
                // L 2646.4658 1147.082z
                lineTo(x = 2646.4658f, y = 1147.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2717 1350
                moveTo(x = 2717.0f, y = 1350.0f)
                // L 2787.5342 1567.082
                lineTo(x = 2787.5342f, y = 1567.082f)
                // L 2602.8733 1432.918
                lineTo(x = 2602.8733f, y = 1432.918f)
                // L 2831.1267 1432.918
                lineTo(x = 2831.1267f, y = 1432.918f)
                // L 2646.4658 1567.082z
                lineTo(x = 2646.4658f, y = 1567.082f)
                close()
            }
            // M247 90 317.534230 307.082039 132.873218 172.917961 H361.126782 L176.465770 307.082039z
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
            ) {
                // M 2717 1770
                moveTo(x = 2717.0f, y = 1770.0f)
                // L 2787.5342 1987.082
                lineTo(x = 2787.5342f, y = 1987.082f)
                // L 2602.8733 1852.918
                lineTo(x = 2602.8733f, y = 1852.918f)
                // L 2831.1267 1852.918
                lineTo(x = 2831.1267f, y = 1852.918f)
                // L 2646.4658 1987.082z
                lineTo(x = 2646.4658f, y = 1987.082f)
                close()
            }
        }.build().also { _usFlag = it }
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
                imageVector = Icons.USFlag,
                contentDescription = null,
                modifier = Modifier
                    .width((7410.0).dp)
                    .height((250.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _usFlag: ImageVector? = null
