#!/bin/bash

#tulevikus veel aasta ja subject

task=$1
uniid=$2

repopath=$3 #"D:/Users/mammu/workspace/loputoo/repod"
zippath=$4 #"D:/Users/mammu/workspace/loputoo/projects"
templatepath=$5 #"D:/Users/mammu/workspace/loputoo/templates/Template"

cd $repopath$uniid/$task/src/
mkdir -p $zippath$task/ #../../zips/$task/

mkdir -p $task

#mvn archetype:generate -DgroupId=$package -DartifactId=$task -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

#rm "$task/src/main/java/$packagepath/App.java"

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

#jar -cMf $zippath$task/$uniid.zip $task

#chmod +x $zippath$task/$uniid.zip

#rm -r $task

#echo "$zippath$task/$uniid.zip"