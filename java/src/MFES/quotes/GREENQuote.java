package MFES.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class GREENQuote {
    private static int hc = 0;
    private static GREENQuote instance = null;

    public GREENQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static GREENQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new GREENQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof GREENQuote;
    }

    public String toString() {
        return "<GREEN>";
    }
}
