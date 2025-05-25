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
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.ui.theme.TacTrixTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

val Icons.Rounded.Group: ImageVector
    get() {
        val current = _group
        if (current != null) return current

        return ImageVector.Builder(
            name = "Rounded.Group",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0f,
            viewportHeight = 960.0f,
        ).apply {
            // M40 800 V688 q0 -34 17.5 -62.5 T104 582 q62 -31 126 -46.5 T360 520 q66 0 130 15.5 T616 582 q29 15 46.5 43.5 T680 688 v112 H40z m720 0 V680 q0 -44 -24.5 -84.5 T666 526 q51 6 96 20.5 t84 35.5 q36 20 55 44.5 t19 53.5 v120 H760z M360 480 q-66 0 -113 -47 t-47 -113 q0 -66 47 -113 t113 -47 q66 0 113 47 t47 113 q0 66 -47 113 t-113 47z m400 -160 q0 66 -47 113 t-113 47 q-11 0 -28 -2.5 t-28 -5.5 q27 -32 41.5 -71 t14.5 -81 q0 -42 -14.5 -81 T544 168 q14 -5 28 -6.5 t28 -1.5 q66 0 113 47 t47 113z M120 720 h480 v-32 q0 -11 -5.5 -20 T580 654 q-54 -27 -109 -40.5 T360 600 q-56 0 -111 13.5 T140 654 q-9 5 -14.5 14 t-5.5 20 v32z m240 -320 q33 0 56.5 -23.5 T440 320 q0 -33 -23.5 -56.5 T360 240 q-33 0 -56.5 23.5 T280 320 q0 33 23.5 56.5 T360 400z m0 320z m0 -400z
            path(
                fill = SolidColor(Color.White),
            ) {
                // M 40 800
                moveTo(x = 40.0f, y = 800.0f)
                // V 688
                verticalLineTo(y = 688.0f)
                // q 0 -34 17.5 -62.5
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -34.0f,
                    dx2 = 17.5f,
                    dy2 = -62.5f,
                )
                // T 104 582
                reflectiveQuadTo(
                    x1 = 104.0f,
                    y1 = 582.0f,
                )
                // q 62 -31 126 -46.5
                quadToRelative(
                    dx1 = 62.0f,
                    dy1 = -31.0f,
                    dx2 = 126.0f,
                    dy2 = -46.5f,
                )
                // T 360 520
                reflectiveQuadTo(
                    x1 = 360.0f,
                    y1 = 520.0f,
                )
                // q 66 0 130 15.5
                quadToRelative(
                    dx1 = 66.0f,
                    dy1 = 0.0f,
                    dx2 = 130.0f,
                    dy2 = 15.5f,
                )
                // T 616 582
                reflectiveQuadTo(
                    x1 = 616.0f,
                    y1 = 582.0f,
                )
                // q 29 15 46.5 43.5
                quadToRelative(
                    dx1 = 29.0f,
                    dy1 = 15.0f,
                    dx2 = 46.5f,
                    dy2 = 43.5f,
                )
                // T 680 688
                reflectiveQuadTo(
                    x1 = 680.0f,
                    y1 = 688.0f,
                )
                // v 112
                verticalLineToRelative(dy = 112.0f)
                // H 40z
                horizontalLineTo(x = 40.0f)
                close()
                // m 720 0
                moveToRelative(dx = 720.0f, dy = 0.0f)
                // V 680
                verticalLineTo(y = 680.0f)
                // q 0 -44 -24.5 -84.5
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -44.0f,
                    dx2 = -24.5f,
                    dy2 = -84.5f,
                )
                // T 666 526
                reflectiveQuadTo(
                    x1 = 666.0f,
                    y1 = 526.0f,
                )
                // q 51 6 96 20.5
                quadToRelative(
                    dx1 = 51.0f,
                    dy1 = 6.0f,
                    dx2 = 96.0f,
                    dy2 = 20.5f,
                )
                // t 84 35.5
                reflectiveQuadToRelative(
                    dx1 = 84.0f,
                    dy1 = 35.5f,
                )
                // q 36 20 55 44.5
                quadToRelative(
                    dx1 = 36.0f,
                    dy1 = 20.0f,
                    dx2 = 55.0f,
                    dy2 = 44.5f,
                )
                // t 19 53.5
                reflectiveQuadToRelative(
                    dx1 = 19.0f,
                    dy1 = 53.5f,
                )
                // v 120
                verticalLineToRelative(dy = 120.0f)
                // H 760z
                horizontalLineTo(x = 760.0f)
                close()
                // M 360 480
                moveTo(x = 360.0f, y = 480.0f)
                // q -66 0 -113 -47
                quadToRelative(
                    dx1 = -66.0f,
                    dy1 = 0.0f,
                    dx2 = -113.0f,
                    dy2 = -47.0f,
                )
                // t -47 -113
                reflectiveQuadToRelative(
                    dx1 = -47.0f,
                    dy1 = -113.0f,
                )
                // q 0 -66 47 -113
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -66.0f,
                    dx2 = 47.0f,
                    dy2 = -113.0f,
                )
                // t 113 -47
                reflectiveQuadToRelative(
                    dx1 = 113.0f,
                    dy1 = -47.0f,
                )
                // q 66 0 113 47
                quadToRelative(
                    dx1 = 66.0f,
                    dy1 = 0.0f,
                    dx2 = 113.0f,
                    dy2 = 47.0f,
                )
                // t 47 113
                reflectiveQuadToRelative(
                    dx1 = 47.0f,
                    dy1 = 113.0f,
                )
                // q 0 66 -47 113
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = 66.0f,
                    dx2 = -47.0f,
                    dy2 = 113.0f,
                )
                // t -113 47z
                reflectiveQuadToRelative(
                    dx1 = -113.0f,
                    dy1 = 47.0f,
                )
                close()
                // m 400 -160
                moveToRelative(dx = 400.0f, dy = -160.0f)
                // q 0 66 -47 113
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = 66.0f,
                    dx2 = -47.0f,
                    dy2 = 113.0f,
                )
                // t -113 47
                reflectiveQuadToRelative(
                    dx1 = -113.0f,
                    dy1 = 47.0f,
                )
                // q -11 0 -28 -2.5
                quadToRelative(
                    dx1 = -11.0f,
                    dy1 = 0.0f,
                    dx2 = -28.0f,
                    dy2 = -2.5f,
                )
                // t -28 -5.5
                reflectiveQuadToRelative(
                    dx1 = -28.0f,
                    dy1 = -5.5f,
                )
                // q 27 -32 41.5 -71
                quadToRelative(
                    dx1 = 27.0f,
                    dy1 = -32.0f,
                    dx2 = 41.5f,
                    dy2 = -71.0f,
                )
                // t 14.5 -81
                reflectiveQuadToRelative(
                    dx1 = 14.5f,
                    dy1 = -81.0f,
                )
                // q 0 -42 -14.5 -81
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -42.0f,
                    dx2 = -14.5f,
                    dy2 = -81.0f,
                )
                // T 544 168
                reflectiveQuadTo(
                    x1 = 544.0f,
                    y1 = 168.0f,
                )
                // q 14 -5 28 -6.5
                quadToRelative(
                    dx1 = 14.0f,
                    dy1 = -5.0f,
                    dx2 = 28.0f,
                    dy2 = -6.5f,
                )
                // t 28 -1.5
                reflectiveQuadToRelative(
                    dx1 = 28.0f,
                    dy1 = -1.5f,
                )
                // q 66 0 113 47
                quadToRelative(
                    dx1 = 66.0f,
                    dy1 = 0.0f,
                    dx2 = 113.0f,
                    dy2 = 47.0f,
                )
                // t 47 113z
                reflectiveQuadToRelative(
                    dx1 = 47.0f,
                    dy1 = 113.0f,
                )
                close()
                // M 120 720
                moveTo(x = 120.0f, y = 720.0f)
                // h 480
                horizontalLineToRelative(dx = 480.0f)
                // v -32
                verticalLineToRelative(dy = -32.0f)
                // q 0 -11 -5.5 -20
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -11.0f,
                    dx2 = -5.5f,
                    dy2 = -20.0f,
                )
                // T 580 654
                reflectiveQuadTo(
                    x1 = 580.0f,
                    y1 = 654.0f,
                )
                // q -54 -27 -109 -40.5
                quadToRelative(
                    dx1 = -54.0f,
                    dy1 = -27.0f,
                    dx2 = -109.0f,
                    dy2 = -40.5f,
                )
                // T 360 600
                reflectiveQuadTo(
                    x1 = 360.0f,
                    y1 = 600.0f,
                )
                // q -56 0 -111 13.5
                quadToRelative(
                    dx1 = -56.0f,
                    dy1 = 0.0f,
                    dx2 = -111.0f,
                    dy2 = 13.5f,
                )
                // T 140 654
                reflectiveQuadTo(
                    x1 = 140.0f,
                    y1 = 654.0f,
                )
                // q -9 5 -14.5 14
                quadToRelative(
                    dx1 = -9.0f,
                    dy1 = 5.0f,
                    dx2 = -14.5f,
                    dy2 = 14.0f,
                )
                // t -5.5 20
                reflectiveQuadToRelative(
                    dx1 = -5.5f,
                    dy1 = 20.0f,
                )
                // v 32z
                verticalLineToRelative(dy = 32.0f)
                close()
                // m 240 -320
                moveToRelative(dx = 240.0f, dy = -320.0f)
                // q 33 0 56.5 -23.5
                quadToRelative(
                    dx1 = 33.0f,
                    dy1 = 0.0f,
                    dx2 = 56.5f,
                    dy2 = -23.5f,
                )
                // T 440 320
                reflectiveQuadTo(
                    x1 = 440.0f,
                    y1 = 320.0f,
                )
                // q 0 -33 -23.5 -56.5
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -33.0f,
                    dx2 = -23.5f,
                    dy2 = -56.5f,
                )
                // T 360 240
                reflectiveQuadTo(
                    x1 = 360.0f,
                    y1 = 240.0f,
                )
                // q -33 0 -56.5 23.5
                quadToRelative(
                    dx1 = -33.0f,
                    dy1 = 0.0f,
                    dx2 = -56.5f,
                    dy2 = 23.5f,
                )
                // T 280 320
                reflectiveQuadTo(
                    x1 = 280.0f,
                    y1 = 320.0f,
                )
                // q 0 33 23.5 56.5
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = 33.0f,
                    dx2 = 23.5f,
                    dy2 = 56.5f,
                )
                // T 360 400z
                reflectiveQuadTo(
                    x1 = 360.0f,
                    y1 = 400.0f,
                )
                close()
                // m 0 320z
                moveToRelative(dx = 0.0f, dy = 320.0f)
                close()
                // m 0 -400z
                moveToRelative(dx = 0.0f, dy = -400.0f)
                close()
            }
        }.build().also { _group = it }
    }

@Preview
@Composable
private fun IconPreview() {
    TacTrixTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = Icons.Rounded.Group,
                contentDescription = null,
                modifier = Modifier
                    .width((960.0).dp)
                    .height((960.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _group: ImageVector? = null
