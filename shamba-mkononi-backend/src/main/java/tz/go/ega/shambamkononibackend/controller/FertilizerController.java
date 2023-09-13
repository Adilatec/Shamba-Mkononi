package tz.go.ega.shambamkononibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tz.go.ega.shambamkononibackend.model.Fertilizer;
import tz.go.ega.shambamkononibackend.payload.dto.FertilizerDto;
import tz.go.ega.shambamkononibackend.payload.response.Response;
import tz.go.ega.shambamkononibackend.service.FertilizerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class FertilizerController {
    @Autowired
    private FertilizerService fertilizerService;

    @PostMapping("add-fertilizer")
    public Response<String> addFertilizer(@RequestBody FertilizerDto body){
        return fertilizerService.addFertilizer(body);
    }

    @GetMapping("all-fertilizer")
    List<Fertilizer> getAllFertilizer(){
        return fertilizerService.getAllFertilizer();
    }
    @GetMapping("code-fertilizer/{code}")
    Response<Fertilizer> getFertilizerWithCode(@PathVariable String code){
        return fertilizerService.getFertilizerByCode(code);
    }
}
