package ganggang3.gang.Service_en;

import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.Repository.CityRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.Repository_en.CategoryRepository_en;
import ganggang3.gang.Repository_en.CityRepository_en;
import ganggang3.gang.Repository_en.PlaceRepository_en;
import ganggang3.gang.domain.*;
import ganggang3.gang.domain_en.*;
import ganggang3.gang.dto.PlaceDto;
import ganggang3.gang.dto.VlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService_en {

    private final PlaceRepository_en placeRepository;
    private final CategoryRepository_en categoryRepository;
    private final CityRepository_en cityRepository;

    public PlaceEn findById(Long placeId){
        Optional<PlaceEn> place=placeRepository.findById(placeId);
        return place.orElseThrow(()->new NoSuchElementException("place가 존재하지 않습니다"));
    }

    public List<PlaceEn> findTop5FromDb(Long cityId, Long categoryId) {
        Optional<CityEn> city=cityRepository.findById(cityId);
        Optional<CategoryEn> category=categoryRepository.findById(categoryId);
        CityEn city1 = city.orElseThrow(() -> new NoSuchElementException("city가 존재하지 않습니다"));
        CategoryEn category1=category.orElseThrow(()->new NoSuchElementException("categoty가 존재하지 않습니다"));
        List<PlaceEn> placeList=placeRepository.findByCityAndCategory(city1,category1);
        Collections.sort(placeList, new Comparator<PlaceEn>() {
            @Override
            public int compare(PlaceEn o1, PlaceEn o2) {
                return Long.compare(o2.getPlace_vlogList().size(),o1.getPlace_vlogList().size());
            }
        });
        return placeList.size()>=5 ? placeList.subList(0,5):placeList.subList(0,placeList.size());
    }


    public List<PlaceDto> getTop5(Long city_id, Long category_id) {
        List<PlaceEn> findTop5 = findTop5FromDb(city_id, category_id);
        List<PlaceDto> placeDtoList = new ArrayList<>();
        findTop5.forEach(p -> {
            //Top5 place 의 vlog_list
            List<VlogDto> vlog_list = getVlogList(p.getPlace_vlogList());
            //Place Dto 할당
            PlaceDto pd = new PlaceDto(
                    p.getId(),
                    p.getName(),
                    p.getLocation_x(),
                    p.getLocation_y(),
                    p.getAddress(),
                    p.getPhone(),
                    p.getPlaceUrl(),
                    p.getImage(),
                    vlog_list
            );

            //리스트에 할당
            placeDtoList.add(pd);
        });

        return placeDtoList;
    }

    private List<VlogDto> getVlogList(List<PlaceVlogEn> p_v_list) {

        List<VlogDto> list = new ArrayList<>();
        for(int j = 0; j< p_v_list.size(); j++){
            PlaceVlogEn pv = p_v_list.get(j);
            VlogEn v = pv.getVlog();
            //Top5 place의 Vlog DTO를 할당
            list.add(
                    new VlogDto(
                            v.getId(),
                            v.getUrl()
                    ));
        }
        return list;
    }

}
