/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 *
 * @author dtam6
 */
public class User {

    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;
    private int status;
    private String imgUrl;

}
