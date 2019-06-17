package com.martint.androidcv;

/**
 * Helper class that handles user settings changes
 */
public class Settings {

    private static int textSize = 16;

    public static int getTextSize() {
        return textSize;
    }

    public static void setTextSize(int sizeSelection) {
        if (sizeSelection == R.id.increase_text_size) {
            switch (textSize) {
                case 14:
                    textSize = 16;
                    break;
                case 16:
                    textSize = 20;
                    break;
                default:
                    break;
            }
        } else {
            switch (textSize) {
                case 20:
                    textSize = 16;
                    break;
                case 16:
                    textSize = 14;
                    break;
                default:
                    break;
            }
        }
    }
}
