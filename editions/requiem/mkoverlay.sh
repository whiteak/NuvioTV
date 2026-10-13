#!/bin/bash
# usage: mkoverlay.sh <repo> ; writes editions/requiem/overlay/<same paths> resized from masters here
set -e; M=$(cd "$(dirname "$0")" && pwd)/masters; R=$1; O=$R/editions/requiem/overlay
pick() { case "$1" in
  *monochrome*) echo mono;; *_foreground*|*foreground.*) echo fg;; *_round*) echo icon_round;;
  *splash_logo*) echo splash;; *wordmark*) echo wordmark;; *banner*) echo banner;;
  *) echo icon_full;; esac; }
cd "$R"
files=$(git show --name-only --format= $(git log --format=%h --grep="Rebrand" | head -1) | grep -E '\.(png|webp)$')
for t in $files; do
  m=$M/$(pick "$t").png; mkdir -p "$O/$(dirname "$t")"
  sz=$(identify -format '%wx%h' "$t"); ch=$(identify -format '%[channels]' "$t")
  if [[ "$ch" == *a* ]]; then convert "$m" -filter Lanczos -resize "${sz}!" -quality 92 "$O/$t"
  else convert "$m" -filter Lanczos -resize "${sz}!" -background '#F2F2F4' -flatten -alpha off -quality 92 "$O/$t"; fi
done
echo "$(echo "$files" | wc -l) files"; du -sh "$O"
