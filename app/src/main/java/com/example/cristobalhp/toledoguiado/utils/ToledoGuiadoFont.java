package com.example.cristobalhp.toledoguiado.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public enum ToledoGuiadoFont {

    QUICKSAND_BOLD, QUICKSAND_LIGHT, QUICKSAND_REGULAR;

    public static Typeface getFont(AssetManager assetManager, ToledoGuiadoFont font) {
        String fontPath = getFontPath(font);

        Typeface typeface = _toledoguiadoFonts.get(font);

        if (typeface != null) {
            return typeface;
        }

        typeface = Typeface.createFromAsset(assetManager, fontPath);

        _toledoguiadoFonts.put(font, typeface);

        return typeface;
    }
    
    @NonNull
    private static String getFontPath(ToledoGuiadoFont font) {
        switch (font) {
            case QUICKSAND_BOLD:
                return "fonts/Lato-Bold.ttf";
            case QUICKSAND_LIGHT:
                return "fonts/Lato-Light.ttf";
            case QUICKSAND_REGULAR:
                return "fonts/Lato-Regular.ttf";
            default:
                return "fonts/Lato-Regular.ttf";
        }
    }

    private static Map<ToledoGuiadoFont, Typeface> _toledoguiadoFonts = new ConcurrentHashMap<>();

}