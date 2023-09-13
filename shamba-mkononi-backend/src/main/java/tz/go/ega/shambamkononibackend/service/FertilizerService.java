package tz.go.ega.shambamkononibackend.service;

import tz.go.ega.shambamkononibackend.model.Fertilizer;
import tz.go.ega.shambamkononibackend.payload.dto.FertilizerDto;
import tz.go.ega.shambamkononibackend.payload.response.Response;

import java.util.List;

public interface FertilizerService {
    Response<String> addFertilizer(FertilizerDto fertilizerDto);
    List<Fertilizer> getAllFertilizer();
    Response<Fertilizer> getFertilizerByCode(String code);
}
