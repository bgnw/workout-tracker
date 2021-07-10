import java.util.Date;

public class Workout {
    private Date startTime;
    private Date endTime;
    private String type;
    private int kcal;

    public Workout(Date startTime, Date endTime, String type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public int getKcal() {
        return kcal;
    }
}
