# Requiem edition

A black and silver Angelcore edition of Ethereal with its own name, artwork and app ID
(so it installs alongside Ethereal). The release workflow builds it after Ethereal by running
`apply.sh`, then publishes it as `Requiem-*.apk` on the same release.

- `overlay/`: resource files that replace Ethereal's, generated from `masters/` by `mkoverlay.sh <repo>`.
- `apply.sh`: copies the overlay and switches the app name, app ID and updater asset name.
