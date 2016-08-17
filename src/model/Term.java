package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TERM database table.
 * 
 */
@Entity
@NamedQuery(name="Term.findAll", query="SELECT t FROM Term t")
public class Term implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TERM_ID")
	private int termId;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private String status;

	@Column(name="TERM_DESC")
	private String termDesc;

	//bi-directional many-to-one association to CourseSection
	@OneToMany(mappedBy="term")
	private List<CourseSection> courseSections;

	public Term() {
	}

	public int getTermId() {
		return this.termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTermDesc() {
		return this.termDesc;
	}

	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}

	public List<CourseSection> getCourseSections() {
		return this.courseSections;
	}

	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}

	public CourseSection addCourseSection(CourseSection courseSection) {
		getCourseSections().add(courseSection);
		courseSection.setTerm(this);

		return courseSection;
	}

	public CourseSection removeCourseSection(CourseSection courseSection) {
		getCourseSections().remove(courseSection);
		courseSection.setTerm(null);

		return courseSection;
	}

}