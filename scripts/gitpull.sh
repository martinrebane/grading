#kogu toiming oleneb failisysteemi ylesehitusest

for repo in repod/*
do
	echo $repo
	cd $repo
	git pull
	cd ../
	cd ../
done
