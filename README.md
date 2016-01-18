# Zoo
Dieser Sourcecode soll einen Threadbasierten Zoo darstellen (Brabenez Aufgabe).
###Was bisher geschah...
Derzeit wurde ein
* Pinguin
* Pinguingehege
* Zoowärter
* GUI
* Kommunikationsschnittstelle über Sockets
realisiert.

###Der Ablauf
Jedes Objekt (Pinguin, Gehege, ect...) hat seine persönlichen Eigenschaften.

####Pinguin
Sobald ein Pinguin erzeugt wird,
* verbindet sich dieser mit dem Server
* bekommt vom Server einen Wärter zugewiesen
* legt Eier in einem zufälligen Zeitabstand

####Pinguingehege
Sobald ein Pinguingehege erzeugt wird,
* verbindet sich dieses mit dem Server
* bekommt vom Server einen Wärter zugewiesen
* Temperatur Sinkt wenn die Heizung nicht eingeschaltet ist

####Zoowärter
Sobald dieser ein Objekt zugewiesen bekommt,
* stellt er fest um welche Art Objekt es sich handelt
* reagiert entsprechend auf Events des zugewiesenen Objektes
(Sammelt die Eier des Pinguins ein oder dreht im Gehege die Heizung auf etc...)


##Was noch zu tun ist...
Der Großteil der Arbeit wurde erledigt. Die Punkte, welche noch zu erledigen sind, sind zu 90% Copy&Paste-Arbeit.
* Jeder soll ein Tier + ein Gehege implementieren

Um diese unglaublich vielen Punkte zu meistern muss man lediglich
* Den Pinguin und das Pinguingehege kopieren und erweitern
* Sich ein cooles Tier mit coolen Bedürfnissen ausdenken
* Den Pinguin so verändern, dass er dem gewünschten Tier entspricht
* Den Zoowärter erweitern, damit er entsprechend auf das gewünschte Tier reagieren kann

#####Note
Jeder, der weitere Funktionen hinzufügen möchte ist dazu herzlich eingeladen.
Ebenso wie jeder, der die Qualität des Sourcecodes (zB. Kommentiern) verbessern möchte.
