package fitplan.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fitplan.core.firebase.LoginViewModel
import fitplan.planner.data.dto.Tasks
import fitplan.ui.login.screens.SignInScreen
import fitplan.ui.theme.backGround
import fitplan.ui.theme.lightGray
import fitplan.ui.theme.monte
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow
import java.security.AccessController
import kotlin.random.Random


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: LoginViewModel
) {
    val isLoginVisible = remember { mutableStateOf(false) }
    val isRegistered = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backGround)
                .then(
                    if (isLoginVisible.value) {
                        Modifier.blur(6.dp)
                    } else {
                        Modifier
                    }
                )
                .drawWithCache {
                    val gradient =
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                backGround.copy(
                                    0.5f
                                )
                            ),
                            startY = size.height / 8.5f,
                            endY = size.height
                        )
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            gradient,
                            blendMode = BlendMode.Multiply
                        )
                    }
                },
        ) {
            itemsIndexed(dummyTasks) { _, _ ->
                Row(modifier = Modifier.basicMarquee()) {
                    repeat(30) {
                        val task = remember {
                            mutableStateOf(dummyTasks[Random.nextInt(0, dummyTasks.size - 1)])
                        }
                        MarqueeCard(taskItem = task.value)
                    }
                }
            }

        }
        AnimatedVisibility(
            visible = isLoginVisible.value,
            enter = slideInHorizontally(initialOffsetX = {
                it
            }),
            exit = slideOutHorizontally(
                targetOffsetX = {
                    it
                }
            )
        ) {
            SignInScreen(isRegistered, navController, viewModel)
        }
        AnimatedVisibility(
            visible = !isLoginVisible.value,
            enter = slideInHorizontally(initialOffsetX = {
                -it
            }),
            exit = slideOutHorizontally(
                targetOffsetX = {
                    -it
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.group_21),
                        contentDescription = null,
                        modifier = Modifier
                            .width(500.dp)
                            .height(650.dp),
                        tint = Color.Unspecified

                    )
                    Row(
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = {
                                    isLoginVisible.value = true
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.White
                                ),
                                border = BorderStroke(1.dp, yellow.copy(alpha = 0.7f)),
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.fitplan),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp),
                                            tint = Color.Unspecified
                                        )
                                        Spacer(modifier = Modifier.width(7.dp))
                                        Text(
                                            text = "Let's Start",
                                            fontSize = 19.sp,
                                            fontFamily = monteEB,
                                            fontWeight = FontWeight.ExtraBold,
                                            softWrap = true,
                                            modifier = Modifier.fillMaxWidth(0.75f),
                                            textAlign = TextAlign.Center,
                                            color = textColor.copy(0.85f),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MarqueeCard(
    modifier: Modifier = Modifier,
    taskItem: Tasks,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier
            .width(200.dp)
            .height(80.dp)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                onClick()
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, if (isSelected) Color.White else Color(0xFFABACAD).copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (isSelected) 1f else 0.5f)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = taskItem.icon ?: R.drawable.fitplan),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 10.dp),
                    tint = if (isSelected) taskItem.color else taskItem.color.copy(0.85f)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = taskItem.name,
                    fontSize = 20.sp,
                    fontFamily = monteEB,
                    fontWeight = FontWeight.ExtraBold,
                    softWrap = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    color = if (isSelected) taskItem.color else taskItem.color.copy(0.85f),
                )
            }
        }
    }
}
