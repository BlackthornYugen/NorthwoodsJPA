package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COURSE_NO")
	private String courseNo;

	@Column(name="COURSE_NAME")
	private String courseName;

	private int credits;

	//bi-directional many-to-one association to CourseSection
	@OneToMany(mappedBy="course")
	private List<CourseSection> courseSections;

	public Course() {
	}

	public String getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public List<CourseSection> getCourseSections() {
		return this.courseSections;
	}

	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}

	public CourseSection addCourseSection(CourseSection courseSection) {
		getCourseSections().add(courseSection);
		courseSection.setCourse(this);

		return courseSection;
	}

	public CourseSection removeCourseSection(CourseSection courseSection) {
		getCourseSections().remove(courseSection);
		courseSection.setCourse(null);

		return courseSection;
	}

}