#Argument #1 on .csv faili nimi, kust lugema hakkame

OLDIFS=$IFS
IFS=","
while read id
	do 
		uniid=$(echo $id | cut -d '@' -f 1)
		./cloneITI0011.sh $uniid
	done < $1
IFS=$OLDIFS
