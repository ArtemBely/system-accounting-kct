
Základní proces je v tuto chvíli tento tento a tool, který používáme je na zpracování dat z JIRA:



Lidé vykáží hodiny do JIRA tasků na portále Škodovky.
Exportují v JIRA data za celý měsíc – viz. „exportdohnalek.xls“
Následně je potřeba tento excel zpracovat do formátu, aby bylo možné importovat do KCT TS - https://nasportal.kctdata.cz/irj/portal

Podmínky pro zpracování excelu:

Správný formát tabulky pro upload do TS (viz. Filipův Excel) – pro TS relevantní: Datum, Typ položky, Zakázka, Počet hodin, Popis

Každý ticket musí být přiřazený na správnou položku v TS, položky v TS jsou:
Paušál FIB AMS (definováno typem ticketu, číslo zakázky vždy stejné + nové číslo položky každý měsíc)

Fakturace FIB AMS (definováno typem ticketu, číslo zakázky vždy stejné + nové číslo položky každý měsíc)

Fakturace Samostatné zakázky (existuje seznam ticketů (epiců) které jsou hrazeny ze samostatného budgetu a ty mají přidělenou vlastní zakázku v SAP, také každý měsíc nové číslo položky, ale stejné číslo zakázky)




Možnost přidání řádků do TS manuálně pro typy položek, které nejsou v JIRA exportu (v jira exportu by měl být pouze typ „Fakturovaný den 0800“), například pohotovosti – ve Filipa excelu k tomuto slouží list „Input_manual“
@Dohnálek Filip: Pohotovosti jsou jediné typy položek, které vám tool zpracovává, souhlasí to? Dovolené a ostatní si upravujete manuálně v TS předpokládám.


Notes: 

You can switch between using these workbooks in case you wanna work with certain file format (excel):
XSSFWorkbook (xls) / HSSFWorkbook (xlsx)
HSSF (Horrible SpreadSheet Format) Implementation: It denotes an API that is working with Excel 2003 or earlier versions.
XSSF (XML SpreadSheet Format) Implementation: It denotes an API that is working with Excel 2007 or later versions.

Or just use universal Workbook object (viz ExcelUtility / ExcelService)