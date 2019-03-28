package app.estat.mob.mvp.model;

import java.io.Serializable;

import app.estat.mob.db.entity.Bull;

public class BullData implements Serializable {

    private String mPublicId;

    private String mName;

    private String mNumber;

    public static final class BullDataBuilder {
        private String mPublicId;
        private String mName;
        private String mNumber;

        public BullDataBuilder withName(String name) {
            this.mName = name;
            return this;
        }

        public BullDataBuilder withNumber(String number) {
            this.mNumber = number;
            return this;
        }

        public BullDataBuilder withPublicId(String publicId) {
            this.mPublicId = publicId;
            return this;
        }

        public BullData build() {
            BullData bullData = new BullData();
            bullData.mName = this.mName;
            bullData.mNumber = this.mNumber;
            bullData.mPublicId = this.mPublicId;
            return bullData;
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

    public static BullData from(Bull bull) {
        return new BullDataBuilder()
                .withName(bull.getName())
                .withNumber(bull.getNumber())
                .withPublicId(bull.getPublicId())
                .build();
    }

    public Bull getBull() {
        Bull bull = new Bull();
        bull.setName(mName);
        bull.setNumber(mNumber);
        bull.setPublicId(mPublicId);
        return bull;
    }
}
