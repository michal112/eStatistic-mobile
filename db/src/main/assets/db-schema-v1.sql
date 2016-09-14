DROP TABLE IF EXISTS MODULE;

CREATE TABLE MODULE(
    ID INTEGER PRIMARY KEY,
    NAME TEXT,
    ICON TEXT,
    DESCRIPTION TEXT,
    ACTIVITY TEXT
);

INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.farm_card_name", "R.drawable.ic_farm_card", "R.string.farm_card_description", "FARM_CARD");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.average_productivity_name", "R.drawable.ic_average_productivity", "R.string.average_productivity_description", "AVERAGE_PRODUCTIVITY");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.my_cows_name", "R.drawable.ic_my_cows", "R.string.my_cows_description", "MY_COWS");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION, ACTIVITY) VALUES (
    null, "R.string.milk_production", "R.drawable.ic_milk_production", "R.string.milk_production_description", "MILK_PRODUCTION");