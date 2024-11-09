# KDateTimeX

[![Maven Central](https://img.shields.io/maven-central/v/dev.sunnat629/kDateTimeX)](https://central.sonatype.com/namespace/dev.sunnat629)
[![License](https://img.shields.io/github/license/sunnat629/KDateTimeUtils)](https://opensource.org/licenses/MIT)
[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-blue)](https://kotlinlang.org/docs/multiplatform.html)

A **Kotlin Multiplatform library** for date and time utilities, designed for modern applications. The library supports multiple platforms including **Android**, **JVM**, **iOS**, **JavaScript**, and **WebAssembly**.

## Features

- Unified date and time utilities across all platforms.
- Easy-to-use API with idiomatic Kotlin practices.
- Built-in support for **localization** and **time zones**.
- Compatible with **Kotlin 2.0** and above.

## Getting Started

### Gradle Setup

Add the following dependencies to your project.

```kotlin
// Root build.gradle.kts
repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.sunnat629.libs:kDateTimeX:1.0.0")
}
```

### Supported Platforms

- **Android**: `kDateTimeX-android`
- **JVM**: `kDateTimeX-jvm`
- **JavaScript**: `kDateTimeX-js`
- **iOS**: `kDateTimeX-ios`
- **WebAssembly (WASM)**: `kDateTimeX-wasm-js`

### Usage

#### Basic Date and Time Conversion

```kotlin
import dev.sunnat629.libs.kdatetimex.*

val epochSeconds = 1634070400L
val formattedDate = epochSeconds.toLocalDateTime().toHumanReadableString()
println("Formatted Date: $formattedDate")
```

#### Time Calculation

```kotlin
val isLeapYear = isLeapYear(2024)
println("Is 2024 a leap year? $isLeapYear")

val currentInstant = getNowInInstant()
println("Current Time: $currentInstant")
```

#### Date Formatting

```kotlin
val timestamp = 1672531199L
val humanReadableTime = formatMillisecondsToHumanTime(timestamp * 1000)
println("Human Readable Time: $humanReadableTime")
```

## Documentation

Detailed documentation and examples are available in the [Wiki](WIKI.md).

## Changelog

See the [Changelog](https://github.com/sunnat629/KDateTimeUtils/releases) for recent changes and updates.

## Contributing

We welcome contributions! Please read our [Contributing Guide](CONTRIBUTING.md) for guidelines.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or support, reach out to [Mohi Us Sunnat](mailto:suncha629@gmail.com).
