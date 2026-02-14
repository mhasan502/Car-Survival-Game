# Car Survival Game

Car Survival Game is a small Java Swing desktop game where you dodge incoming cars and survive as long as possible.

## Features

- Menu screen with Play and Exit actions
- Two-lane survival gameplay
- Score that increases over time
- Game Over screen with Play Again
- Sprite and background audio support from `src/main/resources`

## Tech Stack

- Java 21
- Swing (UI)
- Gradle (`application` plugin)

## Controls

- `Up Arrow`: move up
- `Down Arrow`: move down
- `Left Arrow`: move left
- `Right Arrow`: move right

## Project Structure

- `src/main/java/com/carsurvival`: game source code
- `src/main/resources/sprites`: car sprites
- `src/main/resources/music`: audio file

## Run (Gradle)

Prerequisites:

- Java 21+
- Gradle installed

Run:

```bash
gradle run
```

## Build (Gradle)

```bash
gradle clean build
```

## Compile/Run Without Gradle

Linux/macOS:

```bash
mkdir -p out
javac --release 21 -d out $(find src/main/java -name "*.java")
java -cp out:src/main/resources com.carsurvival.Main
```

Windows (PowerShell):

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac --release 21 -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName })
java -cp "out;src/main/resources" com.carsurvival.Main
```

## IntelliJ IDEA Setup

1. Open the project root as a Gradle project.
2. Set Project SDK to JDK 21 (`File > Project Structure > Project`).
3. Ensure language level is 21.
4. Create an Application run configuration:
   - Main class: `com.carsurvival.Main`
   - Use classpath of module: project main module
   - Working directory: project root
