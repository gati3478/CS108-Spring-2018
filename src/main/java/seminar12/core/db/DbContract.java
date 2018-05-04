package seminar12.core.db;

public final class DbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {
    }

    public static class StudentTable {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_ID = "student_id";
        public static final String COLUMN_NAME_ID_NUMBER = "idnumber";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_REGISTRATION = "register_date";
    }

    public static class CoursesTable {
        public static final String TABLE_NAME = "courses";
        public static final String COLUMN_NAME_ID = "course_id";
        public static final String COLUMN_NAME_COURSE_NAME = "course_name";
        public static final String COLUMN_NAME_CREDIT = "course_credit";
        public static final String COLUMN_NAME_TYPE = "course_type";
    }

    public static class StudentCoursesTable {
        public static final String TABLE_NAME = "student_courses";
        public static final String COLUMN_NAME_STUDENT_ID = "student_id";
        public static final String COLUMN_NAME_COURSE_ID = "course_id";
    }

}
