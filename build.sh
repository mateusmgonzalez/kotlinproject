
# shellcheck disable=SC2164
cd ./mylibrary

./gradlew build -x test

# shellcheck disable=SC2103
cd ..

cd ./recomendacoes/

./gradlew build -x test

cd ..

cd ./springcloud/eurekaserver

./gradlew build -x test

cd ../..

docker compose up