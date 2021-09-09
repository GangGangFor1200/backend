package ganggang3.gang.Service;

import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.domain.Category;
import ganggang3.gang.domain.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
