package com.example.feelingsdiary



class User {

    var firstName: String? = null
    var lastName: String? = null
    var bday: String? = null
    var email: String? = null
    var password: String? = null

    constructor(firstName: String?, lastName: String?, bday: String?, email: String?, password: String? ) {
        this.firstName = firstName
        this.lastName = lastName
        this.bday = bday
        this.email = email
        this.password = password
    }
}