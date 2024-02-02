package fitplan.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fitplan.core.room.HomeViewModel
import fitplan.planner.baseui.navigation.Screens
import fitplan.planner.baseui.utils.ProfileImage
import fitplan.planner.baseui.utils.convertEpochDayToFormattedDate
import fitplan.ui.onboarding.dummyTasks
import fitplan.ui.theme.backGround
import fitplan.ui.theme.buttonBackground
import fitplan.ui.theme.lightGray
import fitplan.ui.theme.merinda
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.orange
import fitplan.ui.theme.textColor
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userPfp: String,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val allTodos = homeViewModel.allTodos.collectAsState(initial = listOf())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = buttonBackground,
                onClick = {
                    navController.navigate(Screens.AddTask.route)
                }) {
                Icon(
                    Icons.Filled.Add,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                )
            }
        },
        containerColor = backGround,
//        drawerContent = { SideDrawer(navController = navController) }
    ) {
        println(it)

//        val homeUiState = homeViewModel.homeUiState
        val state = rememberCollapsingToolbarScaffoldState()

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                val size = (state.toolbarState.progress).dp

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backGround)
                        .parallax(0.6f)
                        .graphicsLayer {
                            alpha = state.toolbarState.progress
                        }
                        .padding(PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(PaddingValues(top = 20.dp, bottom = 20.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        Column() {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Light,
                                            fontSize = 26.sp,
                                            fontFamily = merinda
                                        )
                                    ) {
                                        append("Have a Good Day \n")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 26.sp,
                                            fontFamily = monteEB
                                        )
                                    ) {
                                        append("Kailash")
                                    }
                                },
                                fontWeight = FontWeight.Light,
                                fontSize = 20.sp,
                                color = textColor,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(modifier = Modifier.width(150.dp)) {
                                Text(
                                    text = "You currently have 3 unfinished tasks",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = monteEB
                                )
                            }
                        }

                        ProfileImage(
                            imageUrl = userPfp,
                            modifier = Modifier
                                .shadow(4.dp, RoundedCornerShape(100.dp), true)
                                .border(
                                    width = 1.dp,
                                    color = orange,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .clip(RoundedCornerShape(100.dp))
                                .background(backGround)
                                .size(70.dp)
                                .clickable(interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = {}),
                        )
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = lightGray.copy(alpha = 0.7f),
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                            .shadow(10.dp, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.White)
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {

//                        if (homeUiState.hasNetworkAccess && homeUiState.hasLocationAccess) {
                            Row(
                                modifier = Modifier
                                    .padding(20.dp, 10.dp, 0.dp, 0.dp)
                                    .width(250.dp)
                            ) {
                                Text(
                                    text = "Weather today at 70:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = monteEB,
                                    color = textColor
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column(
                                    modifier = Modifier.height(70.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    InfoWithIcon(
                                        info = "  Temperature: 28 °C",
                                        icon = painterResource(id = R.drawable.thermo_icon)
                                    )
                                    InfoWithIcon(
                                        info = "  Humidity: 30 %",
                                        icon = painterResource(id = R.drawable.humidity_icon)
                                    )
                                    InfoWithIcon(
                                        info = "  Precipitation: $100 mm",
                                        icon = painterResource(id = R.drawable.precipitation_icon)
                                    )
                                }

                                Column(
                                    modifier = Modifier.padding(PaddingValues(start = 10.dp)),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Image(
                                        modifier = Modifier.scale(1f),
                                        painter = painterResource(id = fitplan.ui.presentation.R.drawable.clear_sky),
                                        contentDescription = null
                                    )

                                    Text(
                                        text = "weatherType",
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp,
                                        fontFamily = monteEB,
                                        color = textColor
                                    )
                                }
                            }
//                        } else {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(140.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                when {
//                                    !homeUiState.hasNetworkAccess && !homeUiState.hasLocationAccess -> NoAccessDisplay(
//                                        textToDisplay = "No internet connection and\nlocation is not enabled"
//                                    )
//                                    !homeUiState.hasNetworkAccess -> NoAccessDisplay(
//                                        textToDisplay = "No internet connection"
//                                    )
//                                    !homeUiState.hasLocationAccess -> NoAccessDisplay(
//                                        textToDisplay = "Location is not enabled"
//                                    )
//                                }
//
//                            }
//
//                        }
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .road(Alignment.CenterStart, Alignment.BottomEnd)
                        .padding(60.dp, 16.dp, 16.dp, 16.dp)
                        .size(size),
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backGround)
            ) {
                Box(
                    modifier = Modifier
                ) {

                    Column(
                        modifier = Modifier
                            .padding(PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp))
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(PaddingValues(start = 10.dp, end = 10.dp)),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .height(45.dp)
                                    .width(150.dp)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        onClick = {

                                        }),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "TO DO",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = textColor,
                                    fontFamily = monteEB
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(1.dp)
                                    .background(lightGray)
                            )

                            Box(
                                modifier = Modifier
                                    .height(45.dp)
                                    .width(150.dp)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        onClick = {

                                        }),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "DONE",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = textColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))


                    }

                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        backGround
                                    )
                                )
                            )
                            .align(Alignment.BottomCenter)
                    )

                }

                LazyColumn(
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = 30.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                ) {
                    items(allTodos.value) { todo ->
                        HomeScreenCard(
                            text = todo.title,
                            icon = dummyTasks.find { kk ->
                                kk.name.equals(
                                    todo.tags ?: "FitPlan",
                                    ignoreCase = true
                                )
                            }?.icon ?: fitplan.ui.theme.R.drawable.fitplan,
                            description = todo.description ?: "",
                            date = convertEpochDayToFormattedDate(todo.date),
                            priority = todo.priority
                        )
                    }
                }
            }


        }
    }
}


