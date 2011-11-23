/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.nequissimus.library.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utility class for methods helping with XML.
 * @author Tim Steinbach
 */
public final class XmlUtil {

    /**
     * Hide constructor.
     */
    private XmlUtil() {
    }

    /**
     * Cleans an XML node from all its empty children.
     * @param node Root node to be examined recursively
     * @author Blaise Doughan - http://stackoverflow.com/q/3788279
     */
    public static void removeEmptyTextNodes(final Node node) {

        final NodeList nodeList = node.getChildNodes();
        Node childNode;

        for (int x = nodeList.getLength() - 1; x >= 0; x--) {

            childNode = nodeList.item(x);

            if (childNode.getNodeType() == Node.TEXT_NODE) {

                if (childNode.getNodeValue().trim().equals("")) {

                    node.removeChild(childNode);

                }

            } else if (childNode.getNodeType() == Node.ELEMENT_NODE) {

                XmlUtil.removeEmptyTextNodes(childNode);

            }

        }

    }

}
