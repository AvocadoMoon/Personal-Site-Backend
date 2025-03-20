
#!/bin/bash


# Check if a container named ezequielDB already exists
container_exists=$(docker ps -a --filter "name=^ezequielDB$" --format '{{.Names}}')

if [[ "$container_exists" == "ezequielDB" ]]; then
    echo "Container 'ezequielDB' already exists. Deleting it..."
    docker rm -f ezequielDB
fi

# Create a new PostgreSQL container named ezequielDB
echo "Creating a new PostgreSQL container named 'ezequielDB'..."
docker run --name ezequielDB -e POSTGRES_PASSWORD=passwd -p 127.0.0.1:5432:5432 postgres

if [[ $? -eq 0 ]]; then
    echo "PostgreSQL container 'ezequielDB' has been created successfully."
    else
    echo "Failed to create the PostgreSQL container. Please check Docker and try again."
fi




