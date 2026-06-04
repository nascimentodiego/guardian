[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nascimentodiego_guardian&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nascimentodiego_guardian)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nascimentodiego_guardian&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nascimentodiego_guardian)

![Guardian Logo](tools/imgs/logo_guardian.svg)

# Guardian

A dashboard for home security using the **BiT Platform IoT**. The app provides a centralized
interface to monitor and manage smart home devices, view security logs, control access, and manage
account settings.

---

## Objective

Guardian allows users to:

- Authenticate via email and password (Firebase Auth)
- Monitor IoT home devices in real time
- View activity logs and security events
- Manage devices (e.g., garage door)
- Configure profile, avatar, and nickname

---

## Architecture

The project follows **Clean Architecture** with well-defined layers, combining **MVVM** and **MVI**
patterns in the presentation layer.

```
┌─────────────────────────────────────────────────┐
│                   Presentation                  │
│   Jetpack Compose UI  ←→  ViewModel (MVI/MVVM)  │
│       Intent ──► ViewModel ──► UiState           │
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│                    Domain                        │
│          Use Cases  ─  Entities                  │
└───────────────────────┬─────────────────────────┘
                        │
┌───────────────────────▼─────────────────────────┐
│                     Data                         │
│   Repository  ←  Remote (Firebase / Retrofit)    │
│              ←  Local  (DataStore)               │
└─────────────────────────────────────────────────┘
```

### Patterns

| Pattern                | Description                                               |
|------------------------|-----------------------------------------------------------|
| **Clean Architecture** | Layer separation: Presentation, Domain, and Data          |
| **MVVM + MVI**         | ViewModels receive Intents and emit UiState via StateFlow |
| **Repository Pattern** | Abstraction over data sources (remote and local)          |
| **Result<T>**          | Sealed class representing Success / Error / Loading       |
| **BaseViewModel**      | Generic ViewModel with SavedStateHandle and event channel |

---

## Module Structure

The project is **multi-module**, organized into core modules (shared infrastructure) and feature
modules (encapsulated functionality).

```
guardian/
├── app/                          # Main module, navigation, and root DI
│
├── core/
│   ├── common/                   # Result<T>, exceptions, utilities
│   ├── designsystem/             # Theme, colors, typography, base components
│   ├── domain/                   # Shared domain entities and use cases
│   ├── ui/                       # BaseViewModel, reusable Compose components
│   ├── test/                     # Test utilities (MainDispatcherRule, etc.)
│   └── data/
│       ├── network/              # Retrofit, OkHttp, interceptors
│       ├── datasource/           # Data source interfaces and implementations
│       ├── datastore/            # Preferences with DataStore + Protobuf
│       └── repository/           # Repository implementations and mappers
│
├── feature/
│   ├── registration/             # Login and Registration (Firebase Auth)
│   ├── reports/                  # Activity logs and security events
│   ├── settings/                 # Profile settings (avatar, nickname)
│   └── device/
│       ├── common/               # Shared base across device modules
│       ├── garage/               # Garage door control
│       └── management/           # General device management
│
└── build-config/
    └── convention/               # Convention plugins for standardized Gradle configuration
```

---

## Main Screens

| Screen         | Description                                                              |
|----------------|--------------------------------------------------------------------------|
| **Login**      | Email and password authentication, adaptive layouts (Compact / Expanded) |
| **Register**   | New user registration with email and password validation                 |
| **Home**       | Main dashboard with device status and reports                            |
| **Devices**    | IoT device control and management                                        |
| **Activities** | Security logs and events                                                 |
| **Settings**   | User profile: avatar and nickname                                        |

---

## Libraries

### UI & Compose

| Library                     | Version    | Usage                               |
|-----------------------------|------------|-------------------------------------|
| Jetpack Compose BOM         | 2025.06.00 | Declarative UI framework            |
| Material3                   | 1.3.2      | Design system and visual components |
| Material3 Adaptive          | 1.3.2      | Adaptive layouts (phone/tablet)     |
| Compose Shimmer             | 1.2.0      | Skeleton loading animations         |
| Lifecycle ViewModel Compose | 2.9.1      | ViewModel integration with Compose  |
| Window Layout               | 1.4.0      | Multi-screen size support           |

### Dependency Injection

| Library                 | Version | Usage                                |
|-------------------------|---------|--------------------------------------|
| Hilt                    | 2.56.2  | Dagger-based DI with Android support |
| Hilt Navigation Compose | 1.2.0   | Injection in navigation composables  |

### Networking & Serialization

| Library                    | Version | Usage                              |
|----------------------------|---------|------------------------------------|
| Retrofit                   | 2.9.0   | HTTP client for REST APIs          |
| OkHttp                     | 4.10.0  | Interceptors and request logging   |
| Kotlinx Serialization JSON | 1.7.3   | JSON serialization/deserialization |

### Firebase

| Library               | Version | Usage                       |
|-----------------------|---------|-----------------------------|
| Firebase BOM          | 33.15.0 | Firebase version management |
| Firebase Auth KTX     | -       | User authentication         |
| Firebase Database KTX | 21.0.0  | Real-time database          |

### Local Storage

| Library                       | Version | Usage                                   |
|-------------------------------|---------|-----------------------------------------|
| DataStore                     | 1.1.7   | User preferences storage                |
| Kotlinx Collections Immutable | 0.3.8   | Immutable collections for Compose state |

### Concurrency

| Library            | Version | Usage                                 |
|--------------------|---------|---------------------------------------|
| Kotlinx Coroutines | 1.7.3   | Asynchronous and reactive programming |

### Testing

| Library                 | Version | Usage                                      |
|-------------------------|---------|--------------------------------------------|
| JUnit4                  | 4.13.2  | Unit testing framework                     |
| AndroidX Test Core      | 1.6.1   | Android test utilities                     |
| Espresso                | 3.6.1   | Instrumented UI tests                      |
| Kotlinx Coroutines Test | 1.7.3   | Testing with coroutines and TestDispatcher |
| Compose UI Test         | -       | Compose component testing                  |

### Code Quality

| Tool                  | Usage                            |
|-----------------------|----------------------------------|
| KtLint                | Kotlin code formatting and style |
| Detekt                | Static code analysis             |
| Jacoco                | Test coverage                    |
| Secrets Gradle Plugin | Secure API key management        |

---

## Requirements

- **Android Studio** Ladybug or higher
- **JDK 17**
- **Android SDK** API 21+
- A **Firebase** account with a configured project (`google-services.json` file)

---

## Build

```bash
# Debug
./gradlew assembleDebug

# Release
./gradlew assembleRelease

# Unit tests
./gradlew test

# Test coverage
./gradlew jacocoTestReport
```
