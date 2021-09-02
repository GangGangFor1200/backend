package com.example.ganggang2.service;

import com.example.ganggang2.domain.Vlog;
import com.example.ganggang2.repository.VlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VlogService {

    private final VlogRepository vlogRepository;

    public void Vlogsave(Long placeId){
        //place 조회

        Vlog vlog=new Vlog();
        //repo에 저장
        vlogRepository.save(vlog);
    }
}
