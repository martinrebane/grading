#!/bin/bash

sourcepath=$1
destpath=$2

echo $sourcepath
echo $destpath

mkdir -p $destpath
cd $sourcepath

for file in "$sourcepath*" ; do
	echo $file
	cp $file $destpath
done
