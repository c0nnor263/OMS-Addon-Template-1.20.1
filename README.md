# OMS Addon Template

A minimal template for creating **[Operate My Server (OMS)](https://github.com/c0nnor263/OperateMyServer)** addons.  
Built with **Minecraft Forge**, **Kotlin for Forge**, and **ModDevGradle Legacy**.

---

## Usage

Create a new repository from this template by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Then clone the repository and open it in your IDE. [IntelliJ IDEA](https://www.jetbrains.com/idea/) is recommended for Kotlin development.

After creating your project, you will usually want to update:

- mod metadata in `buildSrc/src/main/kotlin/ModInfo.kt`
- package name `com.example.examplemod`
- mod id in `examplemod/src/main/kotlin/com/example/examplemod/ExampleMod.kt`
- example addon/feature classes under `examplemod/src/main/kotlin/com/example/examplemod/`

---

## Project Setup

The template includes:

- Forge dev setup
- Kotlin support
- OMS API dependency
- OMS runtime dependency for development
- basic OMS addon registration
- example feature structure
- client and server run configurations
- test setup with Kotest and MockK

---

## Development Runs
Available run configurations:

```bash
./gradlew runServer
./gradlew runClient
./gradlew test
```
Or use the IDE run configurations:
- `examplemod - Server`
- `examplemod - Client`

Use:
- `runServer` for server-side addon development
- `runClient` only if your addon has client-side behavior

---

## About OMS
**Operate My Server (OMS)** is a modular platform for Minecraft server control, automation, and lifecycle management.
OMS is built around two core concepts:
- **Addon** - a standalone mod that extends OMS
- **Feature** - a unit of behavior inside an addon.
An addon may contain one or more features. Each addon can be installed, removed, updated, and maintained independently.
This template provides a minimal foundation for building OMS-compatible addons without unrelated Forge example content.

--- 

## Notes
This repository is intentionally minimal.

It is not a generic Forge example mod.
It is a starting point for OMS-compatible addons.
The included `helloworld` feature is only an example.
You can rename it, adapt it, or remove it completely.