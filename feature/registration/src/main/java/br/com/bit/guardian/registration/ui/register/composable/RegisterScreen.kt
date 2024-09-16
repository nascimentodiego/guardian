@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.bit.guardian.registration.ui.register.composable

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextTitleMedium
import br.com.bit.guardian.core.designsystem.component.TextTitleSmall
import br.com.bit.guardian.core.designsystem.extension.handleScreenBySize
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.register.composable.compact.RegisterCompactScreen
import br.com.bit.guardian.registration.ui.register.composable.components.CheckDetailItem
import br.com.bit.guardian.registration.ui.register.composable.expanded.RegisterExpandedScreen
import br.com.bit.guardian.registration.ui.register.composable.provider.RegistrationScreenProvider
import br.com.bit.guardian.registration.ui.register.model.PasswordRuleItem
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState?,
    putEmail: (String) -> Unit,
    putPassword: (String) -> Unit,
    putConfPassword: (String) -> Unit,
    onClickRegisterUser: () -> Unit,
    onBackPressClickListener: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    uiState?.let {
        when (uiState) {
            is RegisterUiState.Idle -> {
                IdleState(
                    uiState,
                    modifier,
                    snackbarHostState,
                    putEmail,
                    putPassword,
                    putConfPassword,
                    onClickRegisterUser,
                    onBackPressClickListener
                )
            }

            is RegisterUiState.Success -> {

            }

            is RegisterUiState.Loading -> {

            }

            is RegisterUiState.Error -> {
                IdleState(
                    uiState,
                    modifier,
                    snackbarHostState,
                    putEmail,
                    putPassword,
                    putConfPassword,
                    onClickRegisterUser,
                    onBackPressClickListener
                )
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun IdleState(
    state: RegisterUiState,
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    putEmail: (String) -> Unit,
    putPassword: (String) -> Unit,
    putConfPassword: (String) -> Unit,
    onClickRegisterUser: () -> Unit,
    onNavigationClickListener: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier
        .background(color = GuardianTheme.colors.primary)
        .nestedScroll(scrollBehavior.nestedScrollConnection), snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, topBar = {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = GuardianTheme.colors.primary,
            titleContentColor = GuardianTheme.colors.onBackground,
        ), title = {
            TextTitleMedium(
                titleRes = R.string.login_register_screen, color = Color.White
            )
        }, navigationIcon = {
            IconButton(onClick = { onNavigationClickListener.invoke() }) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            shape = CircleShape, color = Color.White
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = GuardianIcon.ArrowBack,
                        contentDescription = "Localized description",
                        tint = Color.Black
                    )
                }
            }
        }, scrollBehavior = scrollBehavior
        )
    }, floatingActionButton = {
        AnimatedVisibility(
            visible = state.ruleState.enableButton,
            enter = slideInVertically(initialOffsetY = { it * 2 }),
            exit = slideOutVertically(targetOffsetY = { it * 2 }),
        ) {
            FloatingActionButton(onClick = { onClickRegisterUser.invoke() }) {
                Icon(painterResource(id = GuardianIcon.AddUser), contentDescription = null)
            }
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = GuardianTheme.colors.primary)
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    color = GuardianTheme.colors.background, shape = RoundedCornerShape(16.dp)
                )
                .padding(GuardianTheme.dimens.spacingM)
                .verticalScroll(rememberScrollState())
        ) {
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            LocalWindowSizeClass.current.handleScreenBySize(compactScreen = {
                RegisterCompactScreen(
                    state,
                    passwordVisible,
                    { passwordVisible = !passwordVisible },
                    putEmail,
                    putPassword,
                    putConfPassword,
                )
            }, expandedScreen = {
                RegisterExpandedScreen(
                    state,
                    passwordVisible,
                    { passwordVisible = !passwordVisible },
                    putEmail,
                    putPassword,
                    putConfPassword,
                )
            })
        }
    }
}

@Composable
fun RegistrationRules(listOfPassError: List<PasswordRuleItem>) {
    Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
    TextTitleSmall(titleRes = R.string.login_register_rule_title)
    Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
    Divider()

    listOfPassError.forEach {
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
        CheckDetailItem(
            isSuccess = it.isSuccess, textRes = it.textRes
        )
    }
}

@Composable
@Preview(
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:Nexus 10"
)
fun LoginExpandedScreenPreview(
    @PreviewParameter(RegistrationScreenProvider::class) uiState: RegisterUiState
) {
    GuardianTheme {
        RegisterScreen(uiState = uiState,
            putEmail = { _ -> },
            putPassword = { _ -> },
            putConfPassword = { _ -> },
            onClickRegisterUser = {},
            onBackPressClickListener = {})
    }
}