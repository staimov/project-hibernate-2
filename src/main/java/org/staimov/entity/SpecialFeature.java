package org.staimov.entity;

public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String label;

    SpecialFeature(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static SpecialFeature getByLabel(String label) {
        if (label == null)
            return null;

        for (SpecialFeature feature : SpecialFeature.values()) {
            if (feature.getLabel().equals(label)) {
                return feature;
            }
        }

        throw new IllegalArgumentException(label + " not supported.");
    }
}
