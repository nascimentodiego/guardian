package br.com.bit.guardian.app.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import br.com.bit.guardian.app.ui.destination.ActivitiesDestination
import br.com.bit.guardian.app.ui.destination.AppDestinations
import br.com.bit.guardian.app.ui.destination.DeviceDestination
import br.com.bit.guardian.app.ui.destination.HomeDestination
import br.com.bit.guardian.app.ui.destination.SettingDestination
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GuardianTheme {
                GuardianNavigationSuiteScaffold(
                    modifier = Modifier
                ) { destination ->
                    when (destination) {
                        AppDestinations.HOME -> HomeDestination()
                        AppDestinations.DEVICES -> DeviceDestination()
                        AppDestinations.ACTIVITIES -> ActivitiesDestination()
                        AppDestinations.SETTINGS -> SettingDestination()
                    }
                }
            }
        }
    }
}