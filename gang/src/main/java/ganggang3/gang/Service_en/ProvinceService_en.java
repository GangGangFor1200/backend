package ganggang3.gang.Service_en;

import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Repository_en.ProvinceRepository_en;
import ganggang3.gang.domain.Province;
import ganggang3.gang.domain_en.ProvinceEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProvinceService_en {

    private final ProvinceRepository_en provinceRepository;

    public List<ProvinceEn> findAll(){
        return provinceRepository.findAll();
    }


}
