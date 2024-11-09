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

### **Gradle Setup for All Platforms**

Here's a comprehensive guide on how to include `kDateTimeX` for each supported platform in your Kotlin Multiplatform project. The library is published under the namespace `dev.sunnat629` on Maven Central.

#### **Common Dependency**

To add the library for all platforms, include the following in your `commonMain` source set:

```kotlin
// Root build.gradle.kts
repositories {
    mavenCentral()
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("dev.sunnat629:kDateTimeX:$lib_version")
            }
        }
    }
}
```

### **Platform-Specific Dependencies**

If you want to include the library only for specific platforms, use the following configuration.

#### **1. Android**

```kotlin
androidMain {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-android:$lib_version")
    }
}
```

#### **2. JVM**

```kotlin
jvmMain {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-jvm:$lib_version")
    }
}
```

#### **3. JavaScript**

```kotlin
jsMain {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-js:$lib_version")
    }
}
```

#### **4. iOS (Arm64, X64, Simulator Arm64)**

```kotlin
iosArm64Main {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-ios:$lib_version")
    }
}

iosX64Main {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-ios:$lib_version")
    }
}

iosSimulatorArm64Main {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-ios:$lib_version")
    }
}
```

#### **5. WebAssembly (WASM)**

```kotlin
wasmMain {
    dependencies {
        implementation("dev.sunnat629:kDateTimeX-wasm-js:$lib_version")
    }
}
```

### **Supported Platforms**

| Platform   | Artifact ID               | Example Dependency                                 |
|------------|---------------------------|----------------------------------------------------|
| Common     | `kDateTimeX`              | `implementation("dev.sunnat629:kDateTimeX:$lib_version")` |
| Android    | `kDateTimeX-android`      | `implementation("dev.sunnat629:kDateTimeX-android:$lib_version")` |
| JVM        | `kDateTimeX-jvm`          | `implementation("dev.sunnat629:kDateTimeX-jvm:$lib_version")` |
| JavaScript | `kDateTimeX-js`           | `implementation("dev.sunnat629:kDateTimeX-js:$lib_version")` |
| iOS        | `kDateTimeX-ios`          | `implementation("dev.sunnat629:kDateTimeX-ios:$lib_version")` |
| WASM       | `kDateTimeX-wasm-js`      | `implementation("dev.sunnat629:kDateTimeX-wasm-js:$lib_version")` |

> **Note**: In the current version (`1.0.0`), there are no specific platform-dependent implementations. All the utility functions are part of the **Common** module, so using the common dependency (`kDateTimeX`) is sufficient for all platforms. Separate platform artifacts are provided for compatibility with Kotlin Multiplatform's dependency management, but they do not contain unique platform-specific code.



### **Additional Notes**

- Ensure you have `mavenCentral()` added to your repositories in the root `build.gradle.kts`.
- The common dependency `kDateTimeX` includes shared utilities for all platforms.
- For platform-specific features, use the corresponding artifact ID.

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
