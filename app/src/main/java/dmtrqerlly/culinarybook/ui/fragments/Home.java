package dmtrqerlly.culinarybook.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.ui.activities.SettingsActivity;

public class Home extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.home_fragment, pContainer, false);

        view.findViewById(R.id.button_card_one_first).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {

                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.animation_fragments, 0);
                ft.replace(R.id.main_fragment, new Category());
                ft.commit();
            }
        });

        view.findViewById(R.id.button_card_one_two).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.animation_fragments, 0);
                ft.replace(R.id.main_fragment, new Favorite());
                ft.commit();
            }
        });

        view.findViewById(R.id.button_card_two).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.animation_fragments, 0);
                ft.replace(R.id.main_fragment, new Random());
                ft.commit();
            }
        });

        view.findViewById(R.id.button_card_three).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        return view;
    }
}
