package app.estat.mob.mvp.model;

public class FarmData {
    private String mUserName;

    private String mBarnNumber;

    private String mFarmAddress;

    private FarmData() {
    }

    public String getFarmAddress() {
        return mFarmAddress;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getBarnNumber() {
        return mBarnNumber;
    }

    public static class Builder {
        private String mUserName;

        private String mBarnNumber;

        private String mFarmAddress;

        public Builder userName(String userName) {
            mUserName = userName;
            return this;
        }

        public Builder barnNumber(String barnNumber) {
            mBarnNumber = barnNumber;
            return this;
        }

        public Builder farmAddress(String farmAddress) {
            mFarmAddress = farmAddress;
            return this;
        }

        public FarmData build() {
            FarmData farmData = new FarmData();

            farmData.mUserName = mUserName;
            farmData.mBarnNumber = mBarnNumber;
            farmData.mFarmAddress = mFarmAddress;

            return farmData;
        }
    }
}
