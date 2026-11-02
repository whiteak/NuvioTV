# NuvioTV fork (Ethereal / Requiem TV) — CLAUDE.md

whiteak's fork of NuvioMedia/NuvioTV: an Android TV app (Kotlin, Compose for TV, single `app` module). It is the
**Ethereal TV** app plus the **Requiem** TV edition, and the base that whiteak/Prism (`tv/apply.sh`) and
whiteak/Supernatural build their TV apps on.

Default/working branch: **`dev`** (Ethereal releases, Prism TV, Requiem TV and Supernatural TV all build from it).
Upstream: NuvioMedia/NuvioTV `dev`, merged in weekly. Other `claude/project-thread-*` and `personal/baseline`
branches are leftovers from old sessions. Ignore them: don't build from them, merge them or delete them.

**Jason builds only through GitHub Actions. Don't run Gradle or set up a local build.**

## Layout (only the parts you're likely to touch)

```
app/src/main/java/com/nuvio/tv/
  ui/components/        shared UI (StartupSplashScreen.kt, ...)
  ui/screens/<area>/    screens (settings/, home/, ...)
  data/remote/, data/repository/, core/di/   network, repos, Hilt modules
app/src/full/java/com/nuvio/tv/updater/      in-app updater (AbiSelector, UpdateRepository, ApkDownloader)
app/src/main/res/       strings, colours, drawables, mipmaps, TV banner
app/build.gradle.kts    versionName / versionCode, applicationId, GITHUB_REPO
editions/requiem/       apply.sh, mkoverlay.sh, tv-on-prism.sh, masters/, overlay/ (GENERATED images)
editions/ethereal/      masters/
.github/workflows/      release.yml, requiem-tv.yml, pr-full-debug-build*.yml, android-release.yml
DV7/, libmpv-android/, ffmpeg-decoder-downmix/, org/, baselineprofile/   native/third-party; don't edit
```

Fork-specific code uses `Edition*` names (e.g. `data/remote/supabase/EditionAvatars.kt`). Keep new fork code in
`Edition*` files so upstream merges stay clean.

## Build & release

- `release.yml` runs weekly (Sun 18:00 UTC), on dispatch, or **on any push to `dev`**. It merges upstream, builds
  Ethereal, then runs `editions/requiem/apply.sh` and builds Requiem, and publishes a release. If the sync hits
  conflicts it opens an issue instead.
- **A push to `dev` publishes a release.** Add `[skip ci]` to commits that shouldn't.
- `requiem-tv.yml` (manual) builds **Requiem TV on top of Prism**: it checks out whiteak/prism (`prism_ref` input),
  runs `tv/apply.sh`, then `editions/requiem/tv-on-prism.sh`, and publishes `Requiem-TV-<version>.apk`
  (app ID `com.requiem.tv`).
- Signing alias `ethereal`. Secrets: `SIGNING_KEYSTORE`, `SIGNING_KEY_PASSWORD`, Trakt keys.
  App IDs: releases ship the full **debug** variant, so Ethereal TV installs as `com.nuviodebug.com`. The editions
  sed-replace that exact string (Requiem `com.requiem.tv`, Prism `com.prism.tv`, Supernatural `com.supernatural.tv`).

## Rules

- **Downstream patch scripts anchor on exact text here.** Prism's `tv/apply.sh` and Supernatural's `tv/apply.sh`
  edit `StartupSplashScreen.kt`, `NetworkModule.kt`, `GitHubReleaseDto.kt`, `UpdateRepository.kt`,
  `ApkDownloader.kt`, `AbiSelector.kt` (`RELEASE_ASSET_PREFIX`), `app/build.gradle.kts` (`versionName`,
  `versionCode`, `"com.nuviodebug.com"`, `GITHUB_REPO`) and more. Before restructuring those files, grep both
  `apply.sh` scripts and update their anchors to match.
- `tv-on-prism.sh` depends on Prism's output (`PrismTvOpening()`, `Prism*.kt` file names). If Prism changes those,
  update this script too.
- Never hand-edit `editions/*/overlay/` images. Regenerate them with `editions/requiem/mkoverlay.sh`.
- Keep behaviour consistent with the phone fork (whiteak/NuvioMobile). Say so when a feature can't match on TV.
- Leave upstream CI/bots alone. LF line endings only. GPLv3, keep the "built on Nuvio" credit.

## Working efficiently

- Don't explore the native folders listed above, `.idea/`, `.kotlin/` or `assets/` images.
- Go straight to `ui/screens/<area>/` or `ui/components/`. The `Edition*` grep finds the fork's own code.
- To check that an edition script still applies, run it on a fresh `git clone --depth 1` (no Gradle needed).
