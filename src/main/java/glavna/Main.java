package glavna;

import demo.Student;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main  {
    private static String filePath;
    private static List<Student> listaStudenata = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if(args.length>0){
            filePath = args[0];
            listaStudenata = readStudentsFromFile();
        } else {
            return;
        }

        System.out.println("Dobrodosao dragi korisnice...");
        Scanner sc = new Scanner(System.in);

        do{
            String upisanaLinija = sc.nextLine();
            String[] splitString = upisanaLinija.split(" ");
            Command naredba = Command.valueOf(splitString[0].toUpperCase());

            if(naredba.equals(Command.CREATE)){
                createStudent(splitString);
            }
            else if(naredba.equals(Command.READ)){
                readStudent(splitString[1]);
            }
            else if (naredba.equals(Command.FILTERGRADE)){
                filterGradeStudent(splitString);
            }
            else if (naredba.equals(Command.FILTERNAME)){
                filterNameStudent(splitString);
            }
            else if (naredba.equals(Command.EXIT)){
                System.exit(-1);
            }
        }while(true);


    }

    private static void filterNameStudent(String[] splitString) {
        String pocetakImena = splitString[1];

        listaStudenata.stream().filter(student -> student.getIme().toLowerCase().startsWith(pocetakImena.toLowerCase()))
                .forEach(student -> {
                    String velikaMalaSlova = "";
                    if(splitString.length == 3){
                        velikaMalaSlova = splitString[2];
                    }
                    String ispis = student.getIme() + " " + student.getPrezime();
                    if(velikaMalaSlova.equals(""))
                        System.out.println(ispis);
                    else if(velikaMalaSlova.equals("l"))
                        System.out.println(ispis.toLowerCase());
                    else if(velikaMalaSlova.equals("u"))
                        System.out.println(ispis.toUpperCase());
                });
    }

    private static void filterGradeStudent(String[] splitString) {
        if(splitString.length != 3) {
            System.out.println("Greska! Nedostaje relacija.");
            return;
        }
        String opcijaUsporedivanja = splitString[1];
        Integer ocjena = Integer.parseInt(splitString[2]);
        if(opcijaUsporedivanja.equals("l")){
            listaStudenata.stream().filter(student -> student.getOcjena() < ocjena)
                    .forEach(student -> System.out.println("Ime " + student.getIme()
                        + " Prezime "+student.getPrezime() + " Ocjena "+student.getOcjena()));
        }
        else if(opcijaUsporedivanja.equals("g")){
            listaStudenata.stream().filter(student -> student.getOcjena() > ocjena)
                    .forEach(student -> System.out.println("Ime " + student.getIme()
                            + " Prezime "+student.getPrezime() + " Ocjena "+student.getOcjena()));
        }
        else if(opcijaUsporedivanja.equals("e")){
            listaStudenata.stream().filter(student -> student.getOcjena() == ocjena)
                    .forEach(student -> System.out.println("Ime " + student.getIme()
                            + " Prezime "+student.getPrezime() + "Ocjena "+student.getOcjena()));
        }
    }

    private static void readStudent(String upisanJMBAG) {
        listaStudenata.stream().filter(s->s.getJMBAG().equals(upisanJMBAG))
                .forEach(student -> System.out.println("Ime " + student.getIme()
                        + " Prezime "+student.getPrezime() + " Ocjena "+student.getOcjena()));
    }

    private static void createStudent(String[] splitString) {
        String jmbag = splitString[1];
        String ime = splitString[2];
        String prezime = splitString[3];
        int ocjena = Integer.parseInt(splitString[4]);
        Student newStudent = new Student(jmbag, ime, prezime, ocjena);
        listaStudenata.add(newStudent);
        writeStudentToFile(newStudent);
        System.out.println("Studen uspjesno kreiran!");
    }

    private static List<Student> readStudentsFromFile() throws IOException {
        List<String> allLines=  Files.readAllLines(Paths.get(filePath));
        for(String studentLine : allLines){
            String[] studentInfo = studentLine.split(";");
            String jmbag = studentInfo[0];
            String ime = studentInfo[1];
            String prezime = studentInfo[2];
            Integer ocjena = Integer.parseInt(studentInfo[3]);

            Student newStudent = new Student(jmbag, ime, prezime, ocjena);
            listaStudenata.add(newStudent);
        }
        return listaStudenata;

    }

    private static void writeStudentToFile(Student student){
        try (FileWriter out = new FileWriter(filePath,true)){
            out.append(student.getJMBAG());
            out.append(";");
            out.append(student.getIme());
            out.append(";");
            out.append(student.getPrezime());
            out.append(";");
            out.append(student.getOcjena().toString());
            out.append(";");
            out.append(System.lineSeparator());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
