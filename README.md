# 2022ALG2Semestral-Rehak-BettingApp

## Zadání práce
Program slouží k simulaci sázení na fotbalová a hokejová utkání. Uživatel má po spuštění 3 možnosti, přihlásit se, registrovat a aplikaci ukončit. Pokud je uživatel nový a není tedy registrován, je vyžadována registrace. Registrace je možná pomocí jména, hesla, PID, čísla karty a CVC kódu. Podle vložení čísla karty je uživateli nahráno množství peněz, které se na kartě nachází. Poté může uživatel začít sázen na jednotlivé zápasy. Může si vybrat tým na který vsadí, může si vybrat volbu (prohra, výhra, remíza) a počet peněz, které vsadí. Vsazené peníze mu jsou po vsazení odečteny a po vyhodnocení sázky (v případě výhry) přičteny. Probíhající sázky a historie sázek je ukládána do osobního souboru a aktuální stav ukládán do PDF.

## Návrh řešení
* Hlavní menu
    * Vytvořit nový účet
    * Přihlásit k existujícímu účtu
    * Exit
* Vytvořit nový účet
    * Jméno
    * Heslo
    * PID
    * číslo karty
    * CVC
* Přihlášení
    * Nový ticket
        * Vybrat zápas
        * Vybrat volbu
        * Vsadit peníze
    * Aktivní ticket
    * Historie ticketů
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
Knihovna iText pro práci s PDF. Umožňuje vytvořit, zapsat a přečíst data z '.pdf' formátu.
V rámci vytváření dokumentu a zápisu dat do něj lze formátovat jak celý dokument, tak také jeho prvky (barva, font, velikost,..).

## Práce s knihovnou
Přidání itextpdf.jar do libraries (manuálně - použil jsem plugin Maven v IntelliJ IDEA).

Přihlášenému uživateli se vygeneruje PDF s aktuální historií tiketů s jejich tipem a vsazenou částkou (vyhodnocených / nevyhodnocených).

### Ukázka kódu
```java
Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream("data//"+username+"//statistics.pdf"));

document.open();
document.add(new Paragraph("Username: " + username));
document.add(new Paragraph(" "));
document.add(new Paragraph(tickets.toString()));
document.close();
```
### Formátování
```java
document.setMargins(20, 20, 20, 20);

PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1})).useAllAvailableWidth();
```
# Autor
Autorem práce je František Řehák pro předmět ALG2.
