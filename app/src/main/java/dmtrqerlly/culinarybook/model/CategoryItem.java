package dmtrqerlly.culinarybook.model;

public class CategoryItem {

    private int mId;
    private String mCategory;
    private String mIcon;

    public CategoryItem(final int pId, final String pCategory, final String pIcon) {
        mId = pId;
        mCategory = pCategory;
        mIcon = pIcon;
    }

    public int getId() {
        return mId;
    }

    public void setId(final int pId) {
        mId = pId;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(final String pCategory) {
        mCategory = pCategory;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(final String pIcon) {
        mIcon = pIcon;
    }
}
