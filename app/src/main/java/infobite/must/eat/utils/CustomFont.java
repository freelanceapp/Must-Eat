package infobite.must.eat.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Dell on 12/4/2018.
 */

public class CustomFont {

    public Typeface semiBold(Context mContext) {
        Typeface titleFont = Typeface.createFromAsset(mContext.getAssets(), "font/Raleway-SemiBold.ttf");
        return titleFont;
    }

}