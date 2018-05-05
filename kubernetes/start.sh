#!/bin/bash

docker stop $(docker ps -a -q)
docker rm $(docker ps -aq)
docker rmi -f $(docker images -a -q)

minikube start

#Setup Minikube to run local Docker images ad  build the Docker image
eval $(minikube docker-env)
cd ../SpringDataMongoDB/ && docker build -t user-service:latest .

cd ../kubernetes/mongodb && kubectl create -f deployment.yml && kubectl create -f service.yml

cd ../user-service && kubectl create -f deployment.yml && kubectl create -f service.yml 
