package br.com.bit.guardian.core.designsystem.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val GuardianShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

val Shapes.smallCut
    get() = CutCornerShape(topStart = 12.dp)

val Shapes.mediumCut
    get() = CutCornerShape(topStart = 24.dp)

val Shapes.largeCut
    get() = CutCornerShape(topStart = 8.dp)
