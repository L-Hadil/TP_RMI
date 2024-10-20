# README - Projet RMI Cabinet Vétérinaire

## Binômes
- **Nom du binôme 1**: Hadil Ladj 
- **Nom du binôme 2**: Marvin MARIE-LOUISE

---

## Description du projet

Ce projet est une application Java utilisant la technologie RMI (Remote Method Invocation) pour implémenter un système de gestion de cabinet vétérinaire. Il permet d'ajouter, modifier, supprimer et rechercher des informations sur les animaux du cabinet, ainsi que de gérer leur dossier de suivi. Une fonctionnalité de notification est également présente pour informer les clients inscrits lorsque le cabinet atteint un certain seuil d'animaux enregistrés.

### Fonctionnalités principales :
- **Gestion des animaux** : Ajout, suppression, modification et recherche d'animaux.
- **Notifications** : Les clients sont notifiés automatiquement lorsque le cabinet atteint 100, 500 ou 1000 animaux.
- **Gestion des dossiers de suivi** : Chaque animal dispose d’un dossier de suivi médical qui peut être consulté et mis à jour.
  
### Architecture
Le projet se compose de plusieurs modules :
1. **Serveur RMI** : Héberge la logique du cabinet vétérinaire, gère les objets animaux et le système de notification.
2. **Client RMI** : Permet aux utilisateurs de gérer les animaux à distance et de recevoir des notifications.
3. **Callback RMI** : Utilisé pour informer les clients inscrits lorsqu'un seuil est atteint.

---

## Prérequis
- **Java JDK** : Version 11 ou plus récente.
- **Environnement de développement** : IntelliJ IDEA, Eclipse ou autre IDE compatible avec Java.
- **Politique de sécurité RMI** : Le projet utilise une politique de sécurité RMI via un fichier `security.policy`.

---

## Installation et exécution

### 1. Configuration des chemins

**Important :** Les chemins d'accès dans le projet sont spécifiques à un environnement local. Vous devez adapter les chemins dans votre fichier de projet en fonction de votre propre système.

Dans le fichier **Client.java**, mettez à jour ces lignes avec vos propres chemins :
```java
System.setProperty("java.security.policy", "/chemin/vers/votre/security.policy");
System.setProperty("java.rmi.server.codebase", "file:/chemin/vers/votre/out/production/client/");
```

### 2. Exécution du Serveur

1. Compiler et exécuter la classe **CabinetVeterinaireImpl** dans le module serveur.
2. Assurez-vous que le serveur RMI est bien démarré avec le registre associé.

### 3. Exécution du Client

1. Compiler et exécuter la classe **Client.java**.
2. Le client offre un menu interactif pour ajouter, modifier, supprimer des animaux, ou encore consulter leur dossier médical.


