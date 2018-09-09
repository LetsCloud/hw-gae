/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CHAT;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ADD_POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.server.entity.chat.ChatPost;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.ChatService;
import hu.hw.cloud.shared.dto.chat.AddPostDto;
import hu.hw.cloud.shared.dto.chat.ChatDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@Controller
@RequestMapping(value = ROOT + CHAT, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	private final ChatService chatService;

	private final AppUserService appUserService;

	private final ModelMapper modelMapper;

	@Autowired
	ChatController(ChatService chatService, AppUserService appUserService, ModelMapper modelMapper) {
		this.chatService = chatService;
		this.appUserService = appUserService;
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<ChatDto>> list() {
		List<ChatDto> dtos = new ArrayList<ChatDto>();

		AppUser appUser = appUserService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<ChatDto>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<ChatDto>>(dtos, OK);

		for (Chat entity : chatService.getAll(accountWebSafeKey)) {
			dtos.add(modelMapper.map(entity, ChatDto.class));
		}
		return new ResponseEntity<List<ChatDto>>(dtos, OK);
	}

	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public @ResponseBody ResponseEntity<ChatDto> getByKey(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("getByKey()->webSafeKey=" + webSafeKey);
		Chat entity;
		try {
			entity = chatService.read(webSafeKey);
			logger.info("getByKey()->entity.getWebSafeKey()=" + entity.getWebSafeKey());
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
		ChatDto dto = modelMapper.map(entity, ChatDto.class);
		return new ResponseEntity<ChatDto>(dto, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<ChatDto> create(@RequestBody ChatDto dto) throws RestApiException {
		logger.info("create()");
		try {
			Chat entity = chatService.create(modelMapper.map(dto, Chat.class));
			dto = modelMapper.map(entity, ChatDto.class);
			return new ResponseEntity<ChatDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = ADD_POST, method = POST)
	public ResponseEntity<ChatDto> addPost(@RequestBody AddPostDto postDto) throws RestApiException {
		logger.info("create()");
		try {
			ChatPost post = modelMapper.map(postDto, ChatPost.class);
			Chat entity = chatService.addPost(postDto.getChatWebSafeKey(), post);
			ChatDto dto = modelMapper.map(entity, ChatDto.class);
			return new ResponseEntity<ChatDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

}
