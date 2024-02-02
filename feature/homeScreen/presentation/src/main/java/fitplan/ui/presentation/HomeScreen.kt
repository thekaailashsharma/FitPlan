package fitplan.ui.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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
import fitplan.ui.theme.yellow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class, ExperimentalToolbarApi::class
)
@Composable
fun HomeScreen(
    userPfp: String,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val allTodos = homeViewModel.allTodos.collectAsState(initial = listOf())
    val isSearching = homeViewModel.isChecking.collectAsState()
    val searchText = homeViewModel.searchText.collectAsState()
    val todos = homeViewModel.todos.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
        val state = rememberCollapsingToolbarScaffoldState()
        LaunchedEffect(key1 = isSearching.value) {
            if (isSearching.value) {
                state.toolbarState.collapse()
            } else {
                state.toolbarState.expand()
            }
        }

//        val homeUiState = homeViewModel.homeUiState


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
                                lineHeight = 35.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(modifier = Modifier.width(150.dp)) {
                                Text(
                                    text = "You currently have ${
                                        allTodos.value.filter { too ->
                                            !too.completed
                                        }.size
                                    } unfinished tasks",
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


                    TextField(
                        value = searchText.value,
                        onValueChange = { value ->
                            coroutineScope.launch {
                                homeViewModel.onSearchQueryChange(value)
                                homeViewModel.searchQuery(value).collectLatest { too ->
                                    println("Toooo: $too")
                                    homeViewModel.updateTodos(too)
                                }

                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = lightGray,
                            focusedIndicatorColor = yellow.copy(0.5f),
                            unfocusedIndicatorColor = yellow.copy(0.5f),
                            disabledIndicatorColor = textColor,
                            errorIndicatorColor = textColor,
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = textColor
                            )
                        },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Search",
                                tint = textColor,
                                modifier = Modifier.clickable {
//                                        isSearchVisible = !isSearchVisible
//                                        allWastes = storedWastes
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 20.dp)
                            .shadow(50.dp, shape = RoundedCornerShape(10.dp)),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        )
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
                AnimatedVisibility(visible = !isSearching.value) {
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
                }

                LazyColumn(
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = 30.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                ) {
                    items(todos.value.ifEmpty { allTodos.value }) { todo ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HomeScreenCard(
                                modifier = Modifier
                                    .animateItemPlacement(),
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
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    }
                }
            }
        }
    }
}



