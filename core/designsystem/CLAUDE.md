# Guardian Design System — `core:designsystem`

All UI in the project must be built exclusively with tokens and components from this module. Do not use raw `Color`, `TextStyle`, or hardcoded `dp` values inline in feature code.

## Theme Entry Point

Wrap every screen/composable root with `GuardianTheme`. It provides all `CompositionLocal` providers automatically:

```kotlin
GuardianTheme(
    darkTheme = isSystemInDarkTheme(),
    isStatusBarTranslucent = false // true when the screen manages its own status bar
) {
    // your content
}
```

Access design tokens anywhere inside the tree via the `GuardianTheme` object:

```kotlin
GuardianTheme.colors   // AppColors
GuardianTheme.dimens   // AppDims
GuardianTheme.typography // Typography (Material3)
```

---

## Tokens

### Colors — `GuardianTheme.colors`

`AppColors` wraps the Material3 `ColorScheme` and adds Guardian-specific semantic slots.

| Token | Light | Dark | Usage |
|---|---|---|---|
| `primary` | `#452890` (purple) | `#9771FA` | Primary actions, active nav icons |
| `onPrimary` | White | Gray50 | Text/icons on primary bg |
| `primaryContainer` | `#EFEFEF` | `#313036` | Containers with primary context |
| `secondary` | Gray `#CECDC` | Dark gray | Secondary surfaces |
| `tertiary` | Orange `#E84F1A` | `#FE6C3B` | Tertiary/destructive actions |
| `background` | `#D9D6D6` | `#0B0912` | Page background |
| `surface` | White | `#1C191C` | Card/sheet surfaces |
| `error` | `#EB5757` | same | Error states |
| `outline` | Gray400 | same | Input borders, dividers |
| `success` | `#27AE60` | same | Success indicators |
| `warning` | `#B8901A` | same | Warning indicators |
| `disable` | Gray400 | same | Disabled states |

**Text semantic tokens** (prefer these over `onBackground`/`onSurface` for copy):

| Token | Alpha (Light) | Usage |
|---|---|---|
| `textTitle` | 100% black/white | Headings, primary labels |
| `textSubtitle` | 90% | Subtitles, secondary labels |
| `textBody` | 80% | Body copy, descriptions |
| `textDisable` | ~55% | Disabled text |
| `textInverse` | — | Text on dark/colored surfaces |

**Icon tokens:**

| Token | Usage |
|---|---|
| `iconActiveColor` | Selected/active nav icons |
| `iconInactiveColor` | Unselected nav icons (semi-transparent) |
| `iconActiveContainer` | Background circle behind active nav icon |

**Disabled state helper:**
```kotlin
// extension on Color
GuardianTheme.colors.primary.withState(enabled = isEnabled)
```

---

### Spacing — `GuardianTheme.dimens`

`AppDims` is a singleton object. All values are fixed (not responsive).

| Token | Value | Usage |
|---|---|---|
| `spacingHX` | 52dp | Extra-extra large gaps |
| `spacingH` | 48dp | Section separators |
| `spacingXXL` | 32dp | Large section padding |
| `spacingXL` | 24dp | Card inner padding |
| `spacingL` | 20dp | List item padding |
| `spacingM` | 16dp | Default padding / gap |
| `spacingS` | 12dp | Small gap between elements |
| `spacingXS` | 8dp | Tight gap |
| `spacingXXS` | 4dp | Micro gap (icon-to-label) |

---

### Typography — `GuardianTheme.typography`

Two font families: **Lexend** (body, labels, titles) and **Cabin** (display, large titles).

| Material3 slot | Family | Weight | Size | Usage |
|---|---|---|---|---|
| `displayLarge` | Cabin | 700 | 42sp | Hero display text |
| `displayMedium` | Cabin | 500 | 24sp | App name display |
| `titleLarge` | Cabin | 700 | 42sp | Screen hero titles |
| `titleMedium` | Lexend | 600 | 24sp | Section titles |
| `titleSmall` | Cabin | 500 | 14sp | Card titles, small headings |
| `labelMedium` | Lexend | 500 | 16sp | Subtitles, input labels |
| `bodyMedium` | Lexend | 400 | 14sp | Body copy (default) |
| `bodySmall` | Lexend | 400 | 10sp | Captions, supporting text |
| `headlineSmall` | Lexend | 500 | 10sp | Small highlighted labels |

---

### Shapes — `MaterialTheme.shapes`

| Slot | Radius | Usage |
|---|---|---|
| `small` | 4dp | Chips, snackbars |
| `medium` | 16dp | Cards, bottom sheets |
| `large` | 24dp | Dialogs, drawers |

---

## Components

### Buttons — `component/Button.kt`

| Component | Container | Use when |
|---|---|---|
| `PrimaryButton` | `colors.primary` | Main CTA |
| `LoadedButton` | `colors.primary` | CTA with loading state (`isLoading: Boolean`) |
| `LoadedTertiaryButton` | `colors.tertiary` | Secondary CTA with loading state |
| `SimpleButton` | Outlined, transparent | Low-emphasis action |
| `FinishButton` | Transparent + check icon | Completion confirmation |
| `NextButton` | Transparent + chevron-right | Wizard forward navigation |
| `PrevButton` | Transparent + chevron-left | Wizard back navigation |

```kotlin
LoadedButton(
    onClick = { viewModel.onIntent(Intent.Submit) },
    enabled = uiState.isButtonEnabled,
    isLoading = uiState is UiState.Loading
) {
    Text("Save")
}
```

---

### Text — `component/Text.kt`

Pre-built `Text` composables that apply the correct typography slot and default color token. Always prefer these over raw `Text(style = GuardianTheme.typography.X)`.

| Component | Typography slot | Default color |
|---|---|---|
| `TextTitleLarge(@StringRes)` | `titleLarge` | `textTitle` |
| `TextTitleMedium(String / @StringRes)` | `titleMedium` | `textTitle` |
| `TextTitleSmall(String / @StringRes)` | `titleSmall` | `textTitle` |
| `TextBodyMedium(String / @StringRes)` | `bodyMedium` | `textBody` |
| `TextBodySmall(String / @StringRes)` | `bodySmall` | `textBody` |
| `TextBodyLarge(@StringRes)` | `bodyLarge` | `textBody` |
| `TextHeadLineMedium(String)` | `headlineMedium` | `textTitle` |
| `TextHeadLineSmall(String)` | `headlineSmall` | `textTitle` |

All accept `modifier`, `color`, `textAlign`, and overflow/maxLines where applicable.

---

### Input — `component/InputText.kt`

```kotlin
OutlinedInputText(
    text = state.email,
    isError = state.invalidEmail,
    label = R.string.login_input_title_email,
    supportingText = R.string.login_input_error_email,
    onValueChange = { intent(Intent.InputEmail(it)) }
)
```

Use `OutlinedTextFieldDefaults` extensions for color schemes:
- `guardianTextColor()` — standard, uses `textTitle` tokens
- `forceWhite()` — for fields on colored/dark backgrounds
- `onBackgroundColor()` — uses `onBackground` color

---

### Loading — `component/Loading.kt`

Shimmer placeholder. Backed by `valentinilk/shimmer`.

```kotlin
// Fixed size
LoadingComponent(roundSize = 8.dp, height = 64.dp, width = 200.dp)

// Full width
LoadingComponent(roundSize = 8.dp, height = 64.dp)
```

---

### Error — `component/SimpleError.kt`

Adaptive error state component (auto-switches layout based on `WindowWidthSizeClass`).

```kotlin
SimpleError(
    titleRes = R.string.error_title,          // optional
    descriptionRes = R.string.error_desc,     // optional
    buttonLabelRes = R.string.ds_button_try_again,
    onClickListener = { viewModel.onIntent(Intent.Retry) }
)
```

---

### Branding — `component/Guardian.kt`

| Component | Size | Usage |
|---|---|---|
| `GuardianLogoLarge` | 256dp | Splash screens |
| `GuardianLogoMedium` | 128dp | Onboarding headers |
| `GuardianLogoSmall(iconSize)` | 72dp (default) | Compact headers |
| `GuardianTitleLarge` | `displayLarge` | Full-width wordmark |
| `GuardianDisplayMedium` | `displayMedium` | Compact wordmark |

---

### TitleWithIcon — `component/TitleWithIcon.kt`

Section header combining `GeometricAnimatedIcon` + `TextTitleSmall`.

```kotlin
TitleWithIcon(
    modifier = Modifier.fillMaxWidth(),
    title = R.string.section_devices,
    icon = GuardianIcon.Devices,
    backgroundIcon = R.drawable.ds_bg_polygon,
    iconColor = GuardianTheme.colors.iconActiveColor,
    backgroundIconColor = GuardianTheme.colors.error
)
```

---

## Icons — `icon/GuardianIcon`

All icons are `@DrawableRes Int` or `ImageVector`. Use `GuardianIcon` object instead of `R.drawable` directly.

**General:** `ArrowForward`, `ArrowBack`, `ChevronRight`, `ChevronLeft`, `Close`, `Done`, `Edit`, `Logout`, `CheckCircle`, `UnCheckCircle`, `Visibility`, `VisibilityOff`, `AddUser`, `Calendar`, `Timer`, `Empty`

**Navigation:** `Home`, `Devices`, `Reports`, `Settings`, `Phone`

**Wifi strength:** `WifiBar1`–`WifiBar4`, `WifiManege`

**RF Control:** `RFRemoteControl`

**Avatars:** `Avatar01`–`Avatar06` (use `GuardianIcon.getUserAvatar(id)` to resolve by ID 1–6)

---

## Animated Icons — `icon/animations/`

| Component | Effect | Usage |
|---|---|---|
| `GeometricAnimatedIcon` | Icon over a geometric background shape (polygon/circle) | Section headers, `TitleWithIcon` |
| `WiggleButton` | Spring wiggle + color fill on selection | Nav bar items |
| `DropletButton` | Droplet reveal animation on selection | Alternative nav item style |

Background shape drawables: `ds_bg_polygon`, `ds_bg_circle`.

---

## Adaptive Layout

### `LocalWindowSizeClass`

Provides `GuardianWindowSize(widthSizeClass, heightSizeClass)`. Injected by `GuardianTheme`.

**Extension:** `GuardianWindowSize.handleScreenBySize(compactScreen, expandedScreen)`
- Compact: width is `Compact` OR height is `Expanded` (tall phones)
- Expanded: everything else (tablets, foldables)

```kotlin
LocalWindowSizeClass.current.handleScreenBySize(
    compactScreen = { MyCompactLayout(uiState, intent) },
    expandedScreen = { MyExpandedLayout(uiState, intent) }
)
```

**`BoxWithConstraintsScope` helpers** (for inline adaptive sizing):
```kotlin
isWidthCompact()   // maxWidth < 600dp
isWidthMedium()    // 400dp < maxWidth < 840dp
isWidthExpanded()  // maxWidth > 840dp
```

### `LocalAdaptiveContent`

Provides `WindowWidthSizeClass`. Use when only width-driven layout changes are needed (e.g., nav rail vs. bottom bar decisions at app level).

---

## Modifiers & Extensions

| Extension | Where | Usage |
|---|---|---|
| `Modifier.statusBarPaddingOnly()` | `modifier/EdgeToEdgeModifier` | Add top padding only (edge-to-edge screens) |
| `Modifier.navigationBarPaddingOnly()` | `modifier/EdgeToEdgeModifier` | Add bottom padding only |
| `Modifier.backgroundGradientPrimary(shape?)` | `theme/GradientColor` | Radial gradient using `primary` color |
| `Modifier.conditional(condition) { }` | `extension/ComposableExtensions` | Apply modifier conditionally |
| `Modifier.noRippleClickable { }` | `icon/animations/DropletButton` | Click without ripple effect |
| `Color.withState(enabled)` | `extension/ColorsExtentions` | Returns color at 20% alpha when disabled |
| `OutlinedTextFieldDefaults.guardianTextColor()` | `extension/ColorsExtentions` | Standard input colors |
| `OutlinedTextFieldDefaults.forceWhite()` | `extension/ColorsExtentions` | White input colors |
| `OutlinedTextFieldDefaults.onBackgroundColor()` | `extension/ColorsExtentions` | `onBackground` input colors |

---

## Status Bar

- Default: `GuardianTheme` sets status bar color to `colors.primary`.
- Transparent/custom: pass `isStatusBarTranslucent = true` to `GuardianTheme` and manage it manually with `AdaptiveStatusBarStyle`:

```kotlin
AdaptiveStatusBarStyle(background = GuardianTheme.colors.surface)
// automatically sets light/dark icons based on background luminance
```

---

## Previews

Use `@GuardianThemePreviews` to generate both light and dark previews simultaneously:

```kotlin
@GuardianThemePreviews
@Composable
fun MyComponentPreview() {
    GuardianTheme {
        MyComponent()
    }
}
```

Expands to `@Preview(name = "light", ...)` + `@Preview(name = "dark", ...)`.

---

## Do Not

- Do not use raw `Color(0xFF...)` values in feature code — use `GuardianTheme.colors.*`
- Do not hardcode spacing values — use `GuardianTheme.dimens.*`
- Do not use `MaterialTheme.typography` directly — use `GuardianTheme.typography` or the `Text*` components
- Do not use `MaterialTheme.colorScheme` directly — use `GuardianTheme.colors` (it wraps the color scheme with extra semantic tokens)
- Do not use `Modifier.clickable` for nav icons — use `noRippleClickable` or the animated button components
- Do not add new icons as `R.drawable` references in feature code — add them to `GuardianIcon` first
