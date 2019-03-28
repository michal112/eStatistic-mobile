package app.estat.mob.mvp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.type.Book;
import app.estat.mob.db.type.FormSpinnerItem;

public class CowData implements Serializable {

    private String mPublicId;

    private String mName;

    private String mNumber;

    private Book mBook;

    private Date mBirthday;

    public static final class CowDataBuilder {
        private String mPublicId;
        private String mName;
        private String mNumber;
        private Book mBook;
        private Date mBirthday;

        public CowDataBuilder withName(String name) {
            this.mName = name;
            return this;
        }

        public CowDataBuilder withNumber(String number) {
            this.mNumber = number;
            return this;
        }

        public CowDataBuilder withBook(FormSpinnerItem item) {
            if (item instanceof Book) {
                this.mBook = (Book) item;
            }
            return this;
        }

        public CowDataBuilder withBirthday(Date birthday) {
            this.mBirthday = birthday;
            return this;
        }

        public CowDataBuilder withPublicId(String publicId) {
            this.mPublicId = publicId;
            return this;
        }

        public CowData build() {
            CowData cowData = new CowData();
            cowData.mBirthday = this.mBirthday;
            cowData.mName = this.mName;
            cowData.mBook = this.mBook;
            cowData.mNumber = this.mNumber;
            cowData.mPublicId = this.mPublicId;
            return cowData;
        }
    }

    public String getPublicId() {
        return mPublicId;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public Book getBook() {
        return mBook;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public String getBirthdayAsString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(mBirthday);
    }

    public static CowData from(Cow cow) {
        return new CowDataBuilder()
                .withName(cow.getName())
                .withNumber(cow.getNumber())
                .withBook(cow.getBook())
                .withBirthday(cow.getBirthday())
                .withPublicId(cow.getPublicId())
                .build();
    }

    public Cow getCow() {
        Cow cow = new Cow();
        cow.setBirthday(mBirthday);
        cow.setName(mName);
        cow.setBook(mBook);
        cow.setNumber(mNumber);
        cow.setPublicId(mPublicId);
        return cow;
    }
}
