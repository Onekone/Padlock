#!/bin/bash

# Include config file
source ./CONFIG.sh

rm -rf "$OUT"
mkdir -p "$OUT_CLS"

if [ "$(uname)" == "Darwin" ]; then
    echo Mac OS X platform
    CP="$LIB"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    echo GNU/Linux platform
    CP="$LIB"
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
    echo Windows NT platform
    CP="$LIB"
fi

find . -name "*.java" | xargs javac -cp "$CP" -d $OUT_CLS -sourcepath $SRC -verbose

cp -r $RES $OUT_CLS

mkdir -p $OUT_LIB
cp $LIB $OUT_LIB

jar -cfe $OUT_JAR $MAIN -C $OUT_CLS .