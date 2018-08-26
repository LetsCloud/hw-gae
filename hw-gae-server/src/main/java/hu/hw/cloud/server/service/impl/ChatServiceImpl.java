/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.server.entity.chat.ChatPost;
import hu.hw.cloud.server.entity.chat.FcmToken;
import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ChatRepository;
import hu.hw.cloud.server.service.ChatService;
import hu.hw.cloud.server.service.impl.fcm.FcmService2;
import hu.hw.cloud.shared.dto.NotificationDto;
import hu.hw.cloud.shared.dto.chat.ChatDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public class ChatServiceImpl extends CrudServiceImpl<Chat, ChatDto, ChatRepository> implements ChatService {
	private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

	private final ChatRepository repository;
	private final AccountRepository accountRepository;

	public ChatServiceImpl(ChatRepository repository, AccountRepository accountRepository) {
		super(repository);
		this.repository = repository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Chat> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		return repository.getByAccount(account);
	}

	@Override
	public Chat create(Chat dto) throws Throwable {
		Chat entiy = super.create(dto);
		notifyReceivers(entiy.getSender(), entiy, false);
		return entiy;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}

	@Override
	public Chat addPost(String chatKey, ChatPost post) throws EntityValidationException, UniqueIndexConflictException {
		Date updated = new Date();
		Chat chat = repository.findByWebSafeKey(chatKey);
		post.setCreated(updated);
		chat.getPosts().add(post);
		chat.setUpdated(updated);
		chat = repository.save(chat);
		notifyReceivers(chat.getSender(), chat, true);
		return chat;
	}

	public void notifyReceivers(AppUser sender, Chat chat, Boolean isAddPost) {
		logger.info("notifyReceivers()");

		NotificationDto nd = new NotificationDto(sender.getName(),
				chat.getPosts().get(chat.getPosts().size() - 1).getMessage(), sender.getPicture(),
				chat.getUrl() + chat.getWebSafeKey());

		List<String> tokens = new ArrayList<String>();

		// Sima postolás esetén a chatet inditó tokenjét is begyűjtjük
		if (isAddPost) {
			logger.info("notifyReceivers()->chatStarter=" + chat.getSender().getName());
			for (FcmToken chatStarterToken : chat.getSender().getFcmTokens()) {
				if (!tokens.contains(chatStarterToken.getToken()))
					tokens.add(chatStarterToken.getToken());
			}
		}

		// Majd az értesítettek tokenjeit is begyűjtjük
		for (AppUser receiver : chat.getReceivers()) {
			logger.info("notifyReceivers()->receiver=" + receiver.getName());
			for (FcmToken receiverToken : receiver.getFcmTokens()) {
				if (!tokens.contains(receiverToken.getToken()))
					tokens.add(receiverToken.getToken());
			}
		}

		// Végül kivesszük a sender token-ét
		logger.info("notifyReceivers()->sender=" + sender.getName());
		for (FcmToken senderToken : sender.getFcmTokens())
			tokens.remove(senderToken.getToken());

		for (String token : tokens) {
			logger.info("notifyReceivers()->token=" + token);
			FcmService2.send_FCM_Notification(token, nd);
		}
	}

}
