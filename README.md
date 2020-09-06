# in-memory-command-line-application-IntelliJ

This application allows the user to create a student that has a unique ID number of the academic citizen, name, lastname and a grade. The data is writen in a .CSV file, so an object of the class FileWriter that calls for the method append(). The program with a single argument and that is a absolute path to the file containing the student list. 

The commands that the program supports are "create", "read", "filter-grade", "filter-name" and "exit". For each of them is a enumeration made that is positioned on the [0] place so that it has to be the firts command the user inputs. 

The "create" command creates a student who is saved in the .CSV file. A object from the class Student is created that contains parameters that represent the student data. The object is added to a ArrayList that represents a list of students.

The "read" command reads a student that exists in the list. For the student to show the user has to enter a unique ID number of the academic citizen that the student has. A lambda code is used to filter the student that has the matching unique ID number of the academic citizen.

The "filter-grade" prints the students whose grade is less/equal/greater than the grade given by the user. Three possible values {"i", "g", "e"} indicate the relation used. 

The "filter-name" command prints the name and surname of all students whose name begins with the argument arg1, the argument arg2 is optional and if present its value must be "-u" or "-l" and indicates that the name and surname are written in upper or lower case letters.

The "exit" command ends the programs runtime
