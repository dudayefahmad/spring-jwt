package com.ahmaddudayef.springjwt.models

import javax.persistence.*


@Entity
@Table(name = "roles")
class Role {

    constructor() {}
    constructor(name: ERole?) {
        this.name = name
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var name: ERole? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}
