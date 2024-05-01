#!/bin/sh

args=( "$@" )

if [[ " ${args[*]} " != *"--no-detekt"* ]]; then
  echo "Running detekt..."
  ./gradlew detektAll
fi

if [[ " ${args[*]} " != *"--no-lint"* ]]; then
  echo "Running lint..."
  ./gradlew lint
fi

if [[ " ${args[*]} " != *"--no-unit"* ]]; then
  echo "Running Unit Tests...."
  ./gradlew test
fi

if [[ " ${args[*]} " != *"--no-ui"* ]]; then
  echo "Running Ui Tests...."
  ./gradlew connectedDebugAndroidTest --daemon
fi