package com.fileblocker.flow.file.service;

import com.fileblocker.flow.extensions.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileValidationService {

    private final ExtensionService extensionService;

    public void validate(String filename){
        if(filename == null || filename.isBlank())
            throw new IllegalArgumentException("파일명이 비어있습니다.");
        int idx = filename.lastIndexOf('.');
        if(idx < 0 || idx == filename.length()-1) return; // 확장자 없음 → 허용(정책 변경 가능)
        String ext = filename.substring(idx+1).trim().toLowerCase();
        if(extensionService.getAllBlocked().contains(ext))
            throw new IllegalArgumentException("차단된 확장자입니다: " + ext);
    }
}
