package dmtrqerlly.culinarybook.db;

public interface IDatabaseConst {

    int VERSION = 1;
    String DB_NAME = "dbCulinaryBook";
    String TABLE = "culinary_data";
    String TABLE_FAV = "culinary_data_fav";
    String TABLE_CATEGORY = "culinary_category";
    String NAME = "NAME";
    String DETAILS = "INGREDIENTS";
    String RECIPE = "RECIPE";
    String IMAGE = "IMAGE";
    String TYPE = "TYPE";
    String ID = "_id";
    String ID_FAV = "favorite";
    String CATEGORY_NAME = "CATEGORY_NAME";
    String ICON = "ICON";
    String SHOW_ALL = "select * from " + TABLE;
    String SHOW_CATEGORIES = "select * from " + TABLE_CATEGORY;
    String SHOW_RANDOM = "select * from " + TABLE + " order by random() limit 1";
    String SHOW_FAVORITES = "select * from " + TABLE + " where " + ID + " in (select " + ID_FAV + " from " + TABLE_FAV + ")";
    String SHOW_DETAIL = "select * from " + TABLE + " where " + ID + " = ";
    String SHOW_ALCOHOL = "select * from " + TABLE + " where " + TYPE + " = 1";
    String SHOW_ALCOHOL_FAV = "select * from " + TABLE + " where " + ID + " in (select " + ID_FAV + " from " + TABLE_FAV + ") and TYPE = 1";
    String SHOW_NON_ALCOHOL = "select * from " + TABLE + " where " + TYPE + " = 0";
    String SHOW_NON_ALCOHOL_FAV = "select * from " + TABLE + " where " + ID + " in (select " + ID_FAV + " from " + TABLE_FAV + ") and TYPE = 0";
    String CHECK_FAVORITE = "select * from " + TABLE_FAV + " where " + ID_FAV + " = ";
}