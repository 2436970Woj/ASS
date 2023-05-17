package ass.controller;

import ass.assignment.AssignmentObject;
import ass.assignment.AssignmentValidation;
import ass.noteobject.Note;
import ass.notificationobject.Notification;
import ass.notificationobject.NotificationSupport;
import ass.service.AssignmentService;
import ass.service.NoteService;
import ass.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ass.studentobject.Student;
import ass.service.StudentService;
import ass.studentobject.StudentValidation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ASSController {

    @Autowired
    private StudentService studentService;
    private Student studentError;
    private Student studentTemp = new Student(0, "", "", "", "", "", "", "", "", "", "");
    private StudentValidation valid = new StudentValidation();
    private List<Student> studentList = new ArrayList();
    private int loginAttempts = 0;

    @Autowired
    private AssignmentService assignmentService;
    private AssignmentObject assignmentError;
    private AssignmentObject assignmentTemp = new AssignmentObject(0, "", "", "", "", "", "", "", "");
    private AssignmentValidation assValid = new AssignmentValidation();
    private List<AssignmentObject> assignmentList = new ArrayList();

    @Autowired
    private NoteService noteService;
    private Note note = new Note(0, 0, "", "", "");
    private List<Note> notesList = new ArrayList();

    @Autowired
    private NotificationService notificationService;
    private NotificationSupport notSupport = new NotificationSupport();
    private Notification notificationTemp = new Notification();
    private List<Notification> notificationList = new ArrayList();

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    @GetMapping("/index")
    public String viewHomePage(Model model) {
        studentTemp = new Student(0, "", "", "", "", "", "", "", "", "", "");
        return "index";
    }
    @GetMapping("/indexTest")
    public String viewindexTest(Model model) {
        studentTemp = new Student(0, "", "", "", "", "", "", "", "", "", "");
        return "indexTest";
    }

    @GetMapping("/displayUsers")
    public String showIndex1(Model model) {
        model.addAttribute("student", studentTemp);
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "listStudents";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Student unlockStudent = studentService.getStudentById(id);
        model.addAttribute("unlockStudent", unlockStudent);
        model.addAttribute("student", studentTemp);
        
        if (unlockStudent.getLocked().equals("true")){
            return "updateStudent";
        }
        return "updateStudentUnlocked";
    }
    @GetMapping("/unlockStudent/{id}")
    public String unlockStudent(@PathVariable(value = "id") long id, Model model) {
        Student unlockStudent = studentService.getStudentById(id);
        unlockStudent.setLocked("false");
        studentService.saveStudent(unlockStudent);
        model.addAttribute("unlockStudent", unlockStudent);
        model.addAttribute("student", studentTemp);
        return "studentUnlocked";
    }

    @GetMapping("/register")
    public String showStudentForm(Model model) {
        //Create a model to bind new employee data

        Student student = new Student(0, "", "", "", "", "", "", "", "", "", "");
        studentError = new Student(0, "", "", "", "", "", "", "", "", "", "");
        model.addAttribute("student", student);
        model.addAttribute("studentError", studentError);
        return "registerError";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        //Create a model to bind new employee data

        Student student = new Student();
        model.addAttribute("student", student);
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute("student") Student student, Model model) {
        //Create a model to bind new employee data

        String username = student.getEmail();
        String password = student.getPassword();
        String locked = "";

        studentList = studentService.getAllStudents();
        studentTemp = valid.setUpStudent(username, studentList);

        // check if the username exists
        if (studentTemp.getLocked() == null) {
            studentTemp = student;
            studentTemp.setCourseId("The username or password is incorrect");
            model.addAttribute("student", studentTemp);
            return "login";
        } //the username exists in database
        else {
            locked = studentTemp.getLocked();
            if (locked.equals("true")) {
                // the user is locked
                studentTemp.setCourseId("Your account has been locked");
                model.addAttribute("student", studentTemp);  
                return "login";
            } 
            // check if password and username is correct
            else if (valid.loginValidation(username, password, studentList)) {
                studentTemp.setLocked("false");
                studentService.saveStudent(studentTemp);
                model.addAttribute("student", studentTemp);
                loginAttempts = 0;
                return "redirect:/welcomePage";
            } 
            // login failed
            else if (locked.equals("false")){
                    loginAttempts = 0;
                }
                else {
                    loginAttempts = Integer.parseInt(locked.substring(5));
                }
                loginAttempts++;
                if (loginAttempts >=3){
                    studentTemp.setLocked("true");
                }
                else {
                    studentTemp.setLocked("false" + loginAttempts);
                }
                studentService.saveStudent(studentTemp);
                studentTemp.setCourseId("The username or password is incorrect ");
                model.addAttribute("student", studentTemp);      
        }
        return "login";
    }

    @GetMapping("/welcomePage")
    public String welcomePage(Model model) {
        model.addAttribute("student", studentTemp);

        if (studentTemp.getType().equals("Student")) {
            return "redirect:/welcomePageStudent";
        } else {
            return "redirect:/welcomePageAdvisor";
        }
    }

    @GetMapping("/welcomePageStudent")
    public String welcomePageStudent(Model model) {
        notificationList = notificationService.getAllNotifications();
        assignmentList = assignmentService.getAllAssignments();

        model.addAttribute("student", studentTemp);
        List<Notification> notificationFilteredList = new ArrayList();
        notificationFilteredList = notSupport.getNotifications(studentTemp, notificationList, assignmentList);

        if (notificationFilteredList.isEmpty()) {
            return "welcomePageStudent";
        }
        model.addAttribute("notList", notificationFilteredList);
        return "welcomePageStudentNot";
    }

    @GetMapping("/welcomePageStudentTest")
    public String welcomePageStudentTest(Model model) {
        notificationList = notificationService.getAllNotifications();
        assignmentList = assignmentService.getAllAssignments();
        studentTemp = studentService.getStudentById(32);
        model.addAttribute("student", studentTemp);
        List<Notification> notificationFilteredList = new ArrayList();
        notificationFilteredList = notSupport.getNotifications(studentTemp, notificationList, assignmentList);

        if (notificationFilteredList.isEmpty()) {
            return "welcomePageStudent";
        }
        model.addAttribute("notList", notificationFilteredList);
        return "welcomePageStudentNot";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentTemp = student;
        studentTemp.setLocked("false");
        studentTemp.setType("Student");
        studentList = studentService.getAllStudents();
        studentError = valid.validateAll(student, studentList);

        if (valid.checkErrors(studentError)) {
            return "redirect:/registerErr";
        }
        studentService.saveStudent(student);

        String desc = "New Student has been registered with matriculation number " + student.getMarticulation();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));
        return "index";
    }

    @GetMapping("/registerErr")
    public String showRegError(Model model) {

        model.addAttribute("studentError", studentError);
        model.addAttribute("student", studentTemp);
        return "registerError";
    }

    @GetMapping("/testStudentError")
    public String testStudentError(Model model) {

        studentTemp = studentService.getStudentById(32);
        studentError = new Student();
        model.addAttribute("studentError", studentError);
        model.addAttribute("student", studentTemp);
        return "registerError";
    }

    @GetMapping("/welcomePageAdvisor")
    public String welcomePageAdvisor(Model model) {
        notificationList = notificationService.getAllNotifications();
        assignmentList = assignmentService.getAllAssignments();

        model.addAttribute("student", studentTemp);
        List<Notification> notificationFilteredList = new ArrayList();
        notificationFilteredList = notSupport.getNotifications(studentTemp, notificationList, assignmentList);

        if (notificationFilteredList.isEmpty()) {
            return "welcomePageAdvisor";
        }
        model.addAttribute("notList", notificationFilteredList);
        return "welcomePageAdvisorNot";

    }

    @GetMapping("/welcomePageAdvisorTest")
    public String welcomePageAdvisorTest(Model model) {
        notificationList = notificationService.getAllNotifications();
        assignmentList = assignmentService.getAllAssignments();
        studentTemp = studentService.getStudentById(62);
        model.addAttribute("student", studentTemp);
        List<Notification> notificationFilteredList = new ArrayList();
        notificationFilteredList = notSupport.getNotifications(studentTemp, notificationList, assignmentList);

        if (notificationFilteredList.isEmpty()) {
            return "welcomePageAdvisor";
        }
        model.addAttribute("notList", notificationFilteredList);
        return "welcomePageAdvisorNot";

    }

    @GetMapping("/displayAssStudent")
    public String displayAssStudent(Model model) {
        String studentId = studentTemp.getMarticulation();
        assignmentList = assignmentService.getAllAssignments();
        List<AssignmentObject> assignments = new ArrayList();

        for (AssignmentObject n : assignmentList) {
            if (n.getStudentId().equals(studentId)) {
                assignments.add(n);
            }
        }

        model.addAttribute("student", studentTemp);
        model.addAttribute("listAss", assignments);
        return "listAllAssignmentsStudent";
    }

    @GetMapping("/submitAssForm/{id}")
    public String submitAss(@PathVariable(value = "id") long id, Model model) {
        assignmentTemp = assignmentService.getAssignmentById(id);

        if (assignmentTemp.getSubmitted().equals("no")) {
            model.addAttribute("student", studentTemp);
            model.addAttribute("assignment", assignmentTemp);
            return "submitAssignment";
        }

        model.addAttribute("student", studentTemp);
        model.addAttribute("assignment", assignmentTemp);
        return "submitAssignmentError";
    }

    @PostMapping("/submitAss")
    public String submitAss(@ModelAttribute("assignment") AssignmentObject assignment, Model model) {

        assignmentTemp.setText(assignment.getText());
        assignmentTemp.setSubmitted("submitted on the " + dateFormat.format(date));
        assignmentService.saveAssignment(assignmentTemp);
        model.addAttribute("student", studentTemp);
        model.addAttribute("assignment", assignmentTemp);

        String desc = "Student submitted Assignment " + assignmentTemp.getReference();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));

        return "submitted";
    }

    /*
    *************************************Assignment controller ******************************
     */
    @GetMapping("/createAssignment")
    public String viewCreateAssignment(Model model) {
        //model.addAttribute("listAssignments", assignmentService.getAllAssignments());
        assignmentError = new AssignmentObject();
        assignmentTemp = new AssignmentObject();

        model.addAttribute("student", studentTemp);
        model.addAttribute("assignmentErr", assignmentError);
        model.addAttribute("assignment", assignmentTemp);
        return "createAssignment";
    }

    @PostMapping("/saveAssignment")
    public String saveAssignment(@ModelAttribute("assignment") AssignmentObject assignment) {
        assignmentTemp = assignment;
        assignmentList = assignmentService.getAllAssignments();
        assignmentError = assValid.validateAll(assignment, assignmentList);

        if (assValid.checkErrors(assignmentError)) {
            return "redirect:/createAssErr";
        }
        assignment.setReference(assValid.getReference(assignmentList));
        assignment.setSubmitted("no");
        assignmentService.saveAssignment(assignment);

        String desc = "Advisor created Assignment " + assignmentTemp.getReference();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));
        return "redirect:/welcomePageAdvisor";
    }

    @PostMapping("/updateAssignment")
    public String updateAssignment(@ModelAttribute("assignment") AssignmentObject assignment) {
        assignmentTemp = assignment;
        assignmentList = assignmentService.getAllAssignments();
        assignmentError = assValid.validateAll(assignment, assignmentList);

        if (assValid.checkErrors(assignmentError)) {
            return "redirect:/createAssErr";
        }

        assignmentService.saveAssignment(assignment);

        String desc = "Advisor updated Assignment " + assignmentTemp.getReference();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));
        return "redirect:/welcomePageAdvisor";
    }

    @GetMapping("/createAssErr")
    public String showCreateAssError(Model model) {

        model.addAttribute("student", studentTemp);
        model.addAttribute("assignmentErr", assignmentError);
        model.addAttribute("assignment", assignmentTemp);
        return "createAssignment";
    }

    @GetMapping("/displayAllAssignments")
    public String displayAllAssignments(Model model) {

        model.addAttribute("student", studentTemp);
        model.addAttribute("listAss", assignmentService.getAllAssignments());
        return "listAllAssignments";
    }

    @GetMapping("/showUpdateAssignmentForm/{id}")
    public String showFormUpdateAssignment(@PathVariable(value = "id") long id, Model model) {

        AssignmentObject ass = assignmentService.getAssignmentById(id);
        assignmentError = new AssignmentObject();

        model.addAttribute("student", studentTemp);
        model.addAttribute("assignmentErr", assignmentError);
        model.addAttribute("assignment", ass);
        return "updateAssignment";
    }

    @GetMapping("/showDeleteAssignmentForm/{id}")
    public String showDeleteAssignmentForm(@PathVariable(value = "id") long id, Model model) {

        AssignmentObject ass = assignmentService.getAssignmentById(id);
        model.addAttribute("student", studentTemp);
        model.addAttribute("assignment", ass);
        return "deleteAssignment";
    }

    @GetMapping("/deleteAssignment/{id}")
    public String deleteAssignment(@PathVariable(value = "id") long id, Model model) {
        String desc = "Advisor deleted Assignment " + assignmentTemp.getReference();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));

        model.addAttribute("student", studentTemp);
        assignmentService.deleteAssignmentById(id);
        return "redirect:/welcomePageAdvisor";
    }

    @GetMapping("/addNotesAll")
    public String addNotesAll(Model model) {

        model.addAttribute("student", studentTemp);
        model.addAttribute("listAss", assignmentService.getAllAssignments());
        return "displayNotes";
    }

    /*
    *************************************Note controller ******************************
    *
     */

    @GetMapping("/addNotesForm/{id}")
    public String addNotesForm(@PathVariable(value = "id") long id, Model model) {
        notesList = noteService.getAllNotes();
        assignmentTemp = assignmentService.getAssignmentById(id);
        List<Note> notesAssList = new ArrayList();

        for (Note n : notesList) {
            if (n.getAssignmentId() == assignmentTemp.getId()) {
                notesAssList.add(n);
            }
        }

        assignmentTemp = assignmentService.getAssignmentById(id);
        model.addAttribute("student", studentTemp);
        model.addAttribute("assignment", assignmentTemp);
        model.addAttribute("notesList", notesAssList);
        model.addAttribute("note", note);

        if (notesAssList.isEmpty()) {
            return "addFirstNote";
        }
        return "addNotes";
    }

    @PostMapping("/addNote")
    public String addNotes(@ModelAttribute("note") Note note) {
        date = new Date();
        note.setAddedBy(studentTemp.getFirstLastName());
        note.setAssignmentId(assignmentTemp.getId());
        note.setAddedBy(studentTemp.getType());
        note.setTimestamp(dateFormat.format(date));
        noteService.saveNote(note);

        String desc = studentTemp.getType() + " added new note to Assignment " + assignmentTemp.getReference();
        notificationService.saveNotification(notSupport.createNotification(desc, studentTemp, assignmentTemp));

        if (studentTemp.getType().equals("Student")) {
            return "redirect:/welcomePageStudent";
        } else {
            return "redirect:/welcomePageAdvisor";
        }
    }

    @GetMapping("/addNotesAllStudent")
    public String addNotesAllStudent(Model model) {
        String studentId = studentTemp.getMarticulation();
        assignmentList = assignmentService.getAllAssignments();
        List<AssignmentObject> assignments = new ArrayList();

        for (AssignmentObject n : assignmentList) {
            if (n.getStudentId().equals(studentId)) {
                assignments.add(n);
            }
        }

        model.addAttribute("student", studentTemp);
        model.addAttribute("listAss", assignments);
        return "displayNoteStudent";
    }

    @GetMapping("/addNotesFormStudent/{id}")
    public String addNotesFormStudent(@PathVariable(value = "id") long id, Model model) {
        notesList = noteService.getAllNotes();
        assignmentTemp = assignmentService.getAssignmentById(id);
        List<Note> notesAssList = new ArrayList();

        for (Note n : notesList) {
            if (n.getAssignmentId() == assignmentTemp.getId()) {
                notesAssList.add(n);
            }
        }

        assignmentTemp = assignmentService.getAssignmentById(id);
        model.addAttribute("student", studentTemp);
        model.addAttribute("assignment", assignmentTemp);
        model.addAttribute("notesList", notesAssList);
        model.addAttribute("note", note);

        if (notesAssList.isEmpty()) {
            return "addFirstNoteStudent";
        }
        return "addNoteStudent";
    }

    /*
    *************************************Notification controller ******************************
     */
    @GetMapping("/readNotStudent/{id}")
    public String readNotStudent(@PathVariable(value = "id") long id, Model model) {
        notificationTemp = notificationService.getNotificationById(id);
        model.addAttribute("student", studentTemp);
        model.addAttribute("notification", notificationTemp);
        notificationTemp.setReadStatus("read");
        notificationService.saveNotification(notificationTemp);
        return "welcomePageStudentReadNote";
    }

    @GetMapping("/readNotAdvisor/{id}")
    public String readNotAdvisor(@PathVariable(value = "id") long id, Model model) {
        notificationTemp = notificationService.getNotificationById(id);
        model.addAttribute("student", studentTemp);
        model.addAttribute("notification", notificationTemp);
        notificationTemp.setReadStatus("read");
        notificationService.saveNotification(notificationTemp);
        return "welcomePageAdvisorReadNote";
    }

}
