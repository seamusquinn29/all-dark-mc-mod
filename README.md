# All Dark Mod

A Minecraft Forge mod for version 1.21.7 that transforms the entire world into a Deep Dark biome with enhanced gameplay mechanics.

## Features

### 🌍 World Generation
- **Complete Deep Dark World**: The entire world is converted to Deep Dark biome from bedrock to Y=320
- **Bedrock Ceiling**: A 5-block thick bedrock ceiling at Y=320 blocks access to the surface
- **Normal Cave Generation**: Maintains the natural Deep Dark cave system throughout the world
- **Increased Ancient Cities**: Ancient Cities spawn more frequently than in vanilla

### 👹 Enhanced Mob Spawning
- **Hostile Mob Variety**: All hostile mobs can spawn in Deep Dark biomes, including:
  - Zombies, Skeletons, Spiders, Creepers
  - Endermen, Witches, Slimes, Silverfish
  - Wither Skeletons, Blazes, Ghasts, Magma Cubes
  - Hoglins, Piglin variants, Illagers
  - And many more!
- **Increased Spawn Rates**: Hostile mobs spawn 50% more frequently in Deep Dark
- **Warden Preservation**: The Warden still spawns naturally in Ancient Cities

### 🍖 Hunger Mechanics
- **Constant Hunger Drain**: Hunger decreases even when standing completely still
- **Exploration Incentive**: Forces players to actively search for food sources
- **Deep Dark Only**: Hunger drain only occurs while in Deep Dark biomes

## Installation

### Prerequisites
- Java 21 (JDK)
- Minecraft 1.21.7
- Forge 50.0.0 or later

### Building the Mod

#### Option 1: Using GitHub Actions (Recommended)
Since this mod has compatibility issues with Apple Silicon Macs, the easiest way to build it is using GitHub Actions:

1. **Push this code to a GitHub repository**
2. **Go to the Actions tab** in your GitHub repository
3. **Run the "Build All Dark Mod (Manual)" workflow**
   - Click "Run workflow"
   - Choose "release" build type
   - Click "Run workflow"
4. **Download the built JAR**
   - Once the workflow completes, go to the workflow run
   - Download the "all-dark-mod-X" artifact
   - Extract the JAR file from the downloaded zip

#### Option 2: Local Build (Windows/Linux)
If you're on Windows or Linux (not Apple Silicon Mac):

1. **Clone or download this repository**
   ```bash
   git clone <repository-url>
   cd all-dark-mc-mod
   ```

2. **Build the mod**
   ```bash
   # On Linux/macOS:
   ./gradlew build
   
   # On Windows:
   gradlew.bat build
   ```

3. **Find the mod JAR**
   - The built mod will be in `build/libs/alldarkmod-1.0.0.jar`

#### Option 3: Create a Release
To create an official release with automatic builds:

1. **Create and push a tag**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. **GitHub Actions will automatically build and create a release**
   - The JAR file will be available in the Releases section
   - Users can download it directly from GitHub

### Installing the Mod

1. **Install Forge for Minecraft 1.21.7**
   - Download Forge 50.0.0+ from [files.minecraftforge.net](https://files.minecraftforge.net/)
   - Run the installer and create a new profile

2. **Install the mod**
   - Copy `alldarkmod-1.0.0.jar` to your Minecraft `mods` folder
   - The mods folder is typically located at:
     - **Windows**: `%APPDATA%\.minecraft\mods\`
     - **macOS**: `~/Library/Application Support/minecraft/mods/`
     - **Linux**: `~/.minecraft/mods/`

3. **Launch Minecraft**
   - Select the Forge profile in the launcher
   - Start a new world or load an existing one

## Gameplay Tips

### 🏠 Starting Out
- **Spawn Location**: You'll spawn below the bedrock ceiling at Y=320
- **Immediate Threats**: Hostile mobs can spawn anywhere, so be prepared
- **Food Priority**: Find food sources quickly as hunger drains constantly

### 🍖 Food Sources
- **Ancient Cities**: Contain valuable loot and food items
- **Mob Drops**: Hostile mobs may drop food items
- **Cave Exploration**: Search for mushrooms and other cave food sources

### ⚔️ Combat Strategy
- **Light Management**: Use torches and other light sources strategically
- **Sound Awareness**: Listen for mob sounds in the darkness
- **Warden Encounters**: Ancient Cities still contain Wardens - be extremely careful

### 🏗️ Building
- **Underground Bases**: Build your base in the Deep Dark cave system
- **Lighting**: Ensure your base is well-lit to prevent mob spawning
- **Resource Gathering**: Mine for resources while exploring the cave system

## Technical Details

### Mod Structure
```
src/main/java/com/alldark/alldarkmod/
├── AllDarkMod.java              # Main mod class
├── worldgen/
│   ├── DeepDarkWorldGen.java    # Biome replacement system
│   ├── BedrockCeilingGenerator.java # Bedrock ceiling generation
│   └── AncientCityGenerator.java # Ancient City spawn rates
├── mobspawning/
│   └── HostileMobSpawning.java  # Hostile mob spawning logic
└── hunger/
    └── HungerManager.java       # Hunger drain mechanics
```

### Configuration
The mod currently uses hardcoded values for:
- Hunger drain rate: Every 30 seconds (600 ticks)
- Hunger amount: 1 food level per drain
- Bedrock ceiling: Y=320 with 5-block thickness
- Mob spawn rate increase: 50% more frequent

## Troubleshooting

### Common Issues

**Mod not loading:**
- Ensure you're using Forge 50.0.0+ for Minecraft 1.21.7
- Check that the mod JAR is in the correct mods folder
- Verify Java 21 is installed and being used

**Performance issues:**
- The mod may cause some performance impact due to constant biome checking
- Consider reducing render distance if experiencing lag

**World generation issues:**
- The mod works best with new worlds
- Existing worlds may not fully convert to Deep Dark biomes

### Reporting Issues
If you encounter any issues, please report them with:
- Minecraft version
- Forge version
- Mod version
- Steps to reproduce the issue
- Any error messages or logs

## License

This mod is licensed under the MIT License. See the LICENSE file for details.

## Credits

- **Developer**: AllDarkModsAreUs
- **Minecraft**: Mojang Studios
- **Forge**: MinecraftForge Team

---

**Enjoy your journey in the eternal darkness!** 🌑 