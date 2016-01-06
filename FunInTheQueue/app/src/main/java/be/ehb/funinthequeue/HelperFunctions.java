package be.ehb.funinthequeue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import be.ehb.funinthequeue.model.User;

/**
 * Created by Dieter on 6/01/2016.
 */
public class HelperFunctions {
    // Source: http://stackoverflow.com/a/7882950/2637528
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static boolean isNotEmpty(CharSequence target) {
        return !TextUtils.isEmpty(target);
    }

    public final static void storeUserInPreferences(Activity context, User user) {
        SharedPreferences sharedPref = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("user_id", user.getId());
        editor.putInt("avatar_id", user.getAvatarId());
        editor.putInt("balance", user.getBalance());
        editor.putString("first_name", user.getFname());
        editor.putString("last_name", user.getLname());
        editor.putString("email", user.getMail());

        editor.commit();
    }

    public final static User loadUserFromPreferences(Activity context) {
        SharedPreferences sharedPref = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        return new User(
                sharedPref.getInt("user_id", 0),
                sharedPref.getInt("avatar_id", 0),
                sharedPref.getInt("balance", 0),
                sharedPref.getString("first_name", ""),
                sharedPref.getString("last_name", ""),
                sharedPref.getString("email", "")
        );
    }

    public final static void removeUserPreferences(Activity context) {
        SharedPreferences sharedPref = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }
}
