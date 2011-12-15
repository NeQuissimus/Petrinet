package com.nequissimus.library.os;

import java.awt.Image;
import java.lang.reflect.Method;
import java.net.URL;

import javax.swing.ImageIcon;

import com.apple.eawt.Application;

/**
 * This is a class that provides methods which use the OSX Dock.app.
 * @author Tim Steinbach
 */
@SuppressWarnings("restriction")
public final class MacDockIcon {

    /**
     * Hide constructor.
     */
    private MacDockIcon() {
    }

    /**
     * Set a dock icon for this application.
     * @param icon Icon
     * @return True if icon was set
     */
    public static boolean setIcon(final Image icon) {

        boolean iconSet = false;

        try {

            final Application app =
                com.apple.eawt.Application.getApplication();

            final Method method =
                app.getClass().getMethod("setDockIconImage", Image.class);

            method.invoke(app, icon);

            iconSet = true;

        } catch (final Exception e) {

            return false;

        }

        return iconSet;

    }

    /**
     * Set a dock icon for this application.
     * @param path Icon
     * @return True if icon was set
     */
    public static boolean setIcon(final String path) {

        final ImageIcon icon = new ImageIcon(path);
        final Image image = icon.getImage();

        return MacDockIcon.setIcon(image);

    }

    /**
     * Set a dock icon for this application.
     * @param path Icon
     * @return True if icon was set
     */
    public static boolean setIcon(final URL path) {

        final ImageIcon icon = new ImageIcon(path);
        final Image image = icon.getImage();

        return MacDockIcon.setIcon(image);

    }

}
