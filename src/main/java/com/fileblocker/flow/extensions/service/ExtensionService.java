package com.fileblocker.flow.extensions.service;

import com.fileblocker.flow.common.exception.DuplicateExtensionException;
import com.fileblocker.flow.common.exception.LimitExceededException;
import com.fileblocker.flow.extensions.domain.CustomExtension;
import com.fileblocker.flow.extensions.domain.FixedExtension;
import com.fileblocker.flow.extensions.dto.CreateCustomExtensionRequest;
import com.fileblocker.flow.extensions.dto.ToggleFixedExtensionRequest;
import com.fileblocker.flow.extensions.repository.CustomExtensionRepository;
import com.fileblocker.flow.extensions.repository.FixedExtensionRepository;
import com.fileblocker.flow.util.ExtensionUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExtensionService {

    private static final long CUSTOM_LIMIT = 200L;

    private final FixedExtensionRepository fixedRepo;
    private final CustomExtensionRepository customRepo;

    public List<FixedExtension> listFixed(){ return fixedRepo.findAll(); }
    public List<CustomExtension> listCustom(){ return customRepo.findAll(); }

    public FixedExtension toggleFixed(ToggleFixedExtensionRequest req){
        String name = ExtensionUtils.normalize(req.getName());
        if(!ExtensionUtils.isValid(name)) throw new IllegalArgumentException("확장자 형식 오류");
        FixedExtension fe = fixedRepo.findByName(name).orElseThrow(() -> new IllegalArgumentException("고정 확장자가 존재하지 않습니다."));
        fe.setBlocked(req.isBlocked());
        return fe; // JPA dirty-check
    }

    public CustomExtension addCustom(CreateCustomExtensionRequest req){
        String name = ExtensionUtils.normalize(req.getName());
        if(!ExtensionUtils.isValid(name)) throw new IllegalArgumentException("확장자 형식 오류");
        if(customRepo.count() >= CUSTOM_LIMIT) throw new LimitExceededException("커스텀 확장자는 최대 200개입니다.");
        if(customRepo.existsByName(name) || fixedRepo.existsByName(name)) throw new DuplicateExtensionException("이미 존재하는 확장자입니다.");
        return customRepo.save(CustomExtension.builder().name(name).build());
    }

    public void removeCustom(Long id){ customRepo.deleteById(id); }

    /** 현재 차단 중인 전체 확장자(고정-체크 + 커스텀) */
    public List<String> getAllBlocked(){
        var fixed = fixedRepo.findAll().stream()
                .filter(FixedExtension::isBlocked).map(FixedExtension::getName).toList();
        var custom = customRepo.findAll().stream()
                .map(CustomExtension::getName).toList();
        return new java.util.ArrayList<>() {{ addAll(fixed); addAll(custom); }};
    }
}
