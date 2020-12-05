public class Course {
    private String classCode;
    private String description;
    private int classSize;
    private int priority;
    private String type;
 
    public Course(String classCode, String description, int classSize, int priority, String type) {
        this.classCode = classCode;
        this.description = description;
        this.classSize = classSize;
        this.priority = priority;
        this.type = type;
    }

    public Course(){};

    public void setClassCode(String classCode){
        this.classCode = classCode;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setClassSize(int classSize){
        this.classSize = classSize;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getClassCode(){
        return classCode;    
    }
    public String getDescription(){
        return description;
    }
    public int getClassSize(){
        return classSize;
    }
    public int getPriority(){
        return priority;
    }
    public String getType(){
        return type;
    }
}
