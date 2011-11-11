package com.nequissimus.library.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.nequissimus.library.converter.ArrayCollection;

/**
 * A file filter that uses a file's extension to match.
 * @author: Tim Steinbach
 */
public class ExtensionFileFilter extends javax.swing.filechooser.FileFilter
    implements FileFilter {

    /**
     * List of file extensions.
     */
    private final List<String> extensions;

    /**
     * Description of extensions.
     */
    private String description = null;

    /**
     * Create a file filter with a given file extension.
     * @param extension file extension
     */
    public ExtensionFileFilter(final String extension) {

        this.extensions = new ArrayList<String>();
        this.extensions.add(extension);

    }

    /**
     * Create a file filter that allows several file extensions.
     * @param extensions list of extensions
     */
    public ExtensionFileFilter(final List<String> extensions) {

        this.extensions = extensions;

    }

    /**
     * Create a file filter that allows several file extensions.
     * @param extensions array of extensions
     */
    public ExtensionFileFilter(final String[] extensions) {

        final ArrayCollection<String> col = new ArrayCollection<String>();
        this.extensions = col.toList(extensions);

    }

    @Override
    public final boolean accept(final File file) {

        if ((file == null) || (!file.isFile())) {
            return false;
        }

        boolean matches = false;

        final String name = file.getName();

        for (final String extension : this.extensions) {

            if ((name != null) && (name.endsWith(extension))) {
                matches = true;
                break;
            }

        }

        return matches;

    }

    @Override
    public final String getDescription() {

        return this.description;

    }

    /**
     * Set a file description for the extensions used by this filter. E.g. if
     * using ".jpg", the description could say "JPEG images"
     * @param description Description to be used
     */
    public final void setDescription(final String description) {

        this.description = description;

    }

}
