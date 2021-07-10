import java.util.Date;

public class Profile {
    private String name;
    private Date dob;
    private double weightKG;
    private double heightCM;

    public Profile(String name, Date dob, double weightKG, double heightCM) {
        this.name = name;
        this.dob = dob;
        this.weightKG = weightKG;
        this.heightCM = heightCM;
    }

    public void setWeightKG(double weightKG) {
        this.weightKG = weightKG;
    }

    public void setHeightCM(double heightCM) {
        this.heightCM = heightCM;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public double getWeightKG() {
        return weightKG;
    }

    public double getHeightCM() {
        return heightCM;
    }
}
