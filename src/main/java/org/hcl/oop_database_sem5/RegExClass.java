package org.hcl.oop_database_sem5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExClass {
    private String patternString;
    private final Pattern pattern;
    public Matcher matcher;

    public RegExClass(String pattern) {
        setPatternString(pattern);
        this.pattern = Pattern.compile(this.patternString);
        matcher = this.pattern.matcher(pattern);
    }

    public void setPatternString(String pattern) {
        this.patternString = pattern;
    }
}
