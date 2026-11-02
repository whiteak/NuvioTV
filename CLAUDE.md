# NuvioTV fork (Ethereal TV) — CLAUDE.md

whiteak's fork of NuvioMedia/NuvioTV: an Android TV app (Kotlin, Compose for TV, single `app` module). The default
branch `dev` is the **Ethereal TV** app. Other apps' TV builds (Prism, Requiem, Supernatural) use their **own fixed
base branches** in this repo, not `dev`.

**Jason's rule: every app is its own.** Change only the app he names, on that app's branch. Never carry a change
into another app's branch or base unless he asks for that.
**Jason builds only through GitHub Actions. Don't run Gradle or set up a local build.**

| Branch               | App                                                       |
|----------------------|-----------------------------------------------------------|
| `dev` (default)      | **Ethereal TV**                                           |
| `base/prism`         | Prism TV (built by whiteak/Prism)                         |
| `base/requiem`       | Requiem TV (built by whiteak/Prism "Build Requiem")       |
| `base/supernatural`  | Supernatural TV (built by whiteak/Supernatural)           |

- The `base/*` branches were pinned on 2 Nov 2026 to what each app was last built from. They move only when Jason
  asks, for that app only.
- Upstream NuvioMedia/NuvioTV `dev` is merged into `dev` only when Jason runs the Ethereal workflow.
- Other `claude/project-thread-*` and `personal/baseline` branches are leftovers from old sessions. Ignore them.

## Layout (only the parts you're likely to touch)

```
app/src/main/java/com/nuvio/tv/
  ui/components/        shared UI (StartupSplashScreen.kt, ...)
  ui/screens/<area>/    screens (settings/, home/, ...)
  data/remote/, data/repository/, core/di/   network, repos, Hilt modules
app/src/full/java/com/nuvio/tv/updater/      in-app updater (AbiSelector, UpdateRepository, ApkDownloader)
app/src/main/res/       strings, colours, drawables, mipmaps, TV banner
app/build.gradle.kts    versionName / versionCode, applicationId, GITHUB_OWNER / GITHUB_REPO
editions/ethereal/      masters/
.github/workflows/      release.yml, pr-full-debug-build*.yml, android-release.yml
DV7/, libmpv-android/, ffmpeg-decoder-downmix/, org/, baselineprofile/   native/third-party; don't edit
```

Ethereal's own code uses `Edition*` names (e.g. `data/remote/supabase/EditionAvatars.kt`). Keep new Ethereal code in
`Edition*` files so upstream merges stay clean. Requiem's TV files now live on whiteak/Prism's `requiem` branch.

## Build & release (Ethereal TV)

- `release.yml` ("Sync and release Ethereal") is **manual only**. There's no schedule and no push trigger. It
  optionally merges upstream (`sync` input), builds Ethereal TV and publishes `v<version>` with `Ethereal-TV-*.apk`.
  If the sync hits conflicts it opens an issue instead.
- Pushes no longer publish anything, but still add `[skip ci]` to doc-only commits.
- Signing alias `ethereal`. Secrets: `SIGNING_KEYSTORE`, `SIGNING_KEY_PASSWORD`, Trakt keys.
- App IDs: releases ship the full **debug** variant, so Ethereal TV installs as `com.nuviodebug.com`. The other apps
  sed-replace that exact string (Prism `com.prism.tv`, Requiem `com.requiem.tv`, Supernatural `com.supernatural.tv`).
- Releases tagged `requiem-moved-*` are the one-time update that moved Requiem TVs to whiteak/Requiem. Leave them.

## Rules

- Ethereal changes go on `dev` and reach no other app. Another app's TV change that needs fork code goes on that
  app's `base/*` only, when Jason asks.
- The other apps' TV scripts (Prism `tv/apply.sh`, Requiem `requiem/tv/tv-on-prism.sh`, Supernatural `tv/apply.sh`)
  anchor on exact text in their base. When you move a base forward at Jason's request, re-run that app's scripts on a
  fresh clone of the base and fix the anchors in that app's repo.
- Keep Ethereal TV consistent with Ethereal on the phone (whiteak/NuvioMobile `cmp-rewrite`). Say so when it can't match.
- Leave upstream CI/bots alone. LF line endings only. GPLv3, keep the "built on Nuvio" credit.

## Working efficiently

- Don't explore the native folders listed above, `.idea/`, `.kotlin/` or `assets/` images.
- Go straight to `ui/screens/<area>/` or `ui/components/`. The `Edition*` grep finds Ethereal's own code.
