package com.barisd.controller;


import static com.barisd.constant.EndPoints.*;

import com.barisd.constant.EndPoints;
import com.barisd.repository.entity.Yetki;
import com.barisd.service.YetkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+YETKI)
public class YetkiController {
    private final YetkiService yetkiService;

    @PostMapping(SAVE)
    public ResponseEntity<Yetki> save(@RequestBody Yetki yetki){
        return ResponseEntity.ok(yetkiService.save(yetki));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Yetki>> findAll() {
        return ResponseEntity.ok(yetkiService.findAll());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public String adminarea(){
        return "Burası adminlere özel alandır";
    }

    @GetMapping("/viparea")
    public String viparea(){
        return "Burası VIPlere özel alandır";
    }



}
