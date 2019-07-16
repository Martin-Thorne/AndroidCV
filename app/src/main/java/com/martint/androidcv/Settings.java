package com.martint.androidcv;

/**
 * Helper class that handles user settings changes
 */
public class Settings {

    private static int textSize = R.integer.text_size_medium;
    private static boolean darkModeEnabled = false;

    private Settings() {
    }

    /**
     * @return current text size
     */
    public static int getTextSize() {
        return textSize;
    }

    /**
     * Sets the text size after user selects increase/decrease from popup menu
     *
     * @param sizeSelection
     */
    public static void setTextSize(int sizeSelection) {
        if (sizeSelection == R.id.increase_text_size) {
            switch (textSize) {
                case R.integer.text_size_small:
                    textSize = R.integer.text_size_medium;
                    break;
                case R.integer.text_size_medium:
                    textSize = R.integer.text_size_large;
                    break;
                default:
                    break;
            }
        } else {
            switch (textSize) {
                case R.integer.text_size_large:
                    textSize = R.integer.text_size_medium;
                    break;
                case R.integer.text_size_medium:
                    textSize = R.integer.text_size_small;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @return if dark mode is enabled
     */
    public static boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    /**
     * Sets the state of dark mode
     *
     * @param darkModeEnabled
     */
    public static void setDarkModeEnabled(boolean darkModeEnabled) {
        Settings.darkModeEnabled = darkModeEnabled;
    }
}
