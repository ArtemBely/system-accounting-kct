
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

## Generation of uuid by adding new record. Need to have extension in pgadmin:
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
## Check if it works
SELECT uuid_generate_v4();

## Create table system_accounting_kct from pgadmin

-- Table: public.system_accounting_kct

-- DROP TABLE IF EXISTS public.system_accounting_kct;

CREATE TABLE IF NOT EXISTS public.system_accounting_kct
(
id uuid DEFAULT 'uuid_generate_v4()',
date date NOT NULL,
type_of_item integer NOT NULL,
hours numeric(3,1) NOT NULL,
project_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
description character varying(200) COLLATE pg_catalog."default" NOT NULL,
kind character varying(30) COLLATE pg_catalog."default",
dzc_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT system_accounting_kct_dzc_id_fkey FOREIGN KEY (dzc_id)
REFERENCES public.dzc (dzc) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

## Create table dzc from pgadmin

-- Table: public.dzc

-- DROP TABLE IF EXISTS public.dzc;

CREATE TABLE IF NOT EXISTS public.dzc
(
dzc character varying(10) COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT dzc_pkey PRIMARY KEY (dzc)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dzc
OWNER to postgres;