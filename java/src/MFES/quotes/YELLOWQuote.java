package MFES.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class YELLOWQuote {
    private static int hc = 0;
    private static YELLOWQuote instance = null;

    public YELLOWQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static YELLOWQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new YELLOWQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof YELLOWQuote;
    }

    public String toString() {
        return "<YELLOW>";
    }
}
