package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class International extends NonResident {

    private final int additionalFees = 2650;
    private boolean studyAbroad;

    public International(String name, Major major, int numCredits) {

        super(name, major, numCredits);
        setTuition(TuitionConfig.RES_TUITION);
        setUniversityFee(TuitionConfig.UNI_FEE);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
    }

    @Override
    public void tuitionDue() {
        double totalTuition = 0;
        if (studyAbroad){ //true
            if(getNumCredits() > 12){ //max study abroad credits is 12
                //totalTuition +=
            }
            else{
                totalTuition += getUniversityFee() + additionalFees;
            }
        }
        else{ //false
            totalTuition += getTuition() + getUniversityFee() + additionalFees;
            if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                //totalTuition +=
            }
        }
        setTuitionDueAmount(totalTuition);
    }

    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "international";
    }
}
