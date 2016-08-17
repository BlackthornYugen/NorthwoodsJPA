package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Report {

	private static final int SLOW_LOG_DEFAULT_DURATION = 10;
	private static final String DATE_FORMAT_PATTERN = "d/MM/yyyy";

	/**
	 * Pre-exam test
	 * @param args the name of the parts of question seven to run, for example "a b c d" will run all parts.
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		List<String> argumentsList = java.util.Arrays.asList(args);
		EntityManagerFactory emf = null;
		EntityManager em = null;
		String format = null;

		try {
			emf = Persistence.createEntityManagerFactory("NorthwoodsJPA");
			em = emf.createEntityManager();
			
			/////////////////////////////////
			//              7a            //
			///////////////////////////////
			// In the first section of NorthwoodsReport.java, add a new Enrolment entry to the database. Initiate a
			// transaction and instantiate a new Enrolment member.
			// • For the Course Section (CourseSection): write a Hibernate JPA query that retrieves the
			// CourseSection instance where SEC_NUM = 2 and F_ID = 3. Set the course section for the new
			// enrolment to be the CourseSection instance.
			// • Persist the new enrolment member using the appropriate JPA method and commit the transaction.
			
			// Returns something like:
			//
			// -----7a-----
			// Persisted JS570!
			// ------------
			//
			
			if (argumentsList.indexOf("a") > -1)
			{
				slowLog("\n-----7a-----");
				CourseSection cs = em.createQuery(""
						+ "SELECT cs FROM CourseSection cs "
						+ "WHERE cs.secNum = :SEC_NUM "
						+ " AND  cs.faculty.fId = :F_ID "
						, CourseSection.class)
						.setParameter("SEC_NUM", 2)
						.setParameter("F_ID", 3)
						.setMaxResults(1).getSingleResult();
				
				Student me = new Student();
				me.setSId("JS" + (int)(Math.random() * 1000));
				
				me.setSFirst("John");
				me.setSLast("Steel");
			
				em.getTransaction().begin();
				Enrollment myEnrollment = new Enrollment();
				myEnrollment.setCourseSection(cs);
				myEnrollment.setStudent(me);
				
				em.persist(myEnrollment);
				em.getTransaction().commit();
				slowLog("Persisted %s!", me.getSId());
				slowLog("------------\n");
			}

			/////////////////////////////////
			//              7b            //
			///////////////////////////////
			// In the next section of NorthwoodsReport.java, write the JPQL query that retrieves all of the faculties
			// being supervised by Supervisor 4. Print their first name, last name, location room, building code, and
			// building location capacity.
			
			// Returns something like:
			//
			// -----7b-----
			// ID   First      Last       Location   Capacity   Building Code
			// 1    Teresa     Marx       424        1          BUS
			// 3    Colin      Langley    217        2          LIB
			// ------------
			//
			
			if (argumentsList.indexOf("b") > -1)			
			{
				slowLog("\n-----7b-----");
				List<Faculty> faculty = em.createQuery(""
						+ "SELECT f "
						+ "FROM Faculty f "
						+ "WHERE f.fSuper = :SUPER"
						, Faculty.class)
						.setParameter("SUPER", 4)
						.getResultList();
				
				format = "%-4s %-10s %-10s %-10s %-10s %s";
				slowLog(format, "ID", "First", "Last", "Location", "Capacity", "Building Code");
				for(Faculty f : faculty) {
					slowLog(format,
							f.getFId(),
							f.getFFirst(),
							f.getFLast(),
							f.getLocation().getRoom(),
							f.getLocation().getCapacity(),
							f.getLocation().getBldgCode());
				}
				slowLog("------------\n");
			}
			
			/////////////////////////////////
			//              7c            //
			///////////////////////////////
			// In the next section of NorthwoodsReport.java, write the JPQL query that retrieves all of the students
			// that have achieved any grade of “A” in any of their courses.
			// • Ensure that no student appears in the list more than once.
			// • To receive full marks you must write a single JPQL join query with your Java application.
			// • Order the list of students in ascending order by surname. In your output, include the student
			// ID, first name, last name, birth date, and the name and rank of their faculty advisor.
			
			// Returns something like:
			//
			// -----7c-----
			// ID         First      Last       Birthday           Advisor              Advisor Rank
			// JO100      Tammy      Jones      14/07/1985         Marx, Teresa         Associate
			// PE100      Jorge      Perez      19/08/1985         Marx, Teresa         Associate
			// ------------
			//

			if (argumentsList.indexOf("c") > -1)
			{
				slowLog("\n-----7c-----");
				List<Student> students = em.createQuery(""
						+ "SELECT DISTINCT s "
						+ "FROM Enrollment e "
						+ "JOIN e.student s "
						+ "WHERE e.grade = :GRADE "
						+ "ORDER BY s.sLast"
						, Student.class)
						.setParameter("GRADE", "A")
						.getResultList();
				format = "%-10s %-10s %-10s %-18s %-20s %s";
				
				slowLog(format, "ID", "First", "Last", "Birthday", "Advisor", "Advisor Rank");
				for(Student s : students) {
					slowLog(format,
							s.getSId(),
							s.getSFirst(),
							s.getSLast(),
							s.getSDob(),
							s.getFaculty().getFLast() + ", " + s.getFaculty().getFFirst(),
							s.getFaculty().getFRank());
				}
				slowLog("------------\n");
			}
			
			/////////////////////////////////
			//              7d            //
			///////////////////////////////
			// In the third section of NorthwoodsReport.java, write a JPQL query that lists the Students who reside in
			// Florida State (“FL”) and who are supervised by the faculty member with the surname of “Marx”.
			// • You must use query parameters for the conditions involving state and faculty surname.
			// • Order the list of students first by first name and then by last name.
			// • Output the following attributes for each student: Student ID, first name, last name, and birth
			// date.
			
			// Returns something like:
			//
			// -----7d-----
			// ID         First      Last       Birthday
			// JO100      Tammy      Jones      14/07/1985
			// MA100      John       Marsh      10/10/1982
			// ------------
			//

			if (argumentsList.indexOf("d") > -1)
			{
				slowLog("\n-----7d-----");
				List<Student> students = em.createQuery(""
						+ "SELECT s "
						+ "FROM Student s "
						+ "JOIN s.faculty f "
						+ "WHERE s.sState = :STATE "
						+ "  AND f.fLast = :NAME "
						+ "ORDER BY s.sLast, s.sFirst"
						, Student.class)
						.setParameter("NAME", "Marx")
						.setParameter("STATE", "FL")
						.getResultList();
				format = "%-10s %-10s %-10s %s";
				
				slowLog(format, "ID", "First", "Last", "Birthday");
				for (Student s : students) {
					slowLog(format,
							s.getSId(),
							s.getSFirst(),
							s.getSLast(),
							s.getSDob());
				}
				slowLog("------------\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			System.out.println("Done");
		}
	}

	/**
	 * Slow Logger
	 * @param message The message you want to print
	 * @throws InterruptedException
	 */
	private static void slowLog(String message) throws InterruptedException {
		slowLog(message, SLOW_LOG_DEFAULT_DURATION);
	}
	
	/**
	 * Slow Logger with Formatting
	 * @param format The format string
	 * @param args The format objects
	 * @throws InterruptedException
	 */
	private static void slowLog(String format, Object... args) throws InterruptedException {
		formatDates(args);
		slowLog(String.format(format, args), SLOW_LOG_DEFAULT_DURATION);
	}
	
	/**
	 * Slow Logger with duration
	 * @param message The message you want printed
	 * @param duration The total time you want to take to print it
	 * @throws InterruptedException
	 */
	public static void slowLog(String message, int duration) throws InterruptedException {
		Thread.sleep(duration/2);
		System.out.println(message);
		Thread.sleep(duration/2);
	}
	
	/**
	 * Format Dates
	 * Loops over some objects, formating all dates. 
	 * @param args An array of objects that might include a date
	 */
	public static void formatDates(Object... args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		for (int i = 0; i < args.length; i++) {
			if (Date.class.isAssignableFrom(args[i].getClass())) {
				args[i] = dateFormat.format(args[i]);
			}
		}
	}
}