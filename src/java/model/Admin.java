/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@AllArgsConstructor // ==> tat ca thuoc tinh deu duoc set gia tri
@NoArgsConstructor // ==> thuoc tinh rong
@Getter
@Setter
@ToString
@Builder
public class Admin {

    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role = "Admin";

}
