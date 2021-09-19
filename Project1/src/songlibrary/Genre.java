package songlibrary;

/**
 * An enum containing the different Generes that an album can be.
 * @author AUTHOR
 */
public enum Genre {
    Classical,
    Country,
    Jazz,
    Pop,
    Unknown;

    @Override
    public String toString() {
        switch (this) {
            case Classical: {
                return "Classical";
            }
            case Country: {
                return "Country";
            }
            case Jazz: {
                return "Jazz";
            }
            case Pop: {
                return "Pop";
            }
            default: {
                return "Unknown";
            }
        }
    }
}
