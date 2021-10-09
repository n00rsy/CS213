package studentmanager.config;

/**
 * A class containing constants relating to tuition values.
 * Allows for easy reconfiguration for changes to university tuition.
 * @author Noor, Umar
 */
public class TuitionConfig {

    public static final int MIN_CREDITS = 3;
    public static final int MAX_CREDITS = 24;
    public static final int MIN_FULL_TIME_CREDITS = 12;
    public static final int MAX_FULL_TIME_CREDITS = 16;

    public static final double RES_TUITION = 12536;
    public static final double RES_TUITION_PER_CREDIT = 404;
    public static final double RES_DEFAULT_FIN_AID_AMOUNT = 0;
    public static final double UNI_FEE = 3268;
    public static final double PART_TIME_UNI_FEE_MULTIPLIER = 0.8;

    public static final double NONRES_TUITION = 29737;
    public static final double NONRES_TUITION_PER_CREDIT = 966;

    public static final double INTERNAT_TUITION = 29737;
    public static final double INTERNAT_ADD_FEE = 2650;

    public static final double NY_DISCOUNT = 4000;
    public static final double CT_DISCOUNT = 5000;

    public static final double MAX_FIN_AID = 10000;
}
