CREATE OR REPLACE Procedure UpdateCourse
   ( name_in IN varchar2 )

IS
   cnumber number;

   cursor c1 is
   SELECT course_number
    FROM courses_tbl
    WHERE course_name = name_in;

BEGIN

SELECT COLUMN_A, COLUMN_B from TABLE_1 where COLUMN_A = '123' AND COLUMN_B = 'Germany';

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;