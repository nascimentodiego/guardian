package br.com.bit.guardian.core.test

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.v2.createComposeRule
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass
import org.junit.Before
import org.junit.Rule
import org.robolectric.RuntimeEnvironment

/**
 * Base class for Composable tests that need to run across multiple screen
 * configurations (Compact + Expanded).
 *
 * Each test runs once for every qualifier defined in [ScreenQualifier.DEFAULT],
 * ensuring coverage of responsive layout branches (compactScreen vs expandedScreen).
 *
 * ## Usage:
 *
 * ```kotlin
 * @RunWith(ParameterizedRobolectricTestRunner::class)
 * @Config(sdk = [34])
 * class LoginScreenTest(qualifier: String) : ParameterizedComposeScreenTest(qualifier) {
 *
 *     @Test
 *     fun `LoginScreen shows email field`() {
 *         setScreenContent {
 *             LoginScreen(uiState = LoginUiState.Idle(UserView.Empty), ...)
 *         }
 *         composeRule.onNodeWithText("E-mail").assertExists()
 *     }
 *
 *     companion object {
 *         @JvmStatic
 *         @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
 *         fun qualifiers(): Collection<Array<Any>> = defaultQualifiers()
 *     }
 * }
 * ```
 */
abstract class ParameterizedComposeScreenTest(
    private val qualifier: String
) {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    @Before
    fun applyQualifier() {
        RuntimeEnvironment.setQualifiers(qualifier)
    }

    /**
     * Sets the test content wrapped in [MaterialTheme] with [LocalWindowSizeClass]
     * computed from the current qualifier.
     */
    protected fun setScreenContent(content: @Composable () -> Unit) {
        composeRule.setContent {
            val windowSize = rememberGuardianWindowSizeFromConfig()
            CompositionLocalProvider(LocalWindowSizeClass provides windowSize) {
                MaterialTheme {
                    content()
                }
            }
        }
    }

    companion object {
        /**
         * Helper so subclasses don't have to re-declare the default qualifier list
         * in their companion object.
         */
        @JvmStatic
        fun defaultQualifiers(): Collection<Array<Any>> =
            ScreenQualifier.DEFAULT.map { arrayOf(it) }
    }
}
