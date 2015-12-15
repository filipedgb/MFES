package MFES.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class REDQuote {
    private static int hc = 0;
    private static REDQuote instance = null;

    public REDQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static REDQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new REDQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof REDQuote;
    }

    public String toString() {
        return "<RED>";
    }
}
