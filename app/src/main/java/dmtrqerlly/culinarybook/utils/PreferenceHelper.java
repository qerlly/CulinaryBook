package dmtrqerlly.culinarybook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dmtrqerlly.culinarybook.R;

public class PreferenceHelper {

    public static final String CHECK_UPDATE = "checkboxPref";
    public static final String THEMES = "Themes";

    public void themeSwitch(final Context pContext) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(pContext.getApplicationContext());
        final String themes = preferences.getString(THEMES, "1");

        switch (themes) {
            case "1":
                pContext.setTheme(R.style.AppTheme);
                break;
            case "2":
                pContext.setTheme(R.style.GreyTheme);
                break;
            case "3":
                pContext.setTheme(R.style.BlueGreyTheme);
                break;
            case "4":
                pContext.setTheme(R.style.DeepOrangeTheme);
                break;
            case "5":
                pContext.setTheme(R.style.BrownTheme);
                break;
            case "6":
                pContext.setTheme(R.style.LightOrangeTheme);
                break;
            case "7":
                pContext.setTheme(R.style.YellowTheme);
                break;
            case "8":
                pContext.setTheme(R.style.GreenTheme);
                break;
            case "9":
                pContext.setTheme(R.style.GreenLightTheme);
                break;
            case "10":
                pContext.setTheme(R.style.AmberTheme);
                break;
            case "11":
                pContext.setTheme(R.style.TealTheme);
                break;
            case "12":
                pContext.setTheme(R.style.LightBlueTheme);
                break;
            case "13":
                pContext.setTheme(R.style.BlueTheme);
                break;
            case "14":
                pContext.setTheme(R.style.IndigoTheme);
                break;
            case "15":
                pContext.setTheme(R.style.DeepPurpleTheme);
                break;
            case "16":
                pContext.setTheme(R.style.PurpleTheme);
                break;
            case "17":
                pContext.setTheme(R.style.PinkTheme);
                break;
            case "18":
                pContext.setTheme(R.style.RedTheme);
                break;
        }
    }
}