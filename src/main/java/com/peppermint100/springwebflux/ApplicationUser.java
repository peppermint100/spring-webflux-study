package com.peppermint100.springwebflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {

    @Id
    private Long id;
    private String username;
    private String email;
    private String passport;
}