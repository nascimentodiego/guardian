package br.com.bit.guardian.feature.reports.ui.widget.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.component.SimpleError
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun ErrorReportList(modifier: Modifier = Modifier, retry: () -> Unit) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SimpleError(
            backgroundColor = GuardianTheme.colors.primaryContainer,
            widthSizeClass = WindowWidthSizeClass.Compact,
            titleRes = R.string.ds_simple_error_title,
            descriptionRes = R.string.ds_simple_error_description
        ) {
            retry.invoke()
        }
    }
}

@ThemePreviews
@Composable
fun ErrorReportListPreview() {
    GuardianTheme {
        ErrorReportList {}
    }
}
