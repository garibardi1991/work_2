package ru.garibardi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormModelLombok {
    private String address;
    private String firstName;
    private String lastName;
    private String day;
    private String month;
    private String year;
    private String email;
    private String phone;

}
