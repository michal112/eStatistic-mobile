package app.estat.mob.db;

public enum CowBook {

    MAIN(1),
    INTRODUCTORY(2);

    private final int value;

    CowBook(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
