package group31.classes.WorkoutClass;

public class WorkoutClass {
    private String title;
    private String trainer;
    private String description;



    //constructors
    public WorkoutClass() {
        title = "missing";
        trainer = "missing";
        description = "missing";
    }

    public WorkoutClass(String trainer, String title, String description) {
        this.title = title;
        this.trainer = trainer;
        this.description = description;
    }


    //getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrainer() {
        return trainer;
    }
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
