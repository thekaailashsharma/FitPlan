package fitplan.ui.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor

@Composable
fun InfoWithIcon(info: String, icon: Painter) {
    Row(
        modifier = Modifier.height(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            painter = icon,
            contentDescription = null,
            tint = textColor
        )
        Text(
            text = info,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            fontFamily = monteEB,
            color = textColor
        )
    }
}