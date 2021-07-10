import java.util.ArrayList;
import java.util.Date;

public class Log {
    private ArrayList<Workout> workouts;

    public Log() {
        this.workouts = new ArrayList<>();
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public ArrayList<Workout> getWorkouts(Date from, Date to) {
        ArrayList<Workout> workoutsInRange = new ArrayList<>();

        for (Workout w : workouts) {
            if (w.getStartTime().after(from) && w.getStartTime().before(from))
                workoutsInRange.add(w);
        }

        return workoutsInRange;
    }

    public void addWorkout(Workout w) {
        this.workouts.add(w);
    }

}



