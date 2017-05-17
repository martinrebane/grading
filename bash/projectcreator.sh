#!/bin/bash

#tulevikus veel aasta ja subject

task=$1
uniid=$2

repopath=$3 #"D:/Users/mammu/workspace/loputoo/repod"
zippath=$4 #"D:/Users/mammu/workspace/loputoo/projects"

package=$5 #ee.ttu.java.albumcreation

cd $repopath$uniid/$task/src/
mkdir -p $zippath$task/ #../../zips/$task/

#mkdir -p $task
packagepath=$(tr '.' '/' <<< "$package")

mvn archetype:generate -DgroupId=$package -DartifactId=$task -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

rm "$task/src/main/java/$packagepath/App.java"

for f in */ ; do
	echo "$f $task"
	if [[ "$f" != "$task/" ]]
	then
		cp -r $f "$task/src/main/java/"
	fi
done

jar -cMf $zippath$task/$uniid.zip $task

chmod +x $zippath$task/$uniid.zip

rm -r $task

echo "$zippath$task/$uniid.zip"