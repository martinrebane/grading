#!bin/bash

uniid=$(echo $1 | awk '{print tolower($0)}')
echo $uniid
repopath=$2
path=$repopath$uniid

cd $path

git pull