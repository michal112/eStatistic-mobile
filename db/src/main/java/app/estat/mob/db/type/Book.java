package app.estat.mob.db.type;

public enum Book implements FormSpinnerItem {
    MAIN("R.string.main_book_cow"),
    INTRODUCTORY("R.string.introductory_book_cow"),
    UNKNOWN("R.string.unknown_book_cow");

    private String mTitle;

    Book(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }
}