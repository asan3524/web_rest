
JAVA_OPTS="-server -Xms400m -Xmx400m -Xmn300m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xverify:none -XX:+DisableExplicitGC -Djava.awt.headless=true"

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
    #重定向标准错误输出到文件，丢掉标准输出
    java $JAVA_OPTS -jar ${jar_file} 1>/dev/null 2>"${log_file}" &
    exit 0
else
    echo -e "\033[31m${jar_file}文件不存在！\033[0m"
    exit 1
fi
