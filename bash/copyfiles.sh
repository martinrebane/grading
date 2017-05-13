#!/bin/bash

folderpath=$1
destpath=$2

echo $folderpath
echo $destpath

mkdir -p $destpath
cd $folderpath

for file in "$folderpath*" ; do
	echo $file
	cp $file $destpath
done
