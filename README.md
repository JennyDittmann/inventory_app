# inventory_app
Tool zur Inventarisierung und Lokalisierung des Hausstandes.

Diese App befindet sich derzeit in der Entwicklung. Sie wird überwiegend test driven entwickelt. Aktuell wurde ein grundlegendes Datenmodell sowie erste Suchlogiken implementiert, welches es ermöglichen Gegenstände anhand von Tags zu finden.

## Gibts schon
- Datenmodell mit Items, Räumen, Tags, Containern
- Management Layer
  - Items & Tags hinzufügen, updaten und löschen
  - Verschiedene Suchstrategien für Items mittels Tags

## Kommt irgendwann
- REST API
- Docker Container
- Örtliche Lokalisierung von Items anhand virtueller Raum Koordinaten
- "Pushed Items" -> Items welche man häufig sucht werden bei der Suche höher gewichtet
- "Void container" sowie "Nexus room" -> Wenn man weiß dass das Item vorhanden ist, der Ort/Container jedoch unbekannt ist.
- Radiussuche anhand der virutellen Koordinaten
- Abfrage ob das gewünschte Item an dem hinterlegten Ort gefunden wurde -> Wenn Nicht -> Anpassungsmenü des Containers/Raumes
