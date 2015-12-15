package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class MyTestCase {
    public MyTestCase() {
    }

    protected void assertTrue(final Boolean arg) {
        return;
    }

    protected void assertEqual(final Object expected, final Object actual) {
        if (!(Utils.equals(expected, actual))) {
            System.out.println("Expected: "+expected + "==> Actual: "+actual);
        }
    }

    public String toString() {
        return "MyTestCase{}";
    }
}
