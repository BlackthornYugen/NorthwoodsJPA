package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LOCATION database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOC_ID")
	private int locId;

	@Column(name="BLDG_CODE")
	private String bldgCode;

	private int capacity;

	private String room;

	//bi-directional many-to-one association to CourseSection
	@OneToMany(mappedBy="location")
	private List<CourseSection> courseSections;

	//bi-directional many-to-one association to Faculty
	@OneToMany(mappedBy="location")
	private List<Faculty> faculties;

	public Location() {
	}

	public int getLocId() {
		return this.locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getBldgCode() {
		return this.bldgCode;
	}

	public void setBldgCode(String bldgCode) {
		this.bldgCode = bldgCode;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public List<CourseSection> getCourseSections() {
		return this.courseSections;
	}

	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}

	public CourseSection addCourseSection(CourseSection courseSection) {
		getCourseSections().add(courseSection);
		courseSection.setLocation(this);

		return courseSection;
	}

	public CourseSection removeCourseSection(CourseSection courseSection) {
		getCourseSections().remove(courseSection);
		courseSection.setLocation(null);

		return courseSection;
	}

	public List<Faculty> getFaculties() {
		return this.faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

	public Faculty addFaculty(Faculty faculty) {
		getFaculties().add(faculty);
		faculty.setLocation(this);

		return faculty;
	}

	public Faculty removeFaculty(Faculty faculty) {
		getFaculties().remove(faculty);
		faculty.setLocation(null);

		return faculty;
	}

}