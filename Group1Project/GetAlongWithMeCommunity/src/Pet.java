package getalongwithmecommunity;

public class Pet {
    private int petId;
    private String name;
    private String type;
    private int age;
    private boolean isApproved = false;

    public Pet(int petId, String name, String type, int age) {
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void Approve(){
        isApproved=true;
    }
    
    

    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    
    public String toString() {
        return "petId=" + petId + ",name=" + name + ",type=" + type + ",age=" + age +",Approve="+ isApproved +"\n" ;
    }
    
    
    
    
    
    
    
}
