
package ass.assignment;

import java.util.List;


public class AssignmentValidation {
    
    private AssignmentObject assignmentError;
    
    public AssignmentObject validateAll(AssignmentObject assignment, List<AssignmentObject> assignmentsList){
        assignmentError = new AssignmentObject(0,"","","","","","","","");
        assignmentError.setCourseId(lengthValidation(assignment.getCourseId(),2,30));
        assignmentError.setStudentId(studentIdValidation(assignment.getStudentId()));
        assignmentError.setText(lengthValidation(assignment.getText(),1,250000));
        assignmentError.setCreditUnits(lengthValidation(assignment.getCreditUnits(),1,30));
        assignmentError.setSession(lengthValidation(assignment.getSession(),1,30));
        assignmentError.setTerm(lengthValidation(assignment.getTerm(),1,30));
        return assignmentError;
    }
    public boolean checkErrors(AssignmentObject assignment){
        boolean error = false;//no errors found
        if (!assignmentError.getCourseId().equals("")){
            error = true;
        }
         if (!assignmentError.getStudentId().equals("")){
            error = true;
        }
        if (!assignmentError.getText().equals("")){
            error = true;
        }
        if (!assignmentError.getCreditUnits().equals("")){
            error = true;
        }
        if (!assignmentError.getSession().equals("")){
            error = true;
        }
        if (!assignmentError.getTerm().equals("")){
            error = true;
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
    public String getReference(List<AssignmentObject> assignmentsList){
        String ref = "ASS00000"; 
        String number = "";
        
        if (assignmentsList.isEmpty()){
            return ref;
        }
        
        ref = assignmentsList.get(assignmentsList.size()-1).getReference();
        
        for (int i = 0; i < ref.length();i++){
            if (Character.isDigit(ref.charAt(i))){
                number = number + ref.charAt(i);
            }
        }
        int refNumber = Integer.parseInt(number)+1;
        //ASS00001 AS10000
        if (refNumber<10){
            ref = "ASS0000"+refNumber;
        }
        else  if (refNumber<100){
            ref = "ASS000"+refNumber;
        }
        else  if (refNumber<1000){
            ref = "ASS00"+refNumber;
        }
        else  if (refNumber<10000){
            ref = "ASS0"+refNumber;
        }
        else  if (refNumber<100000){
            ref = "ASS"+refNumber;
        }
        
        return ref;
    }
    public String studentIdValidation(String mart){
        String error = "";
        
        if (mart.length()!=7){
            error = "The Student ID number needs to be 7 characters long";
        }
        if (!mart.chars().allMatch(Character::isDigit)){
            error = "The Student ID number must contain only numbers";
        }
        
        return error;
    }
}
