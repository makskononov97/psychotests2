package psychotests.api.controller;


import org.springframework.web.bind.annotation.*;
import psychotests.api.dto.AckDTO;
//import psychotests.api.dto.PsychologistDTO;
import psychotests.api.exception.BadRequestException;
import psychotests.api.exception.NotFoundException;
//import psychotests.api.factory.PsychologistDTOFactory;
import psychotests.entity.SchoolClassEntity;
import psychotests.entity.SchoolEntity;
import psychotests.entity.PsychologistEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import psychotests.repository.SchoolClassRepository;
import psychotests.repository.PsychologistRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class PsychologistController {

//    psychologistRepository PsychologistRepository;
//
//    psychologistDTOFactory PsychologistDTOFactory;
//
//    SchoolClassRepository schoolClassRepository;
//
//
//    public static final String FETCH_PSYCHOLOGISTS = "/api/schools/classes/psychologists";
//    public static final String CREATE_PSYCHOLOGIST = "/api/schools/classes/{classId}/psychologists";
//    public static final String DELETE_PSYCHOLOGIST = "/api/schools/classes/psychologists/{psychologistId}";
//
//    @GetMapping(FETCH_PSYCHOLOGISTS)
//    public ResponseEntity<List<PsychologistDTO>> fetchPsychologists(String filter) {
//
//        boolean isFiltered = !filter.trim().isEmpty();
//
//        List<PsychologistEntity> Psychologists = PsychologistRepository.findAllByFilter(isFiltered, filter);
//
//        return ResponseEntity.ok(PsychologistDTOFactory.createPsychologistDTOList(Psychologists));
//    }
//
//    @PostMapping(CREATE_PSYCHOLOGIST)
//    public ResponseEntity<PsychologistDTO> createPsychologist(
//            @RequestParam String fullName,
//            @RequestParam Instant birthday,
//            @RequestParam Enum role,
//            @RequestParam Long classId) {
//
//        SchoolClassEntity schoolClass = schoolClassRepository
//                .findById(classId)
//                .orElseThrow(() ->
//                        new NotFoundException(String.format("Class with this ID not found", classId)));
//
//        if(PsychologistRepository.existsByName(fullName)) {
//            throw new BadRequestException(String.format("Psychologist with name \"%s\" already exists", fullName));
//        }
//
//        PsychologistEntity Psychologist = PsychologistRepository.saveAndFlush(
//                PsychologistEntity.makeDefault(fullName, birthday, role, schoolClass)
//        );
//
//        return ResponseEntity.ok(PsychologistDTOFactory.createPsychologistDTO(Psychologist));
//    }
//
//    @DeleteMapping(DELETE_PSYCHOLOGIST)
//    public ResponseEntity<AckDTO> deletePsychologist(@PathVariable Long PsychologistId) {
//
//        if (PsychologistRepository.existsById(PsychologistId)) {
//            PsychologistRepository.deleteById(PsychologistId);
//        }
//
//        return ResponseEntity.ok(AckDTO.makeDefault(true));
//    }

}
