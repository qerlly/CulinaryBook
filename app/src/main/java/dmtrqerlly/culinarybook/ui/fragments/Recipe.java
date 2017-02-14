package dmtrqerlly.culinarybook.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dmtrqerlly.culinarybook.R;

public class Recipe extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.recipe_fragment, pContainer, false);

        final ImageView imageView = (ImageView)view.findViewById(R.id.detail_image);
        final RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.detail_name_holder);
        final TextView nameText = (TextView) view.findViewById(R.id.detail_name);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.button_favorite);
        final TextView ingredientText = (TextView) view.findViewById(R.id.detail_ingredients);
        final TextView recipeText = (TextView) view.findViewById(R.id.detail_recipe);

        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {

            }
        });
        return view;
    }
}