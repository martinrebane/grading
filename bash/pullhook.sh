#!bin/bash

uniid=$(echo $1 | awk '{print tolower($0)}')
echo $uniid
path="$2/$uniid"

cd $path

if [ $(git rev-parse --is-bare-repository) = true ]
then
    REPOSITORY_BASENAME=$(basename "$PWD") 
else
    REPOSITORY_BASENAME=$(basename $(readlink -nf "$PWD"))
fi
echo REPOSITORY_BASENAME is $REPOSITORY_BASENAME

#git stash
git pull