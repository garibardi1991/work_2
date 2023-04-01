package ru.garibardi.models;

import lombok.Data;

@Data
public class LoginBodyLombokModel {
    //  "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

    private String email, password, name, job, id, username, firstName, lastName, phone, userStatus;
}
