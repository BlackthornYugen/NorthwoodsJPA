package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENROLLMENT database table.
 * 
 */
@Entity
@NamedQuery(name="Enrollment.findAll", query="SELECT e FROM Enrollment e")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="E_ID")
	private int eId;

	private String grade;

	//bi-directional many-to-one association to CourseSection
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="C_SEC_ID")
	private CourseSection courseSection;

	//bi-directional many-to-one association to Student
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="S_ID")
	private Student student;

	public Enrollment() {
	}

	public int getEId() {
		return this.eId;
	}

	public void setEId(int eId) {
		this.eId = eId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public CourseSection getCourseSection() {
		return this.courseSection;
	}

	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}