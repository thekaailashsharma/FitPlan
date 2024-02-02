package fitplan.planner.baseui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import fitplan.ui.theme.backGround
import fitplan.ui.theme.green
import fitplan.ui.theme.merinda
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow


@Composable
fun ReusableDialog(
    isVisible: Boolean,
    title: String,
    successRequest: () -> Unit,
    dismissRequest: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    if (isVisible) {
        Dialog(onDismissRequest = dismissRequest) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    Modifier
                        .background(backGround)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = yellow,
                        fontFamily = merinda,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        content()
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(backGround),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        TextButton(onClick = dismissRequest) {
                            Text(
                                "NO",
                                fontWeight = FontWeight.Bold,
                                color = Color.Red.copy(0.85f),
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                fontFamily = monteEB
                            )
                        }
                        TextButton(onClick = successRequest) {
                            Text(
                                "YES",
                                fontWeight = FontWeight.ExtraBold,
                                color = green,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                fontFamily = monteEB
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

    }
}
