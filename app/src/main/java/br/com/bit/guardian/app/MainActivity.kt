package br.com.bit.guardian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app.model.ReportState
import br.com.bit.guardian.app.ui.theme.GuardianTheme
import br.com.bit.guardian.core.common.formatters.toDateTimeFormat
import kotlinx.datetime.Clock

class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val uiState = viewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.getReport()
            }

            GuardianTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    uiState.value?.let {
                        Greeting(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(state: ReportState, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Data e hora: " + "2024-01-08T19:34:24.000Z".toDateTimeFormat(),
            modifier = modifier
        )
        Divider(modifier = Modifier.height(25.dp))
        Text(
            text = "from instant: " +  Clock.System.now().toDateTimeFormat(),
            modifier = modifier
        )
        Divider(modifier = Modifier.height(25.dp))

        Text(
            text = state.date,
            modifier = modifier
        )
        Divider(modifier = Modifier.height(8.dp))

        Text(
            text = state.user,
            modifier = modifier
        )
        Divider(modifier = Modifier.height(8.dp))

        Text(
            text = state.device,
            modifier = modifier
        )
        Divider(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuardianTheme {
       // Greeting("Android")
    }
}