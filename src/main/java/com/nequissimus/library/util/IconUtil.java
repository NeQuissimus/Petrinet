package com.nequissimus.library.util;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class that works on {@link java.swing.Icon}.
 * @author Tim Steinbach
 */
public final class IconUtil {

    /**
     * Hide constructor.
     */
    private IconUtil() {
    }

    /**
     * Get an icon of an image with a given size.
     * @param path Path to image
     * @param size Icon size
     * @return Icon with given size
     */
    public static Icon getIcon(final String path, final Dimension size) {

        ParamUtil.checkNotNull(path);
        ParamUtil.checkNotNull(size);

        final ImageIcon icon = new ImageIcon(path);

        if (null != icon) {
            final Image image = icon.getImage();
            final Image scaled =
                image.getScaledInstance(size.width, size.height,
                    Image.SCALE_SMOOTH);
            icon.setImage(scaled);
        }

        return icon;

    }

}
