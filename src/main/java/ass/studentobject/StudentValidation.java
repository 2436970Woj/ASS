
package ass.studentobject;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentValidation {
    
    private Student studentError;
    
    public Student validateAll(Student student, List<Student> studentsList){
        studentError = new Student(0,"","","","","","","","","","");
        studentError.setFirstName(nameValidation(student.getFirstName()));
        studentError.setLastName(nameValidation(student.getLastName()));
        studentError.setCourseId(lengthValidation(student.getCourseId(),1,30));
        studentError.setAddress(lengthValidation(student.getAddress(),5,99));
        studentError.setDepartment(lengthValidation(student.getDepartment(),4,40));
        studentError.setEmail(emailValidation(student.getEmail(), studentsList));
        studentError.setPassword(passwordValidation(student.getPassword()));
        studentError.setMarticulation(marticulationValidation(student.getMarticulation(), studentsList));
        
        return studentError;
    }
    public boolean checkErrors(Student student){
        boolean error = false;//no errors found
        if (!studentError.getFirstName().equals("")){
            error = true;
        }
        if (!studentError.getLastName().equals("")){
            error = true;
        }
        if (!studentError.getCourseId().equals("")){
            error = true;
        }
        if (!studentError.getMarticulation().equals("")){
            error = true;
        }
        if (!studentError.getDepartment().equals("")){
            error = true;
        }
        if (!studentError.getAddress().equals("")){
            error = true;
        }
        if (!studentError.getEmail().equals("")){
            error = true;
        }
        if (!studentError.getPassword().equals("")){
            error = true;
        }
        
        
        return error;
    }
    
    public String nameValidation(String name){
        String error = "";
        if (name.length()<2){
            error = "The name needs to be at least 2 characters long";
        }
        if (name.length()>30){
            error = "The name is too long";
        }
        if (name.matches("(.)*(\\d)(.)*")){
            error = "The text should not contain numbers";
        }
        return error;
    }
    public String marticulationValidation(String name, List<Student> studentsList){
        String error = "";
        
        if (name.length()!=7){
            error = "The marticulation number needs to be 7 characters long";
        }
        if (!name.chars().allMatch(Character::isDigit)){
            error = "The marticulation number must contain only numbers";
        }
        for(Student s : studentsList){
            String mart = s.getMarticulation();
            if (name.equals(mart)){
                error = "The marticulation number you have entered: " + mart + " is not unique";
            }
        }
        
        return error;
    }
    
    public String emailValidation(String name, List<Student> studentsList) {
        String error = "";
        Pattern pattern;
        Matcher matcher;

        if (name.length()<2){
            error = "The email is too short";
        }
        if (name.length()>30){
            error = "The email is too long";
        }
        pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        matcher = pattern.matcher(name);
        if (!matcher.matches()){
            error = "This is not a valid email address";
        }
        
        for(Student s : studentsList){
            String email = s.getEmail();
            if (name.equals(email)){
                error = "The username/email: " + email + " is already registered in our database";
            }
        }
                
        return error;
    }
    public String lengthValidation(String name, int lengthMin, int lengthMax){
        String error = "";
        
        if (name.length()>lengthMax){
            error = "The text is too long";
        }
        if (name.length()<lengthMin){
            error = "The text is too short";
        }
        
        return error;
    }
    public String passwordValidation(String name){
        String error = "";
        Pattern pattern;
        Matcher matcher;

        if (name.length()<8){
            error = "The password needs to be at leat 8 characters long";
        }
        if (name.length()>30){
            error = "The password is too long";
        }
        pattern = Pattern.compile(".*[A-Z].*");
        matcher = pattern.matcher(name);
        if (!matcher.matches()){
            error = "The password needs to have at least one capital letter";
        }
        
        pattern = Pattern.compile(".*[a-z].*");
        matcher = pattern.matcher(name);
        if (!matcher.matches()){
            error = "The password needs to have at least one lower case letter";
        }
        pattern = Pattern.compile(".*[0-9].*");
        matcher = pattern.matcher(name);
        if (!matcher.matches()){
            error = "The password needs to have at least one number";
        }
        pattern = Pattern.compile(".*[£$&+,:;=?@#|'<>.-^*()%!].*");
        matcher = pattern.matcher(name);
        if (!matcher.matches()){
            error = "The password needs to have at least one special character";
        }

        return error;
    }
    public boolean loginValidation(String enteredEmail, String enteredPass, List<Student> studentsList){
        
        for(Student s : studentsList){
            String emailAddress = s.getEmail();
            String password = s.getPassword();
            if (enteredEmail.equals(emailAddress) && enteredPass.equals(password)){

                return true;
            }
        }  
        return false;
    }
    public Student setUpStudent(String username, List<Student> studentsList){
        
        Student student = new Student();
        
        for(Student s : studentsList){
            String name = s.getEmail();
            if (name.equals(username)){
                student = s;
            }
        }
        
        return student;
    }
}
