package uos.capstone.backend.survey.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.PlaceReview;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.domain.repository.PlaceReviewRepository;
import uos.capstone.backend.place.exception.PlaceNotExistException;
import uos.capstone.backend.survey.domain.Survey;
import uos.capstone.backend.survey.domain.mapper.SurveyCreateMapper;
import uos.capstone.backend.survey.domain.repository.SurveyRepository;
import uos.capstone.backend.survey.dto.request.SurveyCreateRequest;
import uos.capstone.backend.survey.dto.request.SurveyInitRating;
import uos.capstone.backend.survey.dto.response.SurveyCreateResponse;
import uos.capstone.backend.survey.exception.SurveyExistsException;
import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

	private final SurveyRepository surveyRepository;
	private final UserRepository userRepository;
	private final PlaceRepository placeRepository;
	private final PlaceReviewRepository placeReviewRepository;

	public List<SurveyCreateResponse> giveRandomSurvey(Long userId) {
		validateSurvey(userId);

		return surveyRepository.findRandom10Place();
	}

	private void validateSurvey(Long userId) {
		if (surveyRepository.existsByUser(userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException()))) {
			throw new SurveyExistsException();
		}
	}

	@Transactional
	public void save(Long userId, SurveyCreateRequest surveyCreateRequest) {
		validateSurvey(userId);

		List<Survey> surveyList = new ArrayList<>();

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException());

		for (SurveyInitRating surveyInitRating : surveyCreateRequest.getSurveyInitRatingList()){
			Place place = placeRepository.findById(surveyInitRating.getPlaceId())
				.orElseThrow(() -> new PlaceNotExistException());

			surveyList.add(SurveyCreateMapper.INSTANCE.toEntity(place, surveyInitRating.getRate(), user));
		}

		surveyRepository.saveAll(surveyList);

		List<PlaceReview> placeReviewList = surveyList.stream()
			.map(x -> {return PlaceReview.builder()
				.place(x.getPlace())
				.user(x.getUser())
				.rate(x.getRate())
				.username(x.getUser().getNickname())
				.build();})
			.collect(Collectors.toList());

		placeReviewRepository.saveAll(placeReviewList);
	}

	@Transactional
	public void deleteById(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException());

		surveyRepository.deleteAllByUser(user);
		placeReviewRepository.deleteAllByUser(user);
	}

}
