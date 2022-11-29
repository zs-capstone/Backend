package uos.capstone.backend.survey.domain.repository;

import java.util.List;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.survey.dto.response.SurveyCreateResponse;

public interface SurveyRepositoryCustom {

	List<SurveyCreateResponse> findRandom20Place();

}
