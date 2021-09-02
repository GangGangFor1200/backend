package com.example.ganggang2.service;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void CategorySave(Category category){
        categoryRepository.save(category);
    }
}
