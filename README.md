# TacTrix - Multiplatform Tic Tac Toe Game

![CI-MAIN](https://github.com/nasrabadiAM/tictactoe/actions/workflows/tests.yml/badge.svg?branch=main)
[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF.svg?logo=kotlin)](https://kotlinlang.org/docs/multiplatform.html)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)

TacTrix is a modern, cross-platform implementation of the classic Tic Tac Toe game built with Kotlin Multiplatform and Jetpack Compose. Experience the timeless game with a contemporary touch across all your devices.

## 🚀 Try TacTrix

- **🌐 Web Demo**: [Play in your browser](https://nasrabadiam.github.io/tictactoe/)
- **📱 Android**: <a href='https://cafebazaar.ir/app/me.nasrabadiam.tictactoe'><img alt='Download from CafeBazaar' src='https://s.cafebazaar.ir/2/images/get-cafebazaar-en.png' width="200px"/></a>
- **🖥️ Desktop**: Build from source (instructions below)
- **📱 iOS**: Build from source (instructions below)

## ✨ Features

### 🎮 Game Features
- **Classic Gameplay**: Traditional 3x3 Tic Tac Toe with two-player local multiplayer
- **Score Tracking**: Keep track of wins, losses, and draws across game sessions
- **Game State Persistence**: Your game progress is automatically saved
- **Responsive UI**: Optimized layouts for different screen sizes and orientations

### 🎨 Design & UX
- **Material 3 Design**: Modern Material Design 3 components and animations
- **Dynamic Theming**: Supports system dynamic colors on compatible platforms
- **Adaptive Layouts**: Responsive design that works beautifully on phones, tablets, and desktop
- **Smooth Animations**: Delightful transitions and visual feedback

### 🔧 Technical Features
- **Cross-Platform**: Single codebase running on Android, iOS, Desktop, and Web
- **Modern Architecture**: MVVM pattern with reactive state management
- **Type-Safe Navigation**: Jetpack Compose Navigation with compile-time safety
- **Dependency Injection**: Clean architecture with [kotlin-inject](https://github.com/evant/kotlin-inject)
- **Comprehensive Testing**: Unit tests, UI tests, and TDD approach

## 🏗️ Architecture

TacTrix showcases modern mobile development practices:

- **Kotlin Multiplatform**: Shared business logic across all platforms
- **Compose Multiplatform**: Shared User Interface across all platforms
- **Jetpack Compose**: Declarative UI framework for consistent experiences
- **MVVM + StateFlow**: Reactive architecture with unidirectional data flow
- **Dependency Injection**: Clean separation of concerns with kotlin-inject
- **Test-Driven Development**: Comprehensive test coverage with kotlin-test and Mokkery

## 🛠️ Tech Stack

| Category | Technologies                             |
|----------|------------------------------------------|
| **Platforms** | Android, iOS, Desktop (JVM), Web (WASM)  |
| **UI Framework** | Jetpack Compose Multiplatform            |
| **Language** | Kotlin                                   |
| **Architecture** | MVVM, Clean Architecture                 |
| **Dependency Injection** | kotlin-inject with KSP                   |
| **State Management** | StateFlow, Coroutines, SavedStateHandle  |
| **Navigation** | Compose Navigation                       |
| **Serialization** | kotlinx-serialization                    |
| **Testing** | kotlin-test, Mokkery, Compose UI Testing |
| **Code Quality** | Detekt, Lint                             |

## 🚀 Getting Started

### Prerequisites

- **JDK 11** or higher
- **Android Studio Hedgehog** (2023.1.1) or higher
- **Xcode 15** or higher (for iOS development)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/nasrabadiAM/tictactoe.git
   cd tictactoe
   ```

2. **Run quality checks**
   ```bash
   ./check.sh
   ```

3. **Run on your preferred platform**

   **Android:**
   ```bash
   ./gradlew :androidApp:installDebug
   ```

   **Desktop:**
   ```bash
   ./gradlew :shared:run
   ```

   **Web:**
   ```bash
   ./gradlew :shared:wasmJsBrowserDevelopmentRun
   ```

   **iOS:** Open `iosApp/iosApp.xcodeproj` in Xcode and run

## 🧪 Development

### Running Tests

```bash
# Run all checks (tests, lint, static analysis)
./check.sh

# Run specific test suites
./gradlew test                           # Unit tests
./gradlew connectedDebugAndroidTest      # Android UI tests
./gradlew detektAll                      # Static analysis
./gradlew lint                           # Android lint
```

### Building for Production

```bash
# Android APK
./gradlew :androidApp:assembleRelease

# Desktop distribution
./gradlew :shared:packageReleaseDistributionForCurrentOS

# Web build
./gradlew :shared:wasmJsBrowserDistribution
```

## 📱 Platform Support

| Platform | Status | Features |
|----------|--------|----------|
| **Android** | ✅ Released | Full feature set, Material 3, Dynamic theming |
| **Web** | ✅ Demo | Full gameplay, Responsive design |
| **Desktop** | 🚧 In Development | Cross-platform (Windows, macOS, Linux) |
| **iOS** | 🚧 In Development | Native iOS experience |

## 🗺️ Roadmap

Check out our [project backlog and roadmap](https://github.com/users/nasrabadiAM/projects/2/views/1) to see upcoming features and improvements:

- 🤖 AI opponent with difficulty levels
- 🌐 Online multiplayer functionality
- 🎯 Achievement system
- 📊 Advanced statistics and analytics
- 🎵 Sound effects and haptic feedback
- 🌍 Internationalization support

## 🤝 Contributing

We welcome contributions from the community! Whether it's:

- 🐛 Bug reports and fixes
- ✨ New feature implementations
- 📚 Documentation improvements
- 🧪 Test coverage enhancements
- 🎨 UI/UX improvements

Please read our [contribution guidelines](CONTRIBUTING.md) and feel free to submit pull requests or open issues.

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes and run tests: `./check.sh`
4. Commit your changes: `git commit -m 'Add amazing feature'`
5. Push to the branch: `git push origin feature/amazing-feature`
6. Open a Pull Request

## 📄 License

This project is licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with ❤️ using [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- UI powered by [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- Inspired by the timeless appeal of Tic Tac Toe

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

[🌐 Web Demo](https://nasrabadiam.github.io/tictactoe/) • [📊 Roadmap](https://github.com/users/nasrabadiAM/projects/2/views/1)

<a href='https://cafebazaar.ir/app/me.nasrabadiam.tictactoe'><img alt='Download from CafeBazaar' src='https://s.cafebazaar.ir/2/images/get-cafebazaar-en.png' width="200px"/></a>

</div>