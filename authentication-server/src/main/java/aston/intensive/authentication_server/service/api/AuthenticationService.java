package aston.intensive.authentication_server.service.api;

import aston.intensive.authentication_server.dto.UserDto;

public interface AuthenticationService {

    String authenticate(UserDto userDto);
}
