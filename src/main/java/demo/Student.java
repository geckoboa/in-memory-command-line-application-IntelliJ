package demo;

import java.util.Objects;

public class Student  {

    private String JMBAG;
    private String ime;
    private String prezime;
    private Integer ocjena;

    public Student(String JMBAG, String ime, String prezime, Integer ocjena) {
        this.JMBAG = JMBAG;
        this.ime = ime;
        this.prezime = prezime;
        this.ocjena = ocjena;
    }


    public String getJMBAG() {
        return JMBAG;
    }

    public void setJMBAG(String JMBAG) {
        this.JMBAG = JMBAG;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return JMBAG.equals(student.JMBAG) &&
                ime.equals(student.ime) &&
                prezime.equals(student.prezime) &&
                ocjena.equals(student.ocjena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(JMBAG, ime, prezime, ocjena);
    }
}
