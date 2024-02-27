package br.com.bit.guardian.app_ds_catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app_ds_catalog.model.Tab
import br.com.bit.guardian.app_ds_catalog.model.tabs
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuardianTheme {
                MainScreen(tabs)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(tabs: MutableList<Tab>) {

    var tabSelected by remember { mutableStateOf(tabs[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = GuardianTheme.typography.titleSmall,
                        color = GuardianTheme.colors.onPrimary
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Tabs
            val scroll = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GuardianTheme.dimens.spacingS)
                    .horizontalScroll(scroll),
                horizontalArrangement = Arrangement.Center,
            ) {
                tabs.forEachIndexed { index, tab ->
                    val rounded = tabRoundedCornerShape(index)
                    Row(
                        modifier = Modifier
                            .background(
                                GuardianTheme.colors.primaryContainer, rounded
                            )
                            .heightIn(48.dp)
                            .padding(4.dp)
                            .clickable {
                                tabSelected = tabs[index]
                            },
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Icon(
                            painter = painterResource(id = tab.icon),
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp),
                            tint = GuardianTheme.colors.onPrimary
                        )
                        Text(
                            text = stringResource(id = tab.title),
                            style = GuardianTheme.typography.titleSmall,
                            color = GuardianTheme.colors.onPrimary
                        )

                    }
                    Divider(modifier = Modifier.width(1.dp))
                }
            }

            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .background(GuardianTheme.colors.primary)
            )

            // Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(GuardianTheme.dimens.spacingS)
            ) {
                tabSelected.screen.invoke()
            }
        }
    }
}

private fun tabRoundedCornerShape(index: Int): RoundedCornerShape {
    return when (index) {
        0 -> {
            RoundedCornerShape(
                topStart = 4.dp, bottomStart = 4.dp
            )
        }

        tabs.size - 1 -> {
            RoundedCornerShape(
                topEnd = 4.dp, bottomEnd = 4.dp
            )
        }

        else -> {
            RoundedCornerShape(0.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuardianTheme {
        MainScreen(tabs)
    }
}