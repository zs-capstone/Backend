package uos.capstone.backend.common.config.jwt.handler;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import uos.capstone.backend.common.exception.auth.ExceptionCode;

// bean으로 등록됨
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ExceptionCode exception = (ExceptionCode) request.getAttribute("exception");

        // 토큰이 없을 경우
        if(exception.equals(ExceptionCode.NO_TOKEN)) {
            set403Response(response, ExceptionCode.NO_TOKEN);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(ExceptionCode.WRONG_TYPE_TOKEN)) {
            set403Response(response, ExceptionCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception.equals(ExceptionCode.EXPIRED_TOKEN)) {
            set401Response(response, ExceptionCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(ExceptionCode.UNSUPPORTED_TOKEN)) {
            set403Response(response, ExceptionCode.UNSUPPORTED_TOKEN);
        }
        // 토큰이 잘못되었을 경우
        else if (exception.equals(ExceptionCode.WRONG_TOKEN)) {
            set403Response(response, ExceptionCode.WRONG_TOKEN);
        }
    }


    private void set400Response(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(responseJson);
    }

    private void set401Response(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(responseJson);
    }

    private void set403Response(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(responseJson);
    }

}
