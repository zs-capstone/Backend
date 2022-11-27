package uos.capstone.backend.survey.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.survey.domain.Survey;
import uos.capstone.backend.user.domain.User;

public interface SurveyRepository extends JpaRepository<Survey,Long>, SurveyRepositoryCustom {
	void deleteAllByUser(User user);
	Boolean existsByUser(User user);
}
