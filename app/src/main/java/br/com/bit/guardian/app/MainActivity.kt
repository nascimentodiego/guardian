package br.com.bit.guardian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app.model.ReportsUiState
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }


    @Composable
    fun Test() {
        val uiState = viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getReport()
        }

        GuardianTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = GuardianTheme.colors.background
            ) {
                uiState.value?.let { it ->
                    when (it) {
                        is ReportsUiState.Loading -> {
                            Text(
                                text = "Loading...",
                                style = GuardianTheme.typography.titleSmall
                            )
                        }

                        is ReportsUiState.Success -> {
                            Column {
                                it.reports.forEach { report ->
                                    Text(
                                        text = "${report.date} - ${report.user} -  ${report.device}",
                                        style = GuardianTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }

                        is ReportsUiState.Error -> {
                            Text(text = "Ocorreu um Error !")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuardianTheme {
        // Greeting("Android")
    }
}