package com.example.codagami.codagamichallenge.Objects;

/**
 * Created by jakedunahee on 2/7/18.
 */
public class Person {

    private final String mFirstName;
    private final String mLastName;
    private final String mAge;

    public Person(String firstName, String lastName, String age) {
        mFirstName = firstName;
        mLastName = lastName;
        mAge = age;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getAge() {
        return mAge;
    }
}
