package fitplan.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitplan.ui.theme.backGround
import fitplan.ui.theme.green
import fitplan.ui.theme.monte
import fitplan.ui.theme.textColor

@Composable
fun HomeScreenCard(
    isSelected: Boolean = false,
    text: String,
    icon: Int,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backGround.copy(alpha = 0.7f),
        ),
        modifier = Modifier
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            1.dp,
            if (isSelected) green.copy(0.7f) else textColor.copy(alpha = 0.7f)
        )
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backGround)
                .padding(horizontal = 25.dp)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = monte,
                color = textColor,
            )

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}