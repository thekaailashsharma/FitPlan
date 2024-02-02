package fitplan.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val backGround = Color(0xFF1a1b1b)
val yellow = Color(0xFFfabf31)
val indigo = Color(0xFFdcc0fe)
val orange = Color(0xFFf7653c)
val blue = Color(0xFF38abf2)
val green = Color(0xFF98d65c)
val lightGray = Color(0xFF2f2f2f)
val buttonBackground = Color(0xFF6d6c6d)
val textColor = Color(0xFFFCF9FC)


val appGradient: Brush
    @Composable
    get() = Brush.horizontalGradient(
        0.0f to Color(0xFF927DFF).copy(alpha = 0.87f),
        500.0f to Color(0xFF1A1A1A),
    )