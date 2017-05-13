#!/bin/bash

#tulevikus veel aasta ja subject

task=$1
uniid=$2

repopath=$3 #"D:/Users/mammu/workspace/loputoo/repod"
zippath=$4 #"D:/Users/mammu/workspace/loputoo/zips" 

cd $repopath$uniid/$task/src/
mkdir -p $zippath$task/ #../../zips/$task/'

zipfolder=""

for d in */ ; do
	zipfolder=$d
done

mkdir -p $task

if [ $zipfolder == */ ]
then
	for f in $repopath$uniid/$task/src/* ; do
		if [[ -f $f ]]
		then
			cp $f $repopath$uniid/$task/src/$task
		fi
	done
else
	cp -r $zipfolder $task
fi

jar -cMf $zippath$task/$uniid.zip $task

rm -r $task

echo "$zippath$task/$uniid.zip"