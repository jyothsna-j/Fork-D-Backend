package com.forkd.forkd_backend.service;

import org.springframework.stereotype.Service;

import com.forkd.forkd_backend.repository.MasterRepository;

@Service
public class MasterService {

    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public boolean getCurrentState() {
        return masterRepository.getIsEnabled();
    }

    public void setState(boolean value) {
        masterRepository.updateIsEnabled(value);
    }
}