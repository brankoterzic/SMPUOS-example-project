#!/bin/bash

minikube start

#Setup Minikube to run local Docker images ad  build the Docker image
eval $(minikube docker-env)

#Delete all local docker images and containers
docker stop $(docker ps -a -q)
docker rm $(docker ps -aq)
docker rmi -f $(docker images -a -q)

kubectl delete -f ./mongodb/deployment.yml
kubectl delete -f ./mongodb/service.yml
kubectl delete -f ./user-service/deployment.yml
kubectl delete -f ./user-service/service.yml



cd ../SpringDataMongoDB/ && docker build -t user-service:latest .

cd ../kubernetes/mongodb && kubectl create -f deployment.yml && kubectl create -f service.yml

cd ../user-service && kubectl create -f deployment.yml && kubectl create -f service.yml 

minikube service user-service
