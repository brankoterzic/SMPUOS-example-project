#!/bin/bash

#Delete all local docker images and containers
docker stop $(docker ps -a -q)
docker rm $(docker ps -aq)
docker rmi -f $(docker images -a -q)

docker-compose up
