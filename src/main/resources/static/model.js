export class User {
    constructor(id, firstName, lastName, age, email, roles, password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
}

export class Role {
    constructor(id, nameNotPrefix, name) {
        this.id = id;
        this.nameNotPrefix = nameNotPrefix;
        this.name = name;
    }
}