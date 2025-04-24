package com.isacode.service.impl;

import com.isacode.entity.SequentialCode;
import com.isacode.repository.SequentialCodeRepository;
import com.isacode.service.SequentialCodeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequentialCodeServiceImpl implements SequentialCodeService {

    private final SequentialCodeRepository repository;

    @Override
    public SequentialCode searchNumber(int id) {
         SequentialCode code= repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Numeración con id " + id + " no encontrada"));
        code.setNumeration(code.getNumeration() +1);
        return  repository.save(code);
    }

}
