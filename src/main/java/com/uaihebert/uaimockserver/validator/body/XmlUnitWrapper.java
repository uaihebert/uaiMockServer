package com.uaihebert.uaimockserver.validator.body;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Will compare XML. For now, the default is always to ignore white space, otherwise the examples below would not
 * be equals:
 * &lt;name&gt;JC&lt;/name&gt;
 * compared to
 * &lt;name&gt;
 *     JC
 * &lt;/name&gt;
 */
public final class XmlUnitWrapper {
    private XmlUnitWrapper() {
    }

    static {
        XMLUnit.setIgnoreWhitespace(true);
    }

    /**
     * This method will compare both XMLs and validate its attribute order
     *
     * @param expected what are we expecting
     * @param actual what we receive
     * @return if they have same value and child order
     */
    public static boolean isIdentical(final String expected, final String actual) {
        final Diff diff = createDiffResult(expected, actual, false);

        return diff.identical();
    }

    /**
     * This method will compare both XMLs and WILL NOT validate its attribute order
     *
     * @param expected what are we expecting
     * @param actual what we receive
     * @return if they have same value
     */
    public static boolean isSimilar(final String expected, final String actual) {
        final Diff diff = createDiffResult(expected, actual, true);

        return diff.similar();
    }

    private static Diff createDiffResult(final String expected, final String actual, final boolean ignoreAttributeOrder) {
        XMLUnit.setIgnoreAttributeOrder(ignoreAttributeOrder);
        try {
            return XMLUnit.compareXML(expected, actual);
        } catch (SAXException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}