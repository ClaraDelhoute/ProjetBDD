Fournit la possibilité d'exécuter une seule commande pour créer et exécuter un cluster MongoDB fragmenté sur un poste de travail local avec chaque composant MongoDB ( mongod, mongos) exécuté dans un conteneur Docker distinct.

Fournit 11 conteneurs distincts pour :

4 conteneurs pour les shards (2 pour chaque réplica set).
3 config servers
2 routeur



        // Créer la base de données et la collection
        use mybdd;
        db.Groupe.insertOne({ "placeholder": true });

        // Activer le sharding sur la base de données
        sh.enableSharding("mybdd");

        // Configurer la clé de sharding
        sh.shardCollection("mybdd.Groupe", { "CP": 1 });

        // Ajouter des plages taguées
        sh.addTagRange(
            "mybdd.Groupe",
            { "CP": MinKey },
            { "CP": 50000 },
            "low_CP"
        );
        sh.addTagRange(
            "mybdd.Groupe",
            { "CP": 50001 },
            { "CP": MaxKey },
            "high_CP"
        );

        // Associer les tags aux shards
        sh.addShardTag("${array[0]}", "low_CP"); // Premier shard
        sh.addShardTag("${array[1]}", "high_CP"); // Deuxième shard