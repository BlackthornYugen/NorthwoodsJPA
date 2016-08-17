package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="S_ID")
	private String sId;

	@Column(name="S_ADDRESS")
	private String sAddress;

	@Column(name="S_CITY")
	private String sCity;

	@Column(name="S_CLASS")
	private String sClass;

	@Temporal(TemporalType.DATE)
	@Column(name="S_DOB")
	private Date sDob;

	@Column(name="S_FIRST")
	private String sFirst;

	@Column(name="S_LAST")
	private String sLast;

	@Column(name="S_MI")
	private String sMi;

	@Column(name="S_PHONE")
	private String sPhone;

	@Column(name="S_PIN")
	private int sPin;

	@Column(name="S_STATE")
	private String sState;

	@Column(name="S_TIME_ENROLLED")
	private int sTimeEnrolled;

	@Column(name="S_ZIP")
	private String sZip;

	//bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="student")
	private List<Enrollment> enrollments;

	//bi-directional many-to-one association to Faculty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="F_ID")
	private Faculty faculty;

	public Student() {
	}

	public String getSId() {
		return this.sId;
	}

	public void setSId(String sId) {
		this.sId = sId;
	}

	public String getSAddress() {
		return this.sAddress;
	}

	public void setSAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getSCity() {
		return this.sCity;
	}

	public void setSCity(String sCity) {
		this.sCity = sCity;
	}

	public String getSClass() {
		return this.sClass;
	}

	public void setSClass(String sClass) {
		this.sClass = sClass;
	}

	public Date getSDob() {
		return this.sDob;
	}

	public void setSDob(Date sDob) {
		this.sDob = sDob;
	}

	public String getSFirst() {
		return this.sFirst;
	}

	public void setSFirst(String sFirst) {
		this.sFirst = sFirst;
	}

	public String getSLast() {
		return this.sLast;
	}

	public void setSLast(String sLast) {
		this.sLast = sLast;
	}

	public String getSMi() {
		return this.sMi;
	}

	public void setSMi(String sMi) {
		this.sMi = sMi;
	}

	public String getSPhone() {
		return this.sPhone;
	}

	public void setSPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public int getSPin() {
		return this.sPin;
	}

	public void setSPin(int sPin) {
		this.sPin = sPin;
	}

	public String getSState() {
		return this.sState;
	}

	public void setSState(String sState) {
		this.sState = sState;
	}

	public int getSTimeEnrolled() {
		return this.sTimeEnrolled;
	}

	public void setSTimeEnrolled(int sTimeEnrolled) {
		this.sTimeEnrolled = sTimeEnrolled;
	}

	public String getSZip() {
		return this.sZip;
	}

	public void setSZip(String sZip) {
		this.sZip = sZip;
	}

	public List<Enrollment> getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public Enrollment addEnrollment(Enrollment enrollment) {
		getEnrollments().add(enrollment);
		enrollment.setStudent(this);

		return enrollment;
	}

	public Enrollment removeEnrollment(Enrollment enrollment) {
		getEnrollments().remove(enrollment);
		enrollment.setStudent(null);

		return enrollment;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

}