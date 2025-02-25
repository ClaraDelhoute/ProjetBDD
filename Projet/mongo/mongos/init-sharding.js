// Initialisation de la base de données
db = db.getSiblingDB("mydatabase");

/*
====================================================================================================
================================== Création de l'utilisateur admin ==================================
====================================================================================================
*/

// Création d'un utilisateur avec des droits complets sur la base de données
db.createUser({
    user: "adminUser",
    pwd: "adminPassword123", // Assurez-vous de définir un mot de passe sécurisé
    roles: [
        { role: "dbOwner", db: "mydatabase" }
    ]
});

/*
====================================================================================================
================================== Création des collections ========================================
====================================================================================================
*/

// Création de la collection "groupe"
db.createCollection("groupes");
db.createCollection("membres");
db.createCollection("materiels");
db.createCollection("commandes");

/*
====================================================================================================
===================================== Création des indexs ==========================================
====================================================================================================
*/

db.groupes.createIndex({ codePostal: 1 });

db.membres.createIndex({ codePostal: 1 });

db.materiels.createIndex({ codePostal: 1 });
db.materiels.createIndex({ idGroupe: 1 });

db.commandes.createIndex({ codePostal: 1 });
db.commandes.createIndex({ idMembre: 1 });

/*
====================================================================================================
================================== Configuration du sharding ======================================
====================================================================================================
*/


// Activation du sharding sur la base de données
sh.enableSharding("mydatabase");

// Activation du sharding
sh.shardCollection("mydatabase.groupes", { codePostal: 1 });
sh.shardCollection("mydatabase.membres", { codePostal: 1 });
sh.shardCollection("mydatabase.materiels", { codePostal: 1 });
sh.shardCollection("mydatabase.commandes", { codePostal: 1 });

// Configuration des plages de données pour les shards
sh.splitAt("mydatabase.groupes", { codePostal: 50000 });
sh.moveChunk("mydatabase.groupes", { codePostal: 0 }, "shard0");
sh.moveChunk("mydatabase.groupes", { codePostal: 50001 }, "shard1");

sh.splitAt("mydatabase.membres", { codePostal: 50000 });
sh.moveChunk("mydatabase.membres", { codePostal: 0 }, "shard0");
sh.moveChunk("mydatabase.membres", { codePostal: 50001 }, "shard1");

sh.splitAt("mydatabase.materiels", { codePostal: 50000 });
sh.moveChunk("mydatabase.materiels", { codePostal: 0 }, "shard0");
sh.moveChunk("mydatabase.materiels", { codePostal: 50001 }, "shard1");

sh.splitAt("mydatabase.commandes", { codePostal: 50000 });
sh.moveChunk("mydatabase.commandes", { codePostal: 0 }, "shard0");
sh.moveChunk("mydatabase.commandes", { codePostal: 50001 }, "shard1");


/*
====================================================================================================
======================================= Insertion des données ======================================
====================================================================================================
*/


// Insertion des données dans "groupes"
db.groupes.insertMany([
    { _id: ObjectId('67800070c5f2fb32c3f37ba1'), numero: "A001", nom: "Groupe Alpha", ville: "Paris", codePostal: 75001, _class: "org.example.classes.Groupe" },
    { _id: ObjectId('67800070c5f2fb32c3f37ba2'), numero: "A002", nom: "Groupe Beta", ville: "Lyon", codePostal: 69000, _class: "org.example.classes.Groupe" },
    { _id: ObjectId('67800070c5f2fb32c3f37ba3'), numero: "A003", nom: "Groupe Gamma", ville: "Nice", codePostal: 6000, _class: "org.example.classes.Groupe" },
    { _id: ObjectId('67800070c5f2fb32c3f37ba4'), numero: "A004", nom: "Groupe Delta", ville: "Caen", codePostal: 14000, _class: "org.example.classes.Groupe" },
    { _id: ObjectId('67800070c5f2fb32c3f37ba5'), numero: "A005", nom: "Groupe Epsilon", ville: "Lille", codePostal: 59000, _class: "org.example.classes.Groupe" },
]);

// Insertion des données dans "membre"
db.membres.insertMany([
    { _id: ObjectId('67800070c5f2fb32c3f37bc1'), nom: "Dupont", prenom: "Jean", adresse: "10 Rue de Paris", ville: "Paris", codePostal: 75001, email: "jean.dupont@example.com", typeMembre: "CLIENT", password: "password123", confirmPassword: "password123", _class: "org.example.classes.Membre" },
    { _id: ObjectId('67800070c5f2fb32c3f37bc2'), nom: "Durand", prenom: "Marie", adresse: "20 Rue de Lyon", ville: "Lyon", codePostal: 69000, email: "marie.durand@example.com", typeMembre: "CLIENT", password: "securePass456", confirmPassword: "securePass456", _class: "org.example.classes.Membre" },
    { _id: ObjectId('67800070c5f2fb32c3f37bc3'), nom: "Martin", prenom: "Paul", adresse: "30 Rue de Nice", ville: "Nice", codePostal: 6000, email: "paul.martin@example.com", typeMembre: "CLIENT", password: "niceDay789", confirmPassword: "niceDay789", _class: "org.example.classes.Membre" },
    { _id: ObjectId('67800070c5f2fb32c3f37bc4'), nom: "Bernard", prenom: "Claire", adresse: "40 Rue de Caen", ville: "Caen", codePostal: 14000, email: "claire.bernard@example.com", typeMembre: "CLIENT", password: "clairePass101", confirmPassword: "clairePass101", _class: "org.example.classes.Membre" },
    { _id: ObjectId('67be408f933d5116a45a1550'), nom: "a", prenom: "a", adresse: "a", ville: "a", codePostal: 59135, email: "i@yop", typeMembre: "ACTIF", _class: "org.example.classes.Membre" },
    { _id: ObjectId('67800070c5f2fb32c3f37bc5'), nom: "Legrand", prenom: "Alice", adresse: "50 Rue de Lille", ville: "Lille", codePostal: 59000, email: "alice.legrand@example.com", typeMembre: "CLIENT", password: "aliceKey202", confirmPassword: "aliceKey202", _class: "org.example.classes.Membre" }
]);

// Insertion des données dans "materiels"
db.materiels.insertMany([
    { _id: ObjectId(), numeroSerie: "123456", modele: "Inspiron", type: "LAPTOP", marque: "Dell", prix: 500, _class: "org.example.classes.Materiel" },
    { _id: ObjectId(), numeroSerie: "789012", modele: "Pavilion", type: "LAPTOP", marque: "HP", prix: 600, _class: "org.example.classes.Materiel" },
    { _id: ObjectId(), numeroSerie: "345678", modele: "ThinkPad", type: "LAPTOP", marque: "Lenovo", prix: 550, _class: "org.example.classes.Materiel" },
    { _id: ObjectId(), numeroSerie: "901234", modele: "MacBook", type: "LAPTOP", marque: "Apple", prix: 1200, _class: "org.example.classes.Materiel" },
    { _id: ObjectId(), numeroSerie: "567890", modele: "VivoBook", type: "LAPTOP", marque: "Asus", prix: 700, _class: "org.example.classes.Materiel" }
]);

// Insertion des données dans "commandes"
db.commandes.insertMany([
    { _id: ObjectId(), idMembre: { "$ref": "membres", "$id": ObjectId("67800070c5f2fb32c3f37bc3") }, idVendeur: { "$ref": "membres", "$id": ObjectId("67be408f933d5116a45a1550") }, date: new Date("2024-11-15T10:30:00Z"), liste: [{ _id: ObjectId(), numeroSerie: "123456", modele: "Inspiron", type: "LAPTOP", marque: "Dell", prix: 500 }], prixTotal: 500, codePostal: 75001 },
    { _id: ObjectId(), idMembre: { "$ref": "membres", "$id": ObjectId("67800070c5f2fb32c3f37bc3") }, idVendeur: { "$ref": "membres", "$id": ObjectId("67be408f933d5116a45a1550") }, date: new Date("2025-01-05T14:00:00Z"), liste: [{ _id: ObjectId(), numeroSerie: "789012", modele: "Pavilion", type: "LAPTOP", marque: "HP", prix: 600 }, { _id: ObjectId(), numeroSerie: "345678", modele: "ThinkPad", type: "LAPTOP", marque: "Lenovo", prix: 550 }], prixTotal: 1150, codePostal: 69000 },
    { _id: ObjectId(), idMembre: { "$ref": "membres", "$id": ObjectId("67800070c5f2fb32c3f37bc3") }, idVendeur: { "$ref": "membres", "$id": ObjectId("67be408f933d5116a45a1550") }, date: new Date("2025-02-10T09:15:00Z"), liste: [{ _id: ObjectId(), numeroSerie: "901234", modele: "MacBook", type: "LAPTOP", marque: "Apple", prix: 1200 }], prixTotal: 1200, codePostal: 6000 },
    { _id: ObjectId(), idMembre: { "$ref": "membres", "$id": ObjectId("67800070c5f2fb32c3f37bc3") }, idVendeur: { "$ref": "membres", "$id": ObjectId("67be408f933d5116a45a1550") }, date: new Date("2025-03-22T18:45:00Z"), liste: [{ _id: ObjectId(), numeroSerie: "567890", modele: "VivoBook", type: "LAPTOP", marque: "Asus", prix: 700 }, { _id: ObjectId(), numeroSerie: "yu", modele: "yu", type: "ECRAN", marque: "yu", prix: 5 }], prixTotal: 705, codePostal: 14000 },
    { _id: ObjectId(), idMembre: { "$ref": "membres", "$id": ObjectId("67800070c5f2fb32c3f37bc3") }, idVendeur: { "$ref": "membres", "$id": ObjectId("67be408f933d5116a45a1550") }, date: new Date("2025-04-10T20:10:00Z"), liste: [{ _id: ObjectId(), numeroSerie: "123456", modele: "Inspiron", type: "LAPTOP", marque: "Dell", prix: 500 }, { _id: ObjectId(), numeroSerie: "345678", modele: "ThinkPad", type: "LAPTOP", marque: "Lenovo", prix: 550 }, { _id: ObjectId(), numeroSerie: "567890", modele: "VivoBook", type: "LAPTOP", marque: "Asus", prix: 700 }], prixTotal: 1750, codePostal: 59000 }    
]);