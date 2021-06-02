import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        double[] patientTemp = new double[30];
        for (int i = 0; i < patientTemp.length; i++) {
            patientTemp[i] = new BigDecimal(32 + 8 * Math.random()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            //System.out.println(i + 1 + " - " + patientTemp[i]);
        }
        double sumTemp = 0.0;
        for (int j = 0; j < patientTemp.length; j++) {
            sumTemp += patientTemp[j];
        }
        double averageTemp = new BigDecimal(sumTemp / patientTemp.length).setScale(1, RoundingMode.HALF_UP).doubleValue();
        System.out.println(averageTemp);
    }
}