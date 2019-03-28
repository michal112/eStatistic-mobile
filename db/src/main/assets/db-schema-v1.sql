DROP TABLE IF EXISTS MODULE;
DROP TABLE IF EXISTS COW;
DROP TABLE IF EXISTS BULL;
DROP TABLE IF EXISTS MATE;
DROP TABLE IF EXISTS LACTATION;

CREATE TABLE MODULE(
    ID INTEGER PRIMARY KEY,
    NAME TEXT,
    ICON TEXT,
    DESCRIPTION TEXT,
    ACTIVITY TEXT
);

CREATE TABLE COW(
    ID INTEGER PRIMARY KEY,
    PUBLIC_ID TEXT,
    NAME TEXT,
    NUMBER TEXT,
    BOOK TEXT,
    BULL_ID INTEGER,
    BIRTHDAY TEXT
);

CREATE TABLE BULL(
    ID INTEGER PRIMARY KEY,
    PUBLIC_ID TEXT,
    NAME TEXT,
    NUMBER TEXT
);

CREATE TABLE MATE(
    ID INTEGER PRIMARY KEY,
    PUBLIC_ID TEXT,
    DATE TEXT,
    COW_ID INTEGER,
    BULL_ID INTEGER
);

CREATE TABLE LACTATION(
    ID INTEGER PRIMARY KEY,
    PUBLIC_ID TEXT,
    DATE TEXT,
    COW_ID INTEGER
);

INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.farm_card_name", "R.drawable.ic_farm_card", "R.string.farm_card_description", "FARM_CARD");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.average_productivity_name", "R.drawable.ic_average_productivity", "R.string.average_productivity_description", "AVERAGE_PRODUCTIVITY");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.my_cows_name", "R.drawable.ic_my_cows", "R.string.my_cows_description", "MY_COWS");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.my_bulls_name", "R.drawable.ic_my_bulls", "R.string.my_bulls_description", "MY_BULLS");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.milk_production", "R.drawable.ic_milk_production", "R.string.milk_production_description", "MILK_PRODUCTION");