#!/bin/bash

# Cargamos las variables de entorno
source ./.env

# Levantar los servicios
docker-compose up -d --build

# Iniciar el nodo master y los nodos worker enlazandolos al master
docker exec $MASTER_NODE /opt/spark/sbin/start-master.sh
docker exec $WORKER1_NODE /opt/spark/sbin/start-worker.sh spark://$MASTER_NODE:7077
docker exec $WORKER2_NODE /opt/spark/sbin/start-worker.sh spark://$MASTER_NODE:7077
docker exec $WORKER3_NODE /opt/spark/sbin/start-worker.sh spark://$MASTER_NODE:7077