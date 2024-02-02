package fitplan.planner.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fitplan.planner.app.presentation.navigation.NavigationController
import fitplan.planner.app.ui.theme.FitplanTheme
import fitplan.preferences.datastore.UserDatastore
import fitplan.ui.newTask.NewTaskScreen
import fitplan.ui.onboarding.OnBoardingScreen
import fitplan.ui.presentation.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitplanTheme {
                Scaffold { paddingValue ->
                    println(paddingValue)
                    val context = LocalContext.current
                    val datastore = UserDatastore(context)
                    val name = datastore.getName.collectAsState(initial = "")
                    val email = datastore.getEmail.collectAsState(initial = "")
                    val pfp = datastore.getPfp.collectAsState(initial = "")
                    NewTaskScreen()
//                    HomeScreen(pfp.value)
//                    NavigationController(paddingValues = paddingValue)
                }
            }
        }
    }
}

