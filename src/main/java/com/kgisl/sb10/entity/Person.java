package com.kgisl.sb10.entity;

import org.springframework.data.annotation.Id;
 
public record Person(@Id int id, String uname, String email) {
}
