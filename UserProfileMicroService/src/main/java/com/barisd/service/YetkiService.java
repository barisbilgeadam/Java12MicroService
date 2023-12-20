package com.barisd.service;

import com.barisd.repository.YetkiRepository;
import com.barisd.repository.entity.Yetki;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YetkiService {
    private final YetkiRepository yetkiRepository;

    public Yetki save(Yetki yetki){
       return yetkiRepository.save(yetki);
    }

    public List<Yetki> findAll(){
        return yetkiRepository.findAll();
    }

    public List<Yetki> findAllByUserprofileid(String userprofileid){
        return yetkiRepository.findAllByUserprofileid(userprofileid);
    }
}
