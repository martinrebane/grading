#!/bin/bash

projectpath=$1
hashpath=$2 #"D:/Users/mammu/workspace/loputoo/hashes/"

cd $projectpath

folders=$(echo $projectpath | tr "/" "\n")
value="$(find $projectpath -type f -print0 | xargs -0 sha1sum)"
taskfolder=""
uniid=""

for folder in $folders
do
	if [ "$folder" != "src" ]
	then
		taskfolder="$folder"
	fi
done

for folder in $folders
do
	if [ "$folder" == "$taskfolder" ]
	then
		break
	else
		uniid="$folder"
	fi
done

target="$hashpath$uniid"

mkdir -p $target
touch $target/$taskfolder.txt
echo $value > $target/$taskfolder.txt
echo $value