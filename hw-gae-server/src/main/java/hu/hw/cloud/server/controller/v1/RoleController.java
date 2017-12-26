package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.ArrayList;
import java.util.List;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROLE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.hw.cloud.shared.dto.common.RoleDto;

@Controller
@RequestMapping(value = ROOT + ROLE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);

	@RequestMapping(value = "", method = GET)
	public List<RoleDto> list() {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		/*
		 * UserDto userDto = userService.getCurrentUser(); if (userDto == null)
		 * return userDtos; if (userDto.getAccountWebSafeKey() == null) return
		 * userDtos;
		 * 
		 * for (User user:
		 * userService.getAllByAccountKey(userDto.getAccountWebSafeKey())) {
		 * userDtos.add(User.createDto(user)); }
		 */
		return dtos;
	}

	@RequestMapping(method = POST)
	public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto) {
		/*
		 * try { LOGGER.info("create->try->userDto=" + userDto); User user =
		 * userService.create(userDto); userDto = User.createDto(user); return
		 * new ResponseEntity<UserDto>(userDto, OK); } catch (Throwable e) {
		 * LOGGER.info("create->catch (Throwable e)"); e.printStackTrace();
		 * return new ResponseEntity<UserDto>(NOT_FOUND); }
		 */
		return new ResponseEntity<RoleDto>(dto, OK);
	}

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<RoleDto> read(@PathVariable String id) throws Throwable {
		/*
		 * User user = userService.read(id); UserDto userDto =
		 * User.createDto(user); return new ResponseEntity<UserDto>(userDto,
		 * OK);
		 */
		return new ResponseEntity<RoleDto>(new RoleDto(), OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<RoleDto> update(@RequestBody RoleDto dto) {
		/*
		 * try { LOGGER.info("update->try->userDto=" + userDto); User user =
		 * userService.update(userDto); userDto = User.createDto(user); return
		 * new ResponseEntity<UserDto>(userDto, OK); } catch (Throwable e) {
		 * LOGGER.info("create->catch (Throwable e)"); e.printStackTrace();
		 * return new ResponseEntity<UserDto>(NOT_FOUND); }
		 */
		return new ResponseEntity<RoleDto>(dto, OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public void delete(@PathVariable String id) {
		// TODO Auto-generated method stub

	}
}
