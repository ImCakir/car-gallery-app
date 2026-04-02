package com.caglacakir.handler;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<E> {

    private Integer status;

    private Exception<E> exception; // açmış olduğum Exception classını verdim :)


}
