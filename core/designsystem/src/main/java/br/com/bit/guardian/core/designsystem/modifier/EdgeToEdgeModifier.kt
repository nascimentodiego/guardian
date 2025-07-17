package br.com.bit.guardian.core.designsystem.modifier

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.statusBarPaddingOnly(): Modifier {
    val top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    return this.padding(top = top)
}

@Composable
fun Modifier.navigationBarPaddingOnly(): Modifier {
    val bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    return this.padding(bottom = bottom)
}