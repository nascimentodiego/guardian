# Guardian — Android Project

## Module Structure

```
app/                        → entry point, navigation scaffold (adaptive: NavBar/NavRail)
appdscatalog/               → standalone design system catalog app
core/
  common/                   → Result<T>, shared utils, @ExcludeFromGeneratedReport
  data/
    datasource/             → local (DataStore) and remote data sources
    network/                → Retrofit + OkHttp setup, two clients: API and HotSpot
    repository/             → repository implementations + data models (*Data classes)
  designsystem/             → GuardianTheme, components, icons, dimens, WindowSize
  domain/                   → shared UseCases and entities (cross-feature)
  test/                     → MainDispatcherRule, rememberGuardianWindowSizeFromConfig
  ui/                       → base ViewModel, Event, UiState contracts
feature/
  registration/             → login + register screens (Activity-based entry)
  reports/                  → activity log reports
  settings/                 → user profile settings
  device/
    common/                 → shared device models
    garage/                 → garage device feature
    management/             → device management feature
build-config/               → Gradle convention plugins
```

## Architecture & Patterns

**Stack:** Kotlin · Jetpack Compose · Hilt · Retrofit · Firebase · kotlinx.serialization · kotlinx-datetime

**Layer flow:**
```
feature UI → ViewModel → UseCase (core:domain or feature-local) → Repository → DataSource/Network
```

**MVI pattern:** each screen has a `sealed interface Intent`, a `sealed class UiState`, and a `ViewModel` that processes intents and publishes states.

### ViewModel

Extend `br.com.bit.guardian.core.ui.viewmodel.ViewModel<UiState, Event>`:

```kotlin
@HiltViewModel
class FooViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: FooUseCase
) : ViewModel<FooUiState, FooEvent>(savedStateHandle) {
    init {
        if (!restoreState()) publish(FooUiState.Loading)
    }

    fun onIntent(intent: FooIntent) { /* when(intent) dispatch */ }
}
```

- `publish(state)` → emits new UiState and auto-saves to `SavedStateHandle`
- `sendEvent(event)` → one-shot side effects (navigation, snackbars)
- `restoreState()` → call in `init` to restore from process death; returns `true` if state was restored
- `uiState.withData { }` → safe access when state is non-null

### UiState

`sealed class` with `Parcelable` on each subclass (required for `SavedStateHandle` serialization):

```kotlin
sealed class FooUiState(val data: FooData) {
    @Parcelize data class Idle(val d: FooData) : FooUiState(d), Parcelable
    @Parcelize data class Loading(val d: FooData) : FooUiState(d), Parcelable
    @Parcelize data class Success(val d: FooData) : FooUiState(d), Parcelable
}
```

### Result handling

Use `Flow.asResult()` from `core:common` to wrap async operations:

```kotlin
useCase().asResult().map { result ->
    when (result) {
        is Result.Loading  -> publish(FooUiState.Loading)
        is Result.Success  -> publish(FooUiState.Success(result.data))
        is Result.Error    -> publish(FooUiState.Error)
    }
}.collect()
```

### Adaptive UI

Screens support compact and expanded layouts via `LocalWindowSizeClass.current.handleScreenBySize(...)`. Always provide both `compactScreen` and `expandedScreen` lambdas.

## Convention Plugins

New modules must use a convention plugin — do not configure Compose, Hilt, or Jacoco manually.

| Plugin | Use for |
|---|---|
| `guardian.android.application` | `:app` module |
| `guardian.android.library` | any library module |
| `guardian.android.library.compose` | library with Compose |
| `guardian.android.feature` | feature modules (wires Compose + Jacoco + core deps) |
| `guardian.android.hilt` | adds Hilt to any module |

Feature modules automatically get: `core:common`, `core:data:network`, `core:data:repository`, `core:domain`, `core:designsystem`, Firebase BOM.

## Build & Commands

```bash
# Unit tests (all modules)
./gradlew testDebugUnitTest

# Single module test
./gradlew :feature:reports:testDebugUnitTest

# Static analysis
./gradlew ktlintCheck detekt

# Screenshot tests
./gradlew validateDebugScreenshotTest

# Full build
./gradlew assembleDebug
```

**Build types:** `debug` · `release` · `benchmark`
**Flavors:** `demo` · `prod` (dimension: `contentType`) — currently commented out in feature convention

## Testing

- Unit tests live in `src/test/` — use `MainDispatcherRule` from `core:test`
- Test ViewModels with `Fake*` implementations (not mocks) — see `feature/registration/util/fakes/`
- Fake pattern: implement the UseCase interface, expose a `var result` field to control behavior
- Screenshot tests live in `src/screenshotTest/` — annotate with `@PreviewScreenshotTest`
- Use `rememberGuardianWindowSizeFromConfig()` from `core:test` for adaptive UI tests

## Do Not

- Do not use `LiveData` — the project uses `StateFlow` / `Flow` exclusively
- Do not add core dependencies directly in a feature `build.gradle.kts` — the feature convention plugin already provides them
- Do not mock UseCases in ViewModel tests — implement a `Fake*` class instead
- Do not create a new module without a matching or existing convention plugin
- Do not skip `@Parcelize` on UiState subclasses — state restoration will silently fail
