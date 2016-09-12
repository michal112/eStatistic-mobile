DROP TABLE IF EXISTS MODULE;

CREATE TABLE MODULE(
    ID INTEGER PRIMARY KEY,
    NAME TEXT,
    ICON TEXT,
    DESCRIPTION TEXT
);

INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION) VALUES (
    1, "R.string.farm_card_name", "R.drawable.ic_farm_card", "R.string.farm_card_description");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION) VALUES (
    2, "R.string.average_productivity_name", "R.drawable.ic_average_productivity", "R.string.average_productivity_description");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION) VALUES (
    3, "R.string.my_cows_name", "R.drawable.ic_my_cows", "R.string.my_cows_description");
INSERT INTO MODULE(ID, NAME, ICON, DESCRIPTION) VALUES (
    4, "R.string.milk_production", "R.drawable.ic_milk_production", "R.string.milk_production_description");