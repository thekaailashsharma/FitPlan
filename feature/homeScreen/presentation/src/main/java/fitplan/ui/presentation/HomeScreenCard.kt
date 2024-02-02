package fitplan.ui.presentation

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitplan.planner.baseui.utils.circleLayout
import fitplan.ui.theme.backGround
import fitplan.ui.theme.blue
import fitplan.ui.theme.green
import fitplan.ui.theme.indigo
import fitplan.ui.theme.lightGray
import fitplan.ui.theme.merinda
import fitplan.ui.theme.monte
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.orange
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreenCard(
    modifier: Modifier = Modifier,
    priority: Int,
    isSelected: Boolean = false,
    text: String,
    icon: Int,
    description: String,
    date: String,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backGround.copy(alpha = 0.7f),
        ),
        modifier = modifier
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            1.dp,
            if (isSelected) green.copy(0.7f) else textColor.copy(alpha = 0.7f)
        )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
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
                text = priority.toString(),
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier
                    .background(lightGray, shape = CircleShape)
                    .border(BorderStroke(1.dp, yellow.copy(0.75f)), shape = CircleShape)
                    .circleLayout()
                    .padding(8.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = date,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = merinda,
                        color = blue,
                    )
                }
                Row(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = text,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = monte,
                            color = textColor,
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = description,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = merinda,
                            color = indigo,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = yellow.copy(0.65f),
                            modifier = Modifier
                                .size(34.dp)
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}






