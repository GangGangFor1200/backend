package ganggang3.gang.Service;


import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.Repository.CityRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.domain.*;
import ganggang3.gang.dto.PlaceDto;
import ganggang3.gang.dto.VlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public Place findById(long placeId){
        Optional<Place> place=placeRepository.findById(placeId);
        return place.get();
    }

    public long find(long placeId) {
        Optional<Place> place=placeRepository.findById(placeId);
        return place.get().getId();
    }

    public List<Place> findPlaceList(long cityId, long categoryId) {
        Optional<City> city=cityRepository.findById(cityId);
        Optional<Category> category=categoryRepository.findById(categoryId);
        return placeRepository.findByCityAndCategory(city.get(),category.get());
    }

    public List<PlaceVlog> findPlaceVlogList(long placeId) {
        Optional<Place> place=placeRepository.findById(placeId);
        return place.get().getPlace_vlogList();
    }

    public List<Vlog> findVlogList(long placeId) {
        List<PlaceVlog> placeVlogList=findPlaceVlogList(placeId);
        List<Vlog> vlogList=new ArrayList<>();
        if (placeVlogList != null) {
            for (int i = 0; i < placeVlogList.size(); i++) {
                PlaceVlog placeVlog = placeVlogList.get(i);
                vlogList.add(placeVlog.getVlog());
            }
        }
        return vlogList;
    }

    //위에꺼 다 tdd에서 사용하는 메소드들이에용


    //5보다 place적을 때, 예외 추가하기
    public List<Place> findTop5FromDb(long cityId, long categoryId) {
        Optional<City> city=cityRepository.findById(cityId);
        Optional<Category> category=categoryRepository.findById(categoryId);
        List<Place> placeList=placeRepository.findByCityAndCategory(city.get(),category.get());
        Collections.sort(placeList, new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return Long.compare(o2.getPlace_vlogList().size(),o1.getPlace_vlogList().size());
            }
        });
        return placeList.size()>=5 ? placeList.subList(0,5):placeList.subList(0,placeList.size());
    }


    public List<PlaceDto> getTop5(long city_id, long category_id) {
        List<Place> findTop5 = findTop5FromDb(city_id, category_id);
        List<PlaceDto> placeDtoList = new ArrayList<>();
        findTop5.forEach(p -> {
            //Top5 place 의 vlog_list
            List<VlogDto> vlog_list = getVlogList(p.getPlace_vlogList());
            //Place Dto 할당
            PlaceDto pd = PlaceDto.of(p, vlog_list);
            //리스트에 할당
            placeDtoList.add(pd);
        });

        return placeDtoList;
    }

    private List<VlogDto> getVlogList(List<PlaceVlog> p_v_list) {
        List<VlogDto> list = new ArrayList<>();
        for(int j = 0; j< p_v_list.size(); j++){
            PlaceVlog pv = p_v_list.get(j);
            Vlog v = pv.getVlog();
            //Top5 place의 Vlog DTO를 할당
            list.add(
                    new VlogDto(
                            v.getName(),
                            v.getUrl()
                    ));
        }
        return list;
    }

}
