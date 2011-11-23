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
