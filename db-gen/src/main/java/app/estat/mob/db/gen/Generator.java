package app.estat.mob.db.gen;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Generator {

    public static void main(String[] args) throws Exception {
        Schema schema = getDefaultSchema();

        Entity cowEntity = getCowEntity(schema);
        Entity cowParentEntity = getCowParentEntity(schema);

        Property cowParentId = cowEntity.addLongProperty(Constant.PARENT_COLUMN).getProperty();
        cowEntity.addToOne(cowParentEntity, cowParentId);
        cowParentEntity.addToMany(cowEntity, cowParentId).setName(Constant.PARENT_COWS);

        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "./db/src/main/java");
    }

    private static Schema getDefaultSchema() {
        Schema schema = new Schema(Constant.SCHEMA, Constant.DEFAULT_PACKAGE);

        schema.setDefaultJavaPackageDao(Constant.DEFAULT_DAO_PACKAGE);

        return schema;
    }

    private static Entity getCowParentEntity(Schema schema) {
        Entity cowParentEntity = schema.addEntity(Constant.COW_PARENT_ENTITY);

        cowParentEntity.addIdProperty();
        cowParentEntity.addStringProperty(Constant.NAME_COLUMN);
        cowParentEntity.addStringProperty(Constant.NUMBER_COLUMN);

        return cowParentEntity;
    }

    private static Entity getCowEntity(Schema schema) {
        Entity cowEntity = schema.addEntity(Constant.COW_ENTITY);

        cowEntity.addIdProperty();
        cowEntity.addStringProperty(Constant.NAME_COLUMN);
        cowEntity.addStringProperty(Constant.NUMBER_COLUMN);
        cowEntity.addIntProperty(Constant.BOOK_COLUMN);
        cowEntity.addStringProperty(Constant.BIRTH_COLUMN);

        return cowEntity;
    }

}
