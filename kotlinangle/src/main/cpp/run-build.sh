#!/bin/sh
echo "[clean]"
rm -rf build
mkdir build
cd build

echo "[build library]"
cmake ..
make

echo
cp libPrismAngleLibrary.dylib ../../resources
echo "libPrismAngleLibrary.dylib was copied to resources folder"
echo "[done]"