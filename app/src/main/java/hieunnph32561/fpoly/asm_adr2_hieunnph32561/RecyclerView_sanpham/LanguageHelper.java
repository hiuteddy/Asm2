package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LanguageHelper {
    private static final String LANG_PREF_KEY = "language_pref";

    public static void setLanguage(Context context, String languageCode) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(LANG_PREF_KEY, languageCode).apply();
        updateLanguage(context, languageCode);
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(LANG_PREF_KEY, "en"); // Giá trị mặc định là Tiếng Anh
    }

    public static void updateLanguage(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
