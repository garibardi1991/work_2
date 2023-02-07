package ru.garibardi.utils;

import com.github.javafaker.Faker;
import ru.garibardi.models.FormModelLombok;

public class FormDataGenerator {

    private static final Faker faker = new Faker();

    public static FormModelLombok formDateContainer() {

        return FormModelLombok.builder()
                .address(faker.address().fullAddress())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .day("25")
                .month("April")
                .year("1991")
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().subscriberNumber(10))
                .build();
    }

}
