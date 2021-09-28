package songlibrary.models;

import songlibrary.util.Constants;

/**
 * An enum containing the different Generes that an album can be.
 *
 * @author Umar, Noor
 */
public enum Genre {
    Classical,
    Country,
    Jazz,
    Pop,
    Unknown;

    /**
     * Converts the enum to a human-readable string representation.
     *
     * @return string representation of Genre enum
     */
    @Override
    public String toString() {
        switch (this) {
            case Classical: {
                return Constants.CLASSICAL;
            }
            case Country: {
                return Constants.COUNTRY;
            }
            case Jazz: {
                return Constants.JAZZ;
            }
            case Pop: {
                return Constants.POP;
            }
            default: {
                return Constants.UNKNOWN;
            }
        }
    }
}
