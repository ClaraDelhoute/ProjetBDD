#!/bin/bash

# Attendre que mongos soit prêt
until /usr/bin/mongosh --quiet --eval 'db.getMongo()'; do
    sleep 1
done

# Ajouter les shards
IFS=';' read -r -a array <<< "$SHARD_LIST"
for shard in "${array[@]}"; do  
    /usr/bin/mongosh --port 27017 <<EOF
        sh.addShard("${shard}");
EOF
done

# Exécuter le script d'initialisation du sharding
/usr/bin/mongosh < /scripts/init-sharding.js