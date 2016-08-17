package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FACULTY database table.
 * 
 */
@Entity
@NamedQuery(name="Faculty.findAll", query="SELECT f FROM Faculty f")
public class Faculty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="F_ID")
	private int fId;

	@Column(name="F_FIRST")
	private String fFirst;

	@Column(name="F_LAST")
	private String fLast;

	@Column(name="F_MI")
	private String fMi;

	@Column(name="F_PHONE")
	private String fPhone;

	@Column(name="F_PIN")
	private int fPin;

	@Column(name="F_RANK")
	private String fRank;

	@Column(name="F_SUPER")
	private int fSuper;

	//bi-directional many-to-one association to CourseSection
	@OneToMany(mappedBy="faculty")
	private List<CourseSection> courseSections;

	//bi-directional many-to-one association to Location
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LOC_ID")
	private Location location;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="faculty")
	private List<Student> students;

	public Faculty() {
	}

	public int getFId() {
		return this.fId;
	}

	public void setFId(int fId) {
		this.fId = fId;
	}

	public String getFFirst() {
		return this.fFirst;
	}

	public void setFFirst(String fFirst) {
		this.fFirst = fFirst;
	}

	public String getFLast() {
		return this.fLast;
	}

	public void setFLast(String fLast) {
		this.fLast = fLast;
	}

	public String getFMi() {
		return this.fMi;
	}

	public void setFMi(String fMi) {
		this.fMi = fMi;
	}

	public String getFPhone() {
		return this.fPhone;
	}

	public void setFPhone(String fPhone) {
		this.fPhone = fPhone;
	}

	public int getFPin() {
		return this.fPin;
	}

	public void setFPin(int fPin) {
		this.fPin = fPin;
	}

	public String getFRank() {
		return this.fRank;
	}

	public void setFRank(String fRank) {
		this.fRank = fRank;
	}

	public int getFSuper() {
		return this.fSuper;
	}

	public void setFSuper(int fSuper) {
		this.fSuper = fSuper;
	}

	public List<CourseSection> getCourseSections() {
		return this.courseSections;
	}

	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}

	public CourseSection addCourseSection(CourseSection courseSection) {
		getCourseSections().add(courseSection);
		courseSection.setFaculty(this);

		return courseSection;
	}

	public CourseSection removeCourseSection(CourseSection courseSection) {
		getCourseSections().remove(courseSection);
		courseSection.setFaculty(null);

		return courseSection;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setFaculty(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setFaculty(null);

		return student;
	}

}