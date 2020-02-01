package app.estat.mob.mvp.model;

import app.estat.mob.db.entity.Bull;

public class BullData {

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

    public Bull getBull() {
        Bull bull = new Bull();
        bull.setName(mName);
        bull.setNumber(mNumber);
        bull.setPublicId(mPublicId);
        return bull;
    }
}
