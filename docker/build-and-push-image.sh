
pushd ../ezequiel-backend
mvn clean spring-boot:build-image -DskipTests=true
popd

docker push localhost:31000/ezequiel-backend:latest
