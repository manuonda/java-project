package com.springboot.blog.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

   private String name;
   @NotBlank(message = "Username is required")
   private String username;
   @NotBlank(message = "Email is required")
   private String email;
   @NotBlank(message = "Password is required")
   private String password;
}
