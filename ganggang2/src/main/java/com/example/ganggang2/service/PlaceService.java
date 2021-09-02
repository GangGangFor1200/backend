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
    private final CategoryRepository categoryRepository;
    private final StationRepository stationRepository;

    public void Placesave(Long categoryId, Long stationId){
        //해당 category,station가져오기
        Category category=categoryRepository.findOne(categoryId);
        Station station=stationRepository.findOne(stationId);

        //Place인스턴스 만들기
        Place place=Place.createPlace(category,station);

        //db에 저장
        placeRepository.save(place);


    }
    //controller에서 알아낸 station,category값으로 top5가져오기
    public List<Place> TOP5(Long stationId,Long categoryId){
        return placeRepository.findTOP5(stationId,categoryId);
    }

    //현재 보이는 top5의 placeId를 이용해서 vlog목록 가져오기
    //youtube 다음 칸으로 넘길 때 마다 i+=3하기
    public List<Vlog> findVlog(Long placeId,int i){
        Place place=placeRepository.findOne(placeId);
        return place.getVlogList(i);
    }
}
