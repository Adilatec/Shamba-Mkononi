package tz.go.ega.shambamkononibackend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.go.ega.shambamkononibackend.model.Fertilizer;
import tz.go.ega.shambamkononibackend.payload.dto.FertilizerDto;
import tz.go.ega.shambamkononibackend.payload.response.Response;
import tz.go.ega.shambamkononibackend.repositories.FertilizerRepository;
import tz.go.ega.shambamkononibackend.service.FertilizerService;
import tz.go.ega.shambamkononibackend.util.ResponseCode;

import java.util.List;
import java.util.Optional;
@Service
public class FertilizerServiceImpl implements FertilizerService {
    @Autowired
    private FertilizerRepository fertilizerRepository;

    @Override
    public Response<String> addFertilizer(FertilizerDto fertilizerDto) {
        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setName(fertilizerDto.getName());
        fertilizer.setCode(fertilizerDto.getCode());
        fertilizerRepository.save(fertilizer);
        return new Response<>(false, ResponseCode.SUCCESS, "SUCCESSFUL");
    }

    @Override
    public List<Fertilizer> getAllFertilizer() {
        return fertilizerRepository.findAll();
    }

    @Override
    public Response<Fertilizer> getFertilizerByCode(String code) {
        Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findByCode(code);
        if (optionalFertilizer.isPresent()){
            return new Response<>(false, ResponseCode.SUCCESS, optionalFertilizer.get());
        }
        return new Response<>(true, ResponseCode.FAIL, "Does Not Exist");
    }
}
