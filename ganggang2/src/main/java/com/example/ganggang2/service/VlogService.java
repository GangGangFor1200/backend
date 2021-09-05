package com.example.ganggang2.service;

import com.example.ganggang2.domain.Place;
import com.example.ganggang2.domain.Place_Vlog;
import com.example.ganggang2.domain.Vlog;
import com.example.ganggang2.repository.PlaceRepository;
import com.example.ganggang2.repository.Place_VlogRepository;
import com.example.ganggang2.repository.VlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VlogService {

    private final VlogRepository vlogRepository;
    private final Place_VlogRepository place_vlogRepository;
    private final PlaceRepository placeRepository;

    //값이 들어올 때 어떻게 들어올지 몰라서 일단 placelist로 해놨는데 수정하면 돼용
    public void createVlog(String url, String name, List<Place> placeList){
        //vlog 만들기
        Vlog vlog=new Vlog();
        vlog.setName(name);
        vlog.setUrl(url);

        for(int i=0;i<placeList.size();i++) {
            Place place;
            if (placeRepository.findName(placeList.get(i).getName()).size()>0){//place가 이미 있을 경우
                place=placeList.get(i);
                place.Countplus();
            }
            else {
                //place 만들기
                place = new Place();
                //place에 값 넣기 -> 값이 어떻게 들어올지 모르겠땅

            }
            //place_vlog 만들기
            Place_Vlog place_vlog=Place_Vlog.createPlace_Vlog(place,vlog);
            place_vlogRepository.save(place_vlog);

        }


        //repo에 저장
        vlogRepository.save(vlog);
    }
}
