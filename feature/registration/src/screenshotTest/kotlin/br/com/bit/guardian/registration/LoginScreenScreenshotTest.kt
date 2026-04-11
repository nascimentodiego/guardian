package br.com.bit.guardian.registration

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.registration.ui.login.composable.LoginExpandedScreenPreview
import br.com.bit.guardian.registration.ui.login.composable.LoginScreenProvider
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview(showBackground = true)
@Composable
fun LoginScreenScreenshotTest() {
    LoginExpandedScreenPreview(
        uiState = LoginScreenProvider().values.first()
    )
}
