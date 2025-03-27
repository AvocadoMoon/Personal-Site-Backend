#!/usr/bin/env bash

generatorCliImage=openapitools/openapi-generator-cli:v7.1.0

scriptDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
parentDir="$(dirname "$scriptDir")"


docker run --rm -v ${scriptDir}:/local ${generatorCliImage} validate -i /local/openapi.yaml --recommend
if [ $? -ne 0 ]; then
    echo "openapi.yaml is not valid"
    exit 1
fi

#docker run --rm -v ${scriptDir}:/local ${generatorCliImage} config-help -g java

docker run --rm -v ${parentDir}:/local \
    ${generatorCliImage} \
    generate \
    -g java \
    -i /local/docker/openapi.yaml \
    -o /local/java-client \
    -c /local/docker/java-config.yaml

docker run --rm -v ${parentDir}:/local \
${generatorCliImage} generate \
    -g typescript-fetch \
    -i /local/docker/openapi.yaml \
    -o /local/typescript-client \
    -c /local/docker/typescript-config.yaml

cd ..

sudo chown zek:zek -R typescript-client
sudo chown zek:zek -R java-client

rm -r ../AvocadoMoon.github.io/src/backend-api/*
mv -f typescript-client/* ../AvocadoMoon.github.io/src/backend-api/
