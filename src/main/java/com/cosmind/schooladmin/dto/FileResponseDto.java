package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FileResponseDto {

    private byte[] data;
    private FileType fileType;

}
