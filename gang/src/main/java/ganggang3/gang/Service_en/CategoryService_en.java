package ganggang3.gang.Service_en;

import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.Repository_en.CategoryRepository_en;
import ganggang3.gang.domain.Category;
import ganggang3.gang.domain_en.CategoryEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService_en {

    private final CategoryRepository_en categoryRepository;

//    private final JpaRepository jpaRepository;

    public List<CategoryEn> findAll(){
        return categoryRepository.findAll();
    }

}
