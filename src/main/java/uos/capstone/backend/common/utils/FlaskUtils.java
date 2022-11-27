package uos.capstone.backend.common.utils;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.dto.response.EvalResponse;
import uos.capstone.backend.common.dto.response.ListEvalResponse;
import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
public class FlaskUtils {
	private final UserRepository userRepository;
	public ListEvalResponse evaluate(Long userId, NoteCreateRequest noteCreateRequest) {
		final String username = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException()).getNickname();
		final Integer day = noteCreateRequest.getDayEnd().compareTo(noteCreateRequest.getDayStart()) +1;
		final Integer n = noteCreateRequest.getMaxPlacePerDay();
		final List<Long> orgPlaceList= noteCreateRequest.getPlaceList();

		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		// httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		JSONObject body = new JSONObject();
		body.put("orgPlaceList", orgPlaceList);
		body.put("username", username);
		body.put("day", day);
		body.put("n", n);

		HttpEntity<String> entity = new HttpEntity<String>(body.toString(), httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:6000/eval";
		ResponseEntity<ListEvalResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, ListEvalResponse.class);

		return responseEntity.getBody();
	}

	public ListEvalResponse reevaluate(Long userId, Note note, List<Long> orgPlaceList) {
		final String username = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException()).getNickname();
		final Integer day = note.getDayEnd().compareTo(note.getDayStart()) +1;
		final Integer n = note.getMaxPlacePerDay();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		JSONObject body = new JSONObject();
		body.put("orgPlaceList", orgPlaceList);
		body.put("username", username);
		body.put("day", day);
		body.put("n", n);

		HttpEntity<String> entity = new HttpEntity<String>(body.toString(), httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:6000/eval";
		ResponseEntity<ListEvalResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, ListEvalResponse.class);

		return responseEntity.getBody();
	}

}
