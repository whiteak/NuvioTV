#!/bin/bash
# Turns this checkout into the Requiem edition (run in CI before building it).
set -euo pipefail
cd "$(dirname "$0")/../.."
cp -R editions/requiem/overlay/. .
grep -rlZ 'name="app_name"' app/src --include='*.xml' | xargs -0 sed -i 's#<string name="app_name">[^<]*</string>#<string name="app_name">Requiem</string>#'
sed -i 's#<color name="splash_background">[^<]*</color>#<color name="splash_background">\#0B0B0C</color>#' app/src/main/res/values/colors.xml
sed -i 's#[A-Za-z ]* · built on Nuvio#Requiem · built on Nuvio#' app/src/main/res/values/strings.xml
sed -i 's#"com.nuviodebug.com"#"com.requiem.tv"#' app/build.gradle.kts
sed -i "s#val EDITION_DEFAULT_THEME = AppTheme.VIOLET#val EDITION_DEFAULT_THEME = AppTheme.WHITE#" app/src/main/java/com/nuvio/tv/domain/model/AppTheme.kt
sed -i 's#RELEASE_ASSET_PREFIX = "Ethereal-"#RELEASE_ASSET_PREFIX = "Requiem-"#' app/src/full/java/com/nuvio/tv/updater/AbiSelector.kt
grep -q 'com.requiem.tv' app/build.gradle.kts
grep -q "EDITION_DEFAULT_THEME = AppTheme.WHITE" app/src/main/java/com/nuvio/tv/domain/model/AppTheme.kt
grep -q '"Requiem-"' app/src/full/java/com/nuvio/tv/updater/AbiSelector.kt
echo "Requiem edition applied."
