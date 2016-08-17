package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COURSE_SECTION database table.
 * 
 */
@Entity
@Table(name="COURSE_SECTION")
@NamedQuery(name="CourseSection.findAll", query="SELECT c FROM CourseSection c")
public class CourseSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="C_SEC_ID")
	private int cSecId;

	@Column(name="C_SEC_DAY")
	private String cSecDay;

	@Column(name="C_SEC_DURATION")
	private int cSecDuration;

	@Column(name="C_SEC_TIME")
	private int cSecTime;

	@Column(name="MAX_ENRL")
	private int maxEnrl;

	@Column(name="SEC_NUM")
	private int secNum;

	//bi-directional many-to-one association to Course
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COURSE_NO")
	private Course course;

	//bi-directional many-to-one association to Faculty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="F_ID")
	private Faculty faculty;

	//bi-directional many-to-one association to Location
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LOC_ID")
	private Location location;

	//bi-directional many-to-one association to Term
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TERM_ID")
	private Term term;

	//bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="courseSection")
	private List<Enrollment> enrollments;

	public CourseSection() {
	}

	public int getCSecId() {
		return this.cSecId;
	}

	public void setCSecId(int cSecId) {
		this.cSecId = cSecId;
	}

	public String getCSecDay() {
		return this.cSecDay;
	}

	public void setCSecDay(String cSecDay) {
		this.cSecDay = cSecDay;
	}

	public int getCSecDuration() {
		return this.cSecDuration;
	}

	public void setCSecDuration(int cSecDuration) {
		this.cSecDuration = cSecDuration;
	}

	public int getCSecTime() {
		return this.cSecTime;
	}

	public void setCSecTime(int cSecTime) {
		this.cSecTime = cSecTime;
	}

	public int getMaxEnrl() {
		return this.maxEnrl;
	}

	public void setMaxEnrl(int maxEnrl) {
		this.maxEnrl = maxEnrl;
	}

	public int getSecNum() {
		return this.secNum;
	}

	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Term getTerm() {
		return this.term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public List<Enrollment> getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public Enrollment addEnrollment(Enrollment enrollment) {
		getEnrollments().add(enrollment);
		enrollment.setCourseSection(this);

		return enrollment;
	}

	public Enrollment removeEnrollment(Enrollment enrollment) {
		getEnrollments().remove(enrollment);
		enrollment.setCourseSection(null);

		return enrollment;
	}

}