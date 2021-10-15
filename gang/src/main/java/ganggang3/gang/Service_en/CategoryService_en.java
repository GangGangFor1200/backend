package ganggang3.gang.Service_en;

import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.domain.CategoryEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService_en {

    private final CategoryRepository categoryRepository;

//    private final JpaRepository jpaRepository;

    public List<CategoryEn> findAll(){
        return categoryRepository.findAll();
    }

}
