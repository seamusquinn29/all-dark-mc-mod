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

### Building the Mod & Creating a Release

This project uses [release-please](https://github.com/googleapis/release-please) to automate the release process.

#### How it Works
1.  **Commit Changes**: Make your changes and commit them to the `main` branch using the [Conventional Commits](https://www.conventionalcommits.org/) specification.
    - `feat: A new feature` will result in a `minor` version bump (e.g., `1.0.0` -> `1.1.0`).
    - `fix: A bug fix` will result in a `patch` version bump (e.g., `1.0.0` -> `1.0.1`).
    - `feat!: A breaking change` will result in a `major` version bump (e.g., `1.0.0` -> `2.0.0`).

2.  **`release-please` Creates a PR**: On every push to `main`, the `release-please` workflow runs. If it finds release-worthy commits, it will create a "Release PR" that contains an updated `CHANGELOG.md` and the new version number bumped in the project files.

3.  **Merge the PR**: Review and merge the "Release PR".

4.  **Release is Created**: Merging the PR triggers `release-please` to create a new GitHub Release with the correct version tag.

5.  **Build is Triggered**: The new GitHub Release triggers the `Release Build` workflow, which builds the mod.

6.  **JAR is Uploaded**: The workflow uploads the compiled `.jar` file as an asset to the GitHub Release, ready for you to download.

This provides a fully automated way to build and release your mod without needing a local build environment.

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