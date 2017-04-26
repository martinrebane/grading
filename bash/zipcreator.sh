#!/bin/bash

#tulevikus veel aasta ja subject

task=$1
uniid=$2

repopath="D:/Users/mammu/workspace/loputoo/repod"
zippath="D:/Users/mammu/workspace/loputoo/zips" 

cd $repopath/$uniid/$task/src/
mkdir -p ../../zips/$task/

zipfolder=""

for d in */ ; do
	zipfolder=$d
done

mkdir -p $task

cp -r $zipfolder $task

jar -cMf $zippath/$task/$uniid.zip $task

rm -r $task

echo $zippath/$task/$uniid.zip