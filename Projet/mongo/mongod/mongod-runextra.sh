#!/bin/bash

if [ "$DO_INIT_REPSET" = true ] ; then
    # Attendre que le service MongoDB soit prêt
    until /usr/bin/mongosh --port 27017 --quiet --eval 'db.getMongo()'; do
        sleep 1
    done

    # Initialiser le replica set
    /usr/bin/mongosh --port 27017 <<EOF
        rs.initiate({_id: "${REPSET_NAME}", members: [
            {_id: 0, host: "${REPSET_NAME}-replica0:27017"},
            {_id: 1, host: "${REPSET_NAME}-replica1:27017"}
        ]});
EOF
fi