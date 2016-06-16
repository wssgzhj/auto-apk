#!/bin/bash

project_path="demo"
output_dir="apk-home/"
archive_dir="archive/"
stime=`date +%s`

if [ ! -x "$output_dir" ]; then  
mkdir "$output_dir"  
fi

if [ ! -x "$archive_dir" ]; then  
mkdir "$archive_dir"  
fi  

# 1. get dir
# resolve links - $0 may be a softlink
prg="$0"

while [ -h "$prg" ] ; do
	ls=`ls -ld "$prg"`
	link=`expr "$ls" : '.*-> \(.*\)$'`
	if expr "$link" : '/.*' > /dev/null; then
		prg="$link"
	else
		prg=`dirname "$prg"`/"$link"
	fi
done

prg_path=$(cd `dirname "$prg"`; pwd)


# 2. build single apk
cd $project_path
gradle clean assembleRelease


# 3. build mutil-channel apk
cd $prg_path
rm -rf "${output_dir}*"
apk_path="${project_path}/app/build/outputs/apk/app-release.apk"
python generate-channel-apk.py $apk_path $output_dir
echo ">>> build mutil-channel apk complete"


# 4. backup mapping info
mapping_path="${project_path}/app/build/outputs/mapping"
cp -a $mapping_path $output_dir
echo ">>> backup mapping info complete"


# 5. add archive
file_name=`date "+%y%m%d%H%M%S"`.zip
archive_file="${archive_dir}${file_name}"
src_dir="apk-home/"
zip -rq $archive_file $src_dir
echo ">>> add archive complete: ${file_name}"


etime=`date +%s`
echo ">>> Bingo :)"
echo ">>> It fucking took me" $[$etime-$stime]"s."
