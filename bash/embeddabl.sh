#!/bin/bash

task=$1
uniid=$2

repopath=$3 #"D:/Users/mammu/workspace/loputoo/repod/"
zippath=$4 #"D:/Users/mammu/workspace/loputoo/embeddabl/"

cd $repopath$uniid/$task/src/
mkdir -p $zippath$task/ #../../embeddabl/$task/'

mkdir -p $task

for d in "$repopath$uniid/$task/src"/* ; do
	if [[ "$d" != "$repopath$uniid/$task/src/$task" ]]; then
		cp -r $d $task
	fi
done

echo "$repopath$uniid/$task/src/$task"
