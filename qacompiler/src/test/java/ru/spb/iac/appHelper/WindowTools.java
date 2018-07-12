package ru.spb.iac.appHelper;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public class WindowTools {

    public static int[] getRect(String windowName) throws CryptoPro.WindowNotFoundException {
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        if (hwnd == null) {
            throw new CryptoPro.WindowNotFoundException("", windowName);
        }

        int[] rect = {0, 0, 0, 0};
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        return rect;
    }

    private interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
                W32APIOptions.DEFAULT_OPTIONS);
        WinDef.HWND FindWindow(String lpClassName, String lpWindowName);
        void GetWindowRect(WinDef.HWND handle, int[] rect);
    }
}
