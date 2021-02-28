# 5401_Structural_Patterns_1

## Aufgabe:
Erstelle Interfaces und Klassen für die Veranschaulichung der strukturellen Entwurfsmuster 'Bridge', 'Proxy', 'Adapter' und 'Facade'.

### Logger-Bridge
- erstelle eine einfache Klasse 'LoggerBridge', die das Logger-Interface 'Logger' implementiert
- diese Klasse soll als Brücke zwischen dem/den Client(s) (=Tester) und der tatsächlich referenzierten Logger-Instanz dienen
- erstelle eine Testklasse 'LoggerBridgeTester', in der ein 'LoggerBridge'-Objekt für das Loggen genutzt wird (durch simple Factory erzeugen lassen)
- lasse die in der Brücke referenzierte Logger-Instanz - nach einer zufälligen Zeit - 'thread-safe' durch die Factory austauschen (eigener Thread)

### Transmitter-/Receiver-Proxy
- erstelle eine Proxy-Implementierung 'CipherTextTransmitter/-Receiver' für die Text-Implementierungen von 'Transmitter' und 'Receiver' 
- die beiden Klassen 'CipherTextTransmitter/-Receiver' sollen 'TextTransmitter-/Receiver' kapseln und Ende-zu-Ende-Verschlüsselung ergänzen
- als letzter Ausweg vor dem Scheitern einer Realisierung kann eine 'einfache Verschlüsselung' durch Groß-/Kleinschreibung eingesetzt werden
- erstelle eine weitere konkrete Factory 'CipherTextTransRecvFactory', über die Instanzen dieser Klassen erzeugt und angefordert werden können
- adaptiere eine Kopie der Abstract Factory-Testklasse dahingehend, dass nun Transmitter/Receiver-Instanzen über eine Instanz dieser Klasse (Singleton) erzeugt werden
- ergänze die verwendeten Transmitter-/Receiver-Klassen so, dass sie den verarbeiteten Text zur Kontrolle ausgeben (TextLogger, Ausgabe: Klartext->Chiffrat->Chiffrat->Klartext)

### ResultSet-Iterable-Adapter (mit/ohne Generics)
- die Lösung dieser Aufgabe kann durch den Einsatz konkreter Typen (Adventure, Album, ...) anstatt der Typ-Parameter 'T' und 'E' entschärft werden
- Zusatzinfo: 'Iterable' (z.B. Collection, ...) liefert einen 'Iterator', 'Iterator.hasNext' wird als Schleifenbedingung genutzt (vgl. ResultSet.next), 'Iterator.next' liefert das aktuelle Element der Iteration
- erstelle eine das Interface 'Iterator\<E\>' bzw. '\<Adventure/Album/...\>' implementierende abstrakte Klasse 'ResultSetIterator\<E\>' bzw. '\<Adventure/Album/...\>'
- Instanzen dieser Klasse verwalten intern ein ResultSet-Objekt (im Konstruktor übergeben) und liefern durch die Methode 'hasNext' die Verfügbarkeit eines weiteren ELements und durch die Methode 'next' die einzelnen Zeilen als spezifisches Objekt (Adventure/Album/...)
- implementiere darin die beiden Methoden 'hasNext' und 'next' ('hasNext' ruft 'ResultSet.next' auf, 'next' soll von Subklassen überschrieben werden)
- erstelle eine konkrete Implementierung zu einer beliebigen DB-Tabelle (Adventure, Alben, ...) und implementiere darin die 'next'-Methode (erzeugt aus einer ResultSet-Zeile ein Objekt vom Typ Adventure/Album/...)
- erstelle eine das Interface Iterable\<T\> bzw. '\<Adventure/Album/...\>' implementierende Klasse 'IterableResultSet\<T\>' mit einem ResultSet als Instanzvariable (im Konstruktor übergeben)
- implementiere darin die Methode 'iterator' (erzeugt eine Instanz der oben erläuterten Iterator\<T\>-  bzw. '\<Adventure/Album/...\>'-Implementierung zum intern gehaltenen ResultSet-Objekt)
- erstelle ein Testprogramm, das ein ResultSet zur gewählten DB erzeugt und dieses in eine ebenfalls zu erzeugende Instanz der beschriebenen Iterable<>-Implementierung platziert
- die Einträge des ResultSet-Objekts sollen abschließend durch eine erweiterte for-Schleife mit Hilfe dieser Iterable\<\>-Instanz aufgelistet werden (dazu in der Datenklasse 'toString' implementieren)

### CSV-Facade
- erstelle eine Fassaden-Implementierung für das Auslesen einer CSV.Datei
- das Auslesen von CSV-Dateien erfordert die Nutzung vieler verschiedener Klassen (File, InputStream, String, Pattern, Matcher, Integer, Double, DateFormat, ...)
- zum Verbergen der Details dieses umständlichen Importmechanismus soll eine Klasse 'CSVFacade' erstellt werden
- in dieser Fassade soll es eine Methode 'import' geben, der ein Dateiname, das Trennzeichen als (RegEx-)String und ein Prototyp-Objekt übergeben werden
- Rückgabetyp der 'import'-Methode soll Collection<> sein, die konkrete Collection-Art (List/Set/...) bleibt also unbestimmt
- in dieser 'import'-Methode soll die Datei Zeile für Zeile eingelesen, über den Protoyp eine entsprechende Instanz je Zeile erzeugt, in einer Collection gesammelt und retourniert werden
- am einfachsten wird die Befüllung der Instanzvariablen des neuen Objekts wohl über eine Methode der Prototyp-Klasse gehen, der ein String-Array übergeben wird
- in dieser Methode muss nun das für den konkreten Typ (Adventure, Album, ...) typische Umwandeln von Texten in andere Datentypen (LocalDate, Double, ...) erfolgen
  

- optional: die export-Methode bildet den umgekehrten Weg ab und erhält den Dateiname, das Trennzeichen als (RegEx-)String, ein Prototyp-Objekt sowie eine Collection von zu speichernden Objekten
- hilfreich dabei ist eine Methode der Protoyp-Klasse, die einen mit Trennzeichen versehenen String der Inhalte der Instanzvariablen liefert
## Tip:
- das Verschlüsseln und Base64-Codieren kann der Klasse util.TextEncoder entnommen werden (Bsp. für keyString: bPeShVmYq3t6w9y$).
- das Entschlüsseln wird entsprechend umgekehrt zu realisieren sein, also zuerst Base64.decode des Chiffrats gefolgt von doFinal zum ermittelten Byte-Array
- dazu wird ein Decodierungs-Cipher-Objekt benötigt, das mit 'Cipher.DECRYPT_MODE' erzeugt werden muss
- ```singltn```, ```factMeth```, ```abstFact``` und ```prototp```
