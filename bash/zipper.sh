#!/bin/bash

uniid=$1
folder=$2

source=$3 #"D:/Users/mammu/workspace/loputoo/repod/maria.kert/EX05/src"
dest=$4 #"D:/Users/mammu/workspace/loputoo/zips/EX05"

cd $source

jar -cMf $dest/$uniid.zip $folder

chmod +x $dest/$uniid.zip

rm -r $folder

echo "$dest/$uniid.zip"