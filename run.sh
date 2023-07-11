# Levantar los servicios
docker-compose up -d --build

# Iniciar el nodo master y los nodos worker enlazandolos al master
docker exec master-node /opt/spark/sbin/start-master.sh
docker exec worker1-node /opt/spark/sbin/start-worker.sh spark://master-node:7077
docker exec worker2-node /opt/spark/sbin/start-worker.sh spark://master-node:7077
docker exec worker3-node /opt/spark/sbin/start-worker.sh spark://master-node:7077