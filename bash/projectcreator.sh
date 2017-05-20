#!/bin/bash

task=$1
uniid=$2

repopath=$3 #"D:/Users/mammu/workspace/loputoo/repod/"
zippath=$4 #"D:/Users/mammu/workspace/loputoo/zips/"
templatepath=$5 #"D:/Users/mammu/workspace/loputoo/templates/Template"

cd $repopath$uniid/$task/src/
mkdir -p $zippath$task/ #../../zips/$task/

mkdir -p $task

cp -r "$templatepath/.settings" $task
cp -r "$templatepath/.classpath" $task
cp -r "$templatepath/.project" $task
cp -r $templatepath/* $task

for f in */ ; do
	if [[ "$f" != "$task/" ]]
	then
		cp -r $f "$task/src/"
	fi
done

echo "$repopath$uniid/$task/src/$task"