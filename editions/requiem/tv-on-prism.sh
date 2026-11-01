#!/bin/bash
# Requiem for the TV: the full Prism TV app (whiteak/prism tv/apply.sh already applied to <checkout>), named Requiem,
# with Requiem's pictures and black-and-silver look. It keeps Requiem's app ID (com.requiem.tv) and signing key and
# updates from Requiem-TV-*.apk on this repo's releases, so the Requiem already on a TV updates to it in the app.
# Like Prism TV, it can copy the phone's set-up over the home Wi-Fi (Requiem on the iPhone or Android phone).
# usage: editions/requiem/tv-on-prism.sh <path to the Prism-applied NuvioTV checkout>
set -euo pipefail
src="$(cd "$(dirname "$0")" && pwd)"
cd "$1"
grep -q 'PrismTvOpening()' app/src/main/java/com/nuvio/tv/ui/components/StartupSplashScreen.kt || { echo "Apply Prism (tv) first." >&2; exit 1; }

# Requiem's pictures win over Prism's (icons, banner, wordmark, opening picture).
cp -R "$src/overlay/." .

grep -rlZ 'name="app_name"' app/src --include='*.xml' | xargs -0 sed -i 's#<string name="app_name">[^<]*</string>#<string name="app_name">Requiem</string>#'
sed -i 's#<color name="splash_background">[^<]*</color>#<color name="splash_background">\#0B0B0C</color>#' app/src/main/res/values/colors.xml
sed -i 's#[A-Za-z ]* · built on Nuvio#Requiem · built on Nuvio#' app/src/main/res/values/strings.xml
# "Prism" in the TV's words becomes "Requiem" (only in text, never in code names); its Download folder too.
find app/src -name 'Prism*.kt' -print0 | while IFS= read -r -d '' f; do
  perl -pi -e 's/(?<=["\s(])Prism(?=[\s"\x27,)?!:;])/Requiem/g if /"/' "$f"
  sed -i 's#DIRECTORY_DOWNLOADS + "/Prism"#DIRECTORY_DOWNLOADS + "/Requiem"#' "$f"
done

# Same app ID as the Requiem already installed, so it updates in place.
sed -i 's#"com.prism.tv"#"com.requiem.tv"#' app/build.gradle.kts
grep -q '"com.requiem.tv"' app/build.gradle.kts
! grep -q '"com.prism.tv"' app/build.gradle.kts

# In-app updates: Requiem's TV APK on this repo's releases (Prism's own come from whiteak/prism).
# The release's version is read from the APK's name (Requiem-TV-<version>.apk).
sed -i 's#RELEASE_ASSET_PREFIX = "Prism-TV-"#RELEASE_ASSET_PREFIX = "Requiem-TV-"#' app/src/full/java/com/nuvio/tv/updater/AbiSelector.kt
grep -q '"Requiem-TV-"' app/src/full/java/com/nuvio/tv/updater/AbiSelector.kt
sed -i 's#"GITHUB_REPO", "\\"prism\\""#"GITHUB_REPO", "\\"NuvioTV\\""#' app/build.gradle.kts
grep -q '"GITHUB_REPO", "\\"NuvioTV\\""' app/build.gradle.kts

# Black and silver by default (a theme picked in Settings still wins); no Ethereal-only MN avatar.
sed -i "s#val EDITION_DEFAULT_THEME = AppTheme\.[A-Z_]*#val EDITION_DEFAULT_THEME = AppTheme.WHITE#" app/src/main/java/com/nuvio/tv/domain/model/AppTheme.kt
grep -q "EDITION_DEFAULT_THEME = AppTheme.WHITE" app/src/main/java/com/nuvio/tv/domain/model/AppTheme.kt
sed -i 's#const val EDITION_AVATARS_ENABLED = true#const val EDITION_AVATARS_ENABLED = false#' app/src/main/java/com/nuvio/tv/data/remote/supabase/EditionAvatars.kt
grep -q 'EDITION_AVATARS_ENABLED = false' app/src/main/java/com/nuvio/tv/data/remote/supabase/EditionAvatars.kt
echo "Requiem (TV, on Prism) applied."
