package dmtrqerlly.culinarybook.model;

public class Item {

    private int mId;
    private String mImage;
    private String mName;
    private final String mDetails;
    private final String mRecipe;
    private final int mType;

    public Item(final int pId, final String pImage, final String pName, final String pDetails, final String pRecipe, final int pType){
        mId = pId;
        mImage = pImage;
        mName = pName;
        mDetails = pDetails;
        mRecipe = pRecipe;
        mType = pType;
    }

    public int getId() {
        return mId;
    }

    public void setId(final int pId) {
        mId = pId;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(final String pImage) {
        mImage = pImage;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getRecipe() {
        return mRecipe;
    }

}