# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Development Commands

**Quality Checks (Recommended workflow):**
```bash
./check.sh                    # Run all checks (detekt, lint, unit tests, UI tests)
./check.sh --no-detekt        # Skip static analysis
./check.sh --no-lint          # Skip Android lint
./check.sh --no-unit          # Skip unit tests
./check.sh --no-ui            # Skip UI tests
```

**Individual Commands:**
```bash
./gradlew detektAll           # Static analysis with Detekt
./gradlew lint                # Android lint
./gradlew test                # Run all unit tests
./gradlew connectedDebugAndroidTest # Android UI tests
./gradlew build               # Build all platforms
```

**Platform-specific builds:**
```bash
./gradlew :androidApp:assembleDebug        # Android APK
./gradlew :shared:packageDebugReleaseDistributionForCurrentOS # Desktop package
./gradlew :shared:wasmJsBrowserDevelopmentRun # Web development server
```

## Project Architecture

**Kotlin Multiplatform Structure:**
- `shared/` - Common business logic, UI, and tests
- `androidApp/` - Android-specific implementation
- `iosApp/` - iOS-specific implementation  
- Supports: Android, iOS, Desktop (JVM), WebAssembly

**Dependency Injection:**
- Uses `kotlin-inject` with KSP code generation
- Platform-specific `ApplicationComponent` classes in each platform's `di/` folder
- Components are scoped with `@AppScope` and `@ActivityScope`
- ViewModels are injected as factory functions: `(SavedStateHandle) -> GameViewModel`

**State Management Pattern:**
- MVVM with reactive `StateFlow` and Kotlin Coroutines
- `GameUseCase` contains pure business logic with StateFlow properties
- `GameViewModel` manages UI state and uses `SavedStateHandle` for persistence
- State restoration happens in `GameUseCase.restoreGameState()`

**Navigation:**
- Simple two-screen flow: `HomeScreen` → `GameScreen`
- Uses Jetpack Compose Navigation with string routes
- Constants: `HOME_SCREEN_ROUTE = "home"`, `GAME_SCREEN_ROUTE = "game_screen"`

**Testing Strategy:**
- Unit tests in `commonTest/` using `kotlin-test` and `kotlinx-coroutines-test`
- Android UI tests in `androidTest/` using Compose UI testing
- Mocking: Mokkery (multiplatform) and MockK (Android-specific)
- TDD approach - tests drive implementation

**UI Architecture:**
- Jetpack Compose with Material 3 design system
- Responsive design using `WindowSizeClass` with platform-specific implementations
- Custom components: `AutoSizeText`, `SquareLayoutModifier`
- Theme implementations per platform in `ui/theme/Theme.<platform>.kt`

**Key Dependencies:**
- UI: Compose Multiplatform, Material 3, Navigation Compose
- Reactive: Kotlinx Coroutines, StateFlow  
- DI: kotlin-inject with KSP
- Serialization: kotlinx-serialization-json
- Logging: Kermit logger
- Testing: kotlin-test, Mokkery, kotlinx-coroutines-test

**Code Quality:**
- Detekt static analysis with formatting rules configured in `detekt.yml`
- Material 3 design system with dynamic theming support
- Comprehensive test coverage for ViewModels, UseCases, and utilities