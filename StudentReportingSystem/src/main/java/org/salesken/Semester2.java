package org.salesken;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "student2")
public class Semester2 extends Student{
    public Semester2() {
		super();
		// TODO Auto-generated constructor stub
	}
    @Column
	private int EnglishMarks;
    @Column
    private int MathsMarks;
    @Column
    private int ScienceMarks;

    public int getEnglishMarks() {
        return EnglishMarks;
    }

    public void setEnglishMarks(int englishMarks) {
        EnglishMarks = englishMarks;
    }

    public int getMathsMarks() {
        return MathsMarks;
    }

    public void setMathsMarks(int mathsMarks) {
        MathsMarks = mathsMarks;
    }

    public int getScienceMarks() {
        return ScienceMarks;
    }

    public void setScienceMarks(int scienceMarks) {
        ScienceMarks = scienceMarks;
    }
}
