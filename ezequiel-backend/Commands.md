### Build Native Image Instantly
```
mvn clean spring-boot:build-image -DskipTests=true
```

### Create OpenAPI Spec
```
mvn clean install -P gen-spec -DskipTests=true
```

### Start Server
```
mvn spring-boot:run
```
