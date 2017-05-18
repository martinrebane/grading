projectpath=$1
hashpath=$2 #"D:/Users/mammu/workspace/loputoo/hashes/"

cd $projectpath

#D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/
#find $path -type f -print0 | xargs -0 sha1sum
#value=`find $path -type f -print0 | xargs -0 sha1sum`
#echo "${value}"
#echo $value
folders=$(echo $projectpath | tr "/" "\n")
value="$(find $projectpath -type f -print0 | xargs -0 sha1sum)"
#see on ilmselt vajalik ainult windowsis testimiseks, kui just ei tahagi, et ta hashi faili kirjutaks
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