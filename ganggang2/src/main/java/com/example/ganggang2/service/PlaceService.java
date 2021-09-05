package com.example.ganggang2.service;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Place;
import com.example.ganggang2.domain.Station;
import com.example.ganggang2.domain.Vlog;
import com.example.ganggang2.repository.CategoryRepository;
import com.example.ganggang2.repository.PlaceRepository;
import com.example.ganggang2.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public void Placesave(Place place){
        placeRepository.save(place);
    }

    //controller에서 알아낸 station,category값으로 top5가져오기
    public List<Place> TOP5(Long stationId,Long categoryId){
        return placeRepository.findTOP5(stationId,categoryId);
    }


    public long find(long placeId) {
        return placeRepository.findOne(placeId).getId();
    }
}
