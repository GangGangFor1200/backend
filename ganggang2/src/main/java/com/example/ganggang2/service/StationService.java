package com.example.ganggang2.service;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Station;
import com.example.ganggang2.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public void StationSave(Station station){
        stationRepository.save(station);
    }
}
