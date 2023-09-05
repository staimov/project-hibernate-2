package org.staimov.entity;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String label;

    Rating(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Rating getByLabel(String label) {
        if (label == null)
            return null;

        for (Rating rating : Rating.values()) {
            if (rating.getLabel().equals(label)) {
                return rating;
            }
        }

        throw new IllegalArgumentException(label + " not supported.");
    }
}
