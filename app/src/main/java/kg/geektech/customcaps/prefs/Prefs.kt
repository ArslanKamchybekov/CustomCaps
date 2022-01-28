package kg.geektech.customcaps.prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var stringName: String?
        get() = preferences.getString("profileName", null)
        set(s) {
            preferences.edit().putString("profileName", s).apply()
        }

    var stringNumber: String?
        get() = preferences.getString("profileNumber", null)
        set(s) {
            preferences.edit().putString("profileNumber", s).apply()
        }

    var stringEmail: String?
        get() = preferences.getString("profileEmail", null)
        set(s) {
            preferences.edit().putString("profileEmail", s).apply()
        }

    var stringLocation: String?
        get() = preferences.getString("profileLocation", null)
        set(s) {
            preferences.edit().putString("profileLocation", s).apply()
        }

    var stringImg: String?
        get() = preferences.getString("profileImg", null)
        set(s) {
            preferences.edit().putString("profileImg", s).apply()
        }
}