# Trakky Salon — Android (Jetpack Compose)

Salon & barber booking app inspired by trakky.in. Focused 100% on hair & salon services.

## Open in Android Studio
1. Unzip the archive.
2. Android Studio → Open → select the unzipped folder.
3. Let Gradle sync (AGP 8.5.2, Kotlin 2.0, JDK 17). Run on emulator/device (minSdk 24).

## Highlights
- **Animated scissor loader** (Canvas) — opening/closing blades with a falling hair strand; used in splash & full-screen loader.
- **Aurora gradient background** — animated burgundy/onyx/gold ambience on every screen.
- **3D salon cards** — gentle Y-axis tilt via `graphicsLayer` + gold glow shadow.
- **Radar map on Nearby** — sweeping beam with salon dots sorted by distance from user.
- **Glassy floating bottom nav** with animated active pill.
- **Screens**: Splash → Home → Nearby (radar) → Bookings → Profile → Salon Detail (slot picker + confirm).

## Structure
```
app/src/main/java/com/trakky/app/
 ├ MainActivity.kt
 ├ data/Models.kt          # Salon, Service, SampleData
 ├ nav/TrakkyApp.kt        # NavHost + glass bottom bar + transitions
 └ ui/
    ├ theme/Theme.kt       # Gold / RoseGold / Burgundy / Onyx palette
    ├ components/
    │   ├ ScissorLoader.kt # animated barber-scissor loader
    │   └ Common.kt        # SalonCard, ServicePill, AnimatedAuroraBackground
    └ screens/             # Splash, Home, Nearby, Bookings, Profile, SalonDetail
```

Images load from Unsplash via Coil — internet permission already in manifest.
