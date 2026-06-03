package br.com.bit.guardian.core.test

/**
 * Default screen qualifiers for parameterized tests with Robolectric.
 *
 * The values were chosen so that [handleScreenBySize] functions fall into
 * different branches, covering both compactScreen and expandedScreen paths.
 */
object ScreenQualifier {

    /** Typical phone in portrait — falls into compactScreen */
    const val COMPACT = "w360dp-h640dp-port"

    /** Tablet in landscape with medium height — falls into expandedScreen */
    const val EXPANDED = "w1024dp-h640dp-land"

    /** Default set used by most parameterized tests */
    val DEFAULT: List<String> = listOf(COMPACT, EXPANDED)
}
