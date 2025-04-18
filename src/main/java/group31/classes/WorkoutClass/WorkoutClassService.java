package group31.classes.WorkoutClass;

import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {
    public WorkoutClassDAO DAO;

    // constructor
    public WorkoutClassService(WorkoutClassDAO DAO) {
        this.DAO = DAO;
    }

    // methods
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        if (workoutClass == null) {
            System.out.println("WorkoutClass obj null");
            return;
        }

        WorkoutClass newWorkoutClass = new WorkoutClass(workoutClass.getTitle(), workoutClass.getTrainer(), workoutClass.getDescription());
        DAO.addWorkoutClass(newWorkoutClass);
    }

    public WorkoutClass getWorkoutClass(String title, String trainer) throws SQLException {
        if (title == null || trainer == null) {
            System.out.println("Enter a title and trainer");
            return null;
        }
        return DAO.getWorkoutClass(title, trainer);
    }

    public List<WorkoutClass> getAllWorkoutClasss() throws SQLException {
        return DAO.getAllWorkoutClasses();
    }

    public void deleteWorkoutClass(String title) throws SQLException {
        DAO.deleteWorkoutClass(title);
    }

}
