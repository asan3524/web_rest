
JAVA_OPTS="-server -Xms256m -Xmx256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xverify:none -XX:+UseParNewGC -XX:+UseConcMarkSweepGC"

jar_name=$1
this_dir="$( cd "$( dirname "$0"  )" && pwd )"
parent_dir=`dirname "${this_dir}"`
log_dir="${parent_dir}/logs"
log_file="${log_dir}/log.out"
jar_file="${parent_dir}/${jar_name}"


if [ $# -lt 1 ] || [ -z $1 ]; then
    echo -e "\033[31m请输入要部署的jar包名称!\033[0m"
    exit 1
fi


if [ ! -d "${log_dir}" ]; then
    mkdir "${log_dir}"
fi


if [ -f "${jar_file}" ]; then
    java $JAVA_OPTS -jar ${jar_file} >"${log_file}" 2>&1 &
    exit 0
else
    echo -e "\033[31m${jar_file}文件不存在！\033[0m"
    exit 1
fi
