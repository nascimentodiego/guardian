package br.com.bit.guardian.feature.reports.ui.widget.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog

@Composable
fun SuccessReportList(data: List<ActivityLog>) {
    if (data.isEmpty()) {
        EmptyComponent()
    } else {
        LazyRow {
            items(data.size, key = { data[it].key }) { index ->
                ActivityColumnComponent(modifier = Modifier.width(120.dp), data[index])
                Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingXS))
            }
        }
    }
}