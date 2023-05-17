package ass.notificationobject;

import ass.assignment.AssignmentObject;
import ass.studentobject.Student;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationSupport {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public NotificationSupport() {
    }

    public Notification createNotification(String desc, Student student, AssignmentObject assignment) {
        date = new Date();
        Notification not = new Notification();
        not.setAddedBy(student.getType());
        not.setDescription(desc);
        not.setReadStatus("unread");
        not.setTimestamp(dateFormat.format(date));
        not.setUserId(student.getId());
        not.setAssignmentId(assignment.getId());
        return not;
    }

    public List<Notification> getNotifications(Student student, List<Notification> notificationList, List<AssignmentObject> assignmentList) {
        List<Notification> noteList = new ArrayList();
        if (student.getType().equals("Advisor")) {
            for (Notification n : notificationList) {
                if (n.getAddedBy().equals("Student") && n.getReadStatus().equals("unread")) {
                    noteList.add(n);
                }
            }

        } //Student object notifications
        else {
            for (Notification n : notificationList) {
                long nIndex = n.getAssignmentId();
                if (n.getAddedBy().equals("Advisor") && n.getReadStatus().equals("unread")) {
                    //Notification added by Advisor
                    for (AssignmentObject ass : assignmentList) {
                        long aIndex = ass.getId();
                        if (nIndex == aIndex && ass.getStudentId().equals(student.getMarticulation())) {
                            noteList.add(n);
                        }
                    }
                }
            }
        }

        return noteList;
    }
}
