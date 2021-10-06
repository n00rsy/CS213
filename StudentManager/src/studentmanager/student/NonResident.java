package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class NonResident extends Student {

    public NonResident(String name, Major major, int numCredits) {
        super(name, major, numCredits);
        setTuition(TuitionConfig.NONRES_TUITION);
        setTuitionPerCreditHour(TuitionConfig.NONRES_TUITION_PER_CREDIT);
        setUniversityFee(TuitionConfig.UNI_FEE);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
    }

    @Override
    public void tuitionDue() {

    }

    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "non-resident";
    }

}
