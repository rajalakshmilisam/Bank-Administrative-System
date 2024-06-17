#!/bin/bash
set -e

docker-entrypoint.sh mysqld &

until mysqladmin ping -h"localhost" --silent; do
    echo "Waiting for MySQL server to start..."
    sleep 2
done

# Execute your commands
# mysql -u anand -p'password' -h localhost -D claimmanagement -e "SHOW TABLES;"
mysql -u "${MYSQL_USER}" -p"${MYSQL_PASSWORD}" -h localhost -D "${MYSQL_DATABASE}" -e "SHOW TABLES;"

# Keep the container running
wait