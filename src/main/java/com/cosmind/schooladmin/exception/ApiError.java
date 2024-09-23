package com.cosmind.schooladmin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private String displayMessage;
    private List<String> detailsMessages;

}
