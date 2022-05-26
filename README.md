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
![Diagram](https://github.com/RehakFrantisek/2122ALG2-Rehak-BettingApp/blob/main/BetApp-SimpleDiagram.png)

Projekt **Sazkoveho simulatoru**, který umožňuje přihlášenému 
uživateli vsadit na sportovní událost, která bude vytvořená 
administrátorem (bookmakerem). V navrženém rozhraní bude mít uživatel
možnost sázet, kontrolovat svoje výsledky, vkládat a vybírat peníze
mezi virtuální peněženkou a bankovním účtem (platební kartou).

Aplikace bude umožňovat přihlášení dvojího typu: </br>
    - uživatelské </br>
    - administrátorské

Po přihlášení uživatele se objeví rozhraní, 
kde bude mít možnost procházet historii ticketů a jejich 
výsledek nebo vytvářet sázky nové na budoucí utkání. </br>
Administrátor bude mít po přihlášení možnost stejně tak 
procházet historii sázek a jejich výsledky a přidávat utkání nová
s předem zvoleným (náhodně vygenerovaným kurzem). </br>
Oba typy profilů, budou mít možnost se odhlásit a znovu přihlásit.
Nový uživatel bude mít možnost registrace, přidat nového administrátora není možné.</br>

Možné akce uživatele po přihlášení:
- vklad peněz
- sazka
- historie
- list budoucich zapasu
- napoveda ?

(po hodině bych rád ještě zadání zkonzultoval)
