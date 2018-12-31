# HumineAYPI
### *Guide d'utilisation du BeaconScript:*  
 #### Sommaire:
1. Creer l'environnement d'execution.
2. Réalisez votre premier script.
3. Le compilateur.
4. Les variables.
5. Les conditions.
6. Les boucles.
_________________
# **1) Créer l'environnement d'execution.**

Avant de commencer a créer vos premier script en ```BeaconScript```, il est nécéssaire de bien comprendre comment bien installer un environnement cappable de comprendre vos futurs scripts.

Dans un premier temps il va falloir procéder a l'instalation d'un serveur minecraft. L'objectif est que vous soyez administrateur sur celui-ci et que vous puissiez avoir accés a la totalité de ces dossiers... *(L'objectif de ce guide n'est pas de présenter les détais de l'instalation d'un serveur. C'est a vous de vous débrouiller de faire vos propres recherches.)*

#### A) Installer L'AYPI. 

Une fois votre serveur mis en place, vous pouvez voir qu'il possède un dossier ```/plugins```. Vous pouvez alors télécharger le moteur de vos futurs scripts: L'*AYPI*. Télécharger donc la dernière version, et glisser la dans le dossier ```/plugins``` de votre serveur.
A présent démarer votre serveur.

Accédez a la console du serveur, et tappez la commande:  
```> plugins``` ou ```> pl```	  
Le but de cette commande est d'afficher la liste des plugins installer sur le serveur. Si vous avez correctement installer *l'AYPI*, le serveur devrez afficher:  
```Plugins (1): Aypi```.

#### B) Configurer L'AYPI. 
Quand L'*AYPI* est installé sur un serveur, il va créer de nouveaux fichier dans le dossier ```/plugins``` de celui-ci. Rendez vous dans le dossier ```/plugins/Aypi```. Ouvrez le fichier ```config.yml``` puis changez la valeur de ```xmlloader: false``` a ```xmlloader: true```. Redémarrez le serveur et observez que le plugins a créer un nouveaux dossier.
Le dossier ```/plugins/Aypi/xml-reader```qui viens d'être créer est **le** dossier dans le quel vous devrez placer vos scripts.
_________
# **2) Réalisez votre premier script.** #

### A) Le format XML #
Les scripts ```BeaconScript``` sont en réalité des fichiers au format ```xml```. Leurs contenus respecte donc un format particulier sout la forme de *"Balise"*.
Si vous n'étes pas familiez au language de balisage, je vous conseile de lire cette <a href="https://openclassrooms.com/fr/courses/1766341-structurez-vos-donnees-avec-xml/1766585-les-elements-de-base">article</a>.

### B) L'entête du fichier #
Pour des soucis de simplicité la totalité de vos scripts devrons commencer par cet entête:
```<?xml version="1.0" encoding="utf-8"?>```.
Je ne rentrerez pas dans les details de pourquoi cette ligne est importante mais je vous conseile de vous y interesser en mennant vos propres recherches sur le sujet.  
Creer la balise ```<server></server>```. Cette balise et la racine de votre script. Tout votre code devra se trouver a l'interieur de cette balise.  

### C) Votre premier script 

Observons le célébre ***"Hello World !"*** adappté en ```BeaconScript```.  

```XML
<?xml version="1.0" encoding="utf-8"?>
<server>
	<on-server-start>
		<console-log>'Hello World !'</console-log>
	</on-server-start>
</server>
```
Nous pouvons voir deux nouvelles balises:
* La balise ```<on-server-start>```
* La balise ```<console-log>```

La balise ```<on-server-start>``` est une balise évenement. Les balises évenements sont des balises qui vont executer leurs contenus quand l'evenement qui lui est atribuer s'execute sur le serveur. Par convention les balises evenements commence par on et se poursuivent par le nom de l'évenement en question.
Ici la balise s'execute quand le serveur démarre.

La balise ```<console-log>``` est une balise a contenus compilable. Nous verrons plus en details par la suite ce que nous propose un contenus compilable mais dans le cas présent, la balise prend un contenus de type *'STRING'* c'est a dire une chaine de caractère.

>Un **'STRING'** doit obligatoirement être entourer par des guillmet simple '. Sinon le compilateur ne comprendra pas quoi faire.    

 La balise ```<console-log>``` va afficher un message dans la console du serveur. Le message a sera donc le contenus compilable de la balise.

 Le code présenter va donc afficher le message "Hello World !" dans la console du serveur quand celui-ci va s'allumer.
 _________

 # 3) Le compilateur 
Le compilateur est une notion a assez importante a comprendre afin de pouvoir exploiter au mieu le ```BeaconScript```.

Il existe trois type de contenus pouvant être interpreter par le compilateur:
* 'STRING' ou chaine de caractère un type que nous avons vue précédament.
* 'NUMBER' ou les nombre. Utile pour réaliser des calcules.
* 'BOOLEAN' prend en compte les valeur 'true' ou 'false'

>Le compilateur va prendre une serie d'arguments et faire toutes les operations demander avec chaqu'un d'entre eux pour renvoyer un seul résultat. Tout les arguments doivent être séparé par un espace.
#### A) Les operations sur les nombres ou type 'NUMBER' #  

Il est possible de faire 4 type d'opperation entre les nombres:
* les addition avec le signe '+'.
* les soustractions avec le signe '-'.
* les multiplications avec le signe '*'.
* les divisions avec le signe '/' (a savoir qu'il est bien sur impossible de diviser par 0).

voyons quelque cas pour mieux comprendre comment fonctionne le compilateur avec les nombre.

Tout les codes vont afficher dans la console ```42```
```XML
<!-- quelque calcules simple -->
<console-log>10 + 32</console-log>
<console-log>50 - 8</console-log>
<console-log>21 * 2</console-log>
<console-log>84 / 2</console-log>

<!-- on peut aussi combiner les calcules -->
<console-log>100 / 2 - 20 * 1.5 - 3</console-log>
```

Vous remarquerez que le dernier calcules affiche ```42``` et non ```17```. Le compilateur ne respecte pas les prioriter de calcules mathematique traditionnelle. Il se contentante de faire les calcules dans l'ordre de gauche a droite. Alors comment faire en sorte de modifier ces prioriter de calcules ?
Il suffit d'utiliser des parantheses. L'orsque que le compilateur rencontre des parantheses il va calculer leurs contenus en prioriter.

```XML
<!-- on peut aussi combiner les calcules -->
<console-log>(100 / 2) - (20 * 1.5) - 3</console-log>
```
Maintenant le code affiche ```17``` et non ```42```.

#### B) Les operations sur les boolean ou type 'BOOLEAN'

Un boolean peut avoir deux valeur possible: vrai ou fausse soit ```'true'``` ou ```'false'```.

Il est possible de faire plusieurs opperation entre les valeurs boolean. Il y a trois operateur différent pour les boolean.

* ```AND``` retourne ```'true'``` si les deux valeurs comparé sont ```'true'```.
* ```OR``` retourne ```'true'``` si au moins une des valeurs comparé est ```'true'```.
* ```XOR``` retourne ```'true'``` si une seul des valeurs comparé et ```'true'```.

Dans tout les autres cas les operateur retourne la valeur ```'false'```.  
Observons donc quelques exemple a travers du code.
```XML
<!-- retourne 'true' -->
<console-log>true AND true</console-log>

<console-log>true OR false</console-log>
<console-log>false OR true</console-log>
<console-log>true OR true</console-log>

<console-log>true XOR false</console-log>
<console-log>false XOR true</console-log>

<!-- retourne 'false' -->
<console-log>true AND false</console-log>
<console-log>false AND true</console-log>
<console-log>false AND false</console-log>

<console-log>false OR false</console-log>

<console-log>true XOR true</console-log>
<console-log>false XOR false</console-log>
```

Il est egualement possible d'utiliser des parentheses pour faire des calcules avec prioriter.

Il existe un dernier operateur boolean. Le ```NOT```. C'est un operateur particulier car il ne prend qu'un seul paramètre. Le ```NOT``` va inverser la valeur du boolean qui se trouve après lui.  
exemple:
```XML
<!-- retourne 'true' -->
<console-log>NOT false</console-log>

<!-- retourne 'false' -->
<console-log>NOT true</console-log>
```

### C) Traitement des type 'STRING'

Dans le cas des type **'STRING'** il est possible de les additionner entre eux.  
exemple:

```XML
<!-- affiche 'Bonjour tout le monde' dans la console-->
<console-log>'Bonjour tout ' + 'le monde'</console-log>
<!-- affiche 'Vous avez 42 ans' dans la console-->
<console-log>'Vous avez ' + 42 + ' ans</console-log>
<!-- affiche 'Vous etes majeur: true' dans la console-->
<console-log>'Vous etes majeur: ' + true</console-log>
```

### D) Encore plus de comparaisons
Il existe plusieur comparateur de nombre:  
* égal: eq
* inferieur: lt
* superieur: gt
* inferieur ou égal: loe
* superieur ou égal: goe
