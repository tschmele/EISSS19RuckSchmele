/*******************************************************************************
  input von anzeige an /matching/anzeige :
  new : {
    id : oabvr7ösid25nvn
    data : {
      anfrage : true/false
      standort : [latitude, longitude]
      tags : [
        "fleisch",
        "schwein",
        "rind",
        "huhn",
        "fisch",
        "obst",
        "gemüse",
        "zitrus",
        "milch",
        "laktosefrei",
        "glutenfree"
      ]
    }
  }

  mögliche benutzer :
  user : {
    id : oap2738bwdsjbqo
    data : {
      standort : [lantitude, longitude],
      bewertung : 0-5,
      tags : [
        "vegetarier",
        "veganer",
        "laktoseintolerant",
        "stirbt an gluten",
        "zitrusallergie"
      ]
    }
  }

  > tags der neuen anzeige an "Benutzerverwaltung" schicken
  > rückmeldung enthält nur benutzer die mit tags passen
  > benutzer nach standort filtern, alle >20km weg fliegen raus
  > wenn mehr als 1 rest vorhanden:
    > benutzer anhand wertung sortieren
    > max 5 höchste behalten
    > person mit meisten kommentaren wählen
      > bei gleichstand person ohne beschreibung ausschließen
      > immernoch mehrere? alle benachrichtigen
      > first come first serve
  > wenn mehr als 15 rest vorhanden:
    > benutzer anhand wertung sortieren
    > nur mit höchstem ergebnis behalten
    > person mit meisten kommentaren wählen
      > bei gleichstand person ohne beschreibung ausschließen
      > immernoch mehrere? alle benachrichtigen
      > first come first serve
  > wenn nur 1 rest vorhanden:
    > benachrichtigen
  > wenn kein rest vorhanden:
    > schade. kein realisitscher abnehmer vorhanden

  For Future Improvement:
    > Lager miteinbeziehen
    > Bei Anfrage Personen mit gewünschtem Artikel bevorzugen
    > Bei Angebot Personen mit gewünschtem Artikel hinten anstellen

*******************************************************************************/
