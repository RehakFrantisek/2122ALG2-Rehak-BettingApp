# 2022ALG2Semestral-Rehak-BettingApp

## Zadání práce
Program slouží k simulaci sázení na fotbalová a hokejová utkání. Uživatel má po spuštění 3 možnosti, přihlásit se, registrovat a aplikaci ukončit. Pokud je uživatel nový a není tedy registrován, je vyžadována registrace. Registrace je možná pomocí jména, hesla, PID, čísla karty a CVC kódu. Podle vložení čísla karty je uživateli nahráno množství peněz, které se na kartě nachází. Poté může uživatel začít sázen na jednotlivé zápasy. Může si vybrat tým na který vsadí, může si vybrat volbu (prohra, výhra, remíza) a počet peněz, které vsadí. Vsazené peníze mu jsou po vsazení odečteny a po vyhodnocení sázky (v případě výhry) přičteny. Probíhající sázky a historie sázek je ukládána do osobního souboru a aktuální stav ukládán do PDF.

## Návrh řešení
* Hlavní menu
    * Vytvoření nového účtu
    * Přihlášení k existujícímu účtu
    * Exit
* Vytvoření nového účtu
    * Jméno
    * Heslo
    * PID
    * číslo karty
    * CVC
* Přihlášení
    * Nový ticket
        * Vybrat zápas
        * Vybrat volbu
        * Vsazené peníze
    * Aktuální tickety
    * Historie sázek
    * Historie dle vsazených peněz
    * Odhlásit
* Exit
   * Ano
   * Zpět do menu

## Zjednodušený class diagram
![Simple-Diagram](https://github.com/RehakFrantisek/2122ALG2-Rehak-BettingApp/blob/main/BetApp-SimpleDiagram.png)

## Zjednodušený class diagram
![Diagram](https://github.com/RehakFrantisek/2122ALG2-Rehak-BettingApp/blob/main/BettingApp-dia.png)

# Použitá externí knihovna
[itextPdf](https://github.com/itext/itextpdf)
Knihovna iText pro práci s PDF. Umožňuje vytvořit, zapsat a přečíst data z .pdf formátu.

## Práce s knihovnou
Přidání itextpdf.jar do libraries (manuálně - použil jsem plugin Maven v IntelliJ IDEA)

Přihlášenému uživateli se vygeneruje PDF s aktuální historií tiketů s jejich tipem a vsazenou částkou (vyhodnocených / nevyhodnocených) 
```java
Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream("data//"+username+"//statistics.pdf"));

document.open();
document.add(new Paragraph("Username: " + username));
document.add(new Paragraph(" "));
document.add(new Paragraph(tickets.toString()));
document.close();
```

# Autor
Autorem projektu je František Řehák pro předmět ALG2.
