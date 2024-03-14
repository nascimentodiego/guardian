package br.com.bit.guardian.core.designsystem.extension

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.unit.dp

fun BoxWithConstraintsScope.isWidthCompact() = this.maxWidth < 600.dp
fun BoxWithConstraintsScope.isWidthMedium() = this.maxWidth > 400.dp && this.maxWidth < 840.dp
fun BoxWithConstraintsScope.isWidthExpanded() = this.maxWidth > 840.dp

fun BoxWithConstraintsScope.isHeightCompact() = this.maxHeight < 480.dp
fun BoxWithConstraintsScope.isHeightMedium() = this.maxHeight > 480.dp && this.maxHeight < 900.dp
fun BoxWithConstraintsScope.isHeightExpanded() = this.maxHeight > 900.dp
