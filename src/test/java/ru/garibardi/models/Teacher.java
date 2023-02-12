package ru.garibardi.models;

//{
//        "Name": "Dmitrii",
//        "Age": 33,
//        "IsGoodTeacher": true,
//        "lessons": ["JUnit5","files"],
//        "passport": {
//        "number": 123456,
//        "issueDate": "12.12.2022"
//        }
//        }

import java.util.List;

public class Teacher {
    public String Name;
    public int Age;
    public boolean IsGoodTeacher;
    public List<String> lessons;
    public Passport passport;
    public static class Passport {
        public int number;
        public String issueDate;


    }


}
