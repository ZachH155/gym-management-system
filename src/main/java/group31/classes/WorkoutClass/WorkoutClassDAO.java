package group31.classes.WorkoutClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WorkoutClassDAO {
    public Connection connection;

    //constructor
    public WorkoutClassDAO(Connection connection) {
        this.connection = connection;
    }


    public void addWorkoutClass(WorkoutClass workoutclass) throws SQLException {
        String sql = "INSERT INTO workoutclasses(title, trainer, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, workoutclass.getTitle());
            statement.setString(2, workoutclass.getTrainer());
            statement.setString(3, workoutclass.getDescription());
            statement.executeUpdate();
            System.out.println("Workout Class Created!");
        }  catch (Exception e) {
            System.err.println("Database connection failed");
        }
    }

    public WorkoutClass getWorkoutClass(String title, String trainer) throws SQLException {
        WorkoutClass currentWorkoutClass = new WorkoutClass();
        String sql = "SELECT * FROM workoutclasses";
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {

            while (result.next()) {
                if (result.getString("title").equals(title)
                    && result.getString("trainer").equals(trainer)) {
                    currentWorkoutClass = new WorkoutClass(result.getString("title"),
                    result.getString("trainer"),
                    result.getString("description"));
                    break;
                }
            }
            return currentWorkoutClass;
        } catch (Exception e) {
            System.err.println("Database connection failed");
        }
        return currentWorkoutClass;
    }

    public void deleteWorkoutClass(String title) throws SQLException {
        String sql = "DELETE FROM workoutclasses WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.executeUpdate();
        }
    }

    public List<WorkoutClass> getAllWorkoutClasses() {
        String sql = "SELECT * FROM workoutclasses";
        List<WorkoutClass> workoutClassList = new ArrayList<>();
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {
                while (result.next()) {
                    WorkoutClass listWorkoutClass = new WorkoutClass(result.getString("title"),
                    result.getString("trainer"),
                    result.getString("description"));
                    workoutClassList.add(listWorkoutClass);
                }

            return workoutClassList;
        } catch (Exception e) {
            System.err.println("Database connection failed");  
            return workoutClassList; 
        }
    }

    public List<WorkoutClass> getAllWorkoutClassesTrainer(String trainer) {
        String sql = "SELECT * FROM workoutclasses";
        List<WorkoutClass> workoutClassList = new ArrayList<>();
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {
                while (result.next()) {
                    if (result.getString("trainer").equals(trainer)) {
                        WorkoutClass listWorkoutClass = new WorkoutClass(result.getString("title"),
                        result.getString("trainer"),
                        result.getString("description"));
                        workoutClassList.add(listWorkoutClass);
                    }
                }

            return workoutClassList;
        } catch (Exception e) {
            System.err.println("Database connection failed");  
            return workoutClassList; 
        }
    }
};
