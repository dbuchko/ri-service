git pull
./gradlew clean build -Dhttp.proxyHost=162.53.103.195 -Dhttp.proxyPort=80 -Dhttps.proxyHost=162.53.103.195 -Dhttps.proxyPort=80 -x test
cf push -f pas/manifest-lower.yml
