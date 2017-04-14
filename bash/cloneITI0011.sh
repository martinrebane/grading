#$1 on csv fail lugemiseks, hetkel kasutasin ITI0011 uniID-sid
#voib teha ka nii, et user on command line'ilt ette antud (ehk siis maria.kert asemel miski muu)

uniID=$(echo $1 | awk '{print tolower($0)}')
path = "https://maria.kert@git.ttu.ee/ained/iti0011/$uniID.git"
echo $uniID

cd repod
git clone $path
cd ../