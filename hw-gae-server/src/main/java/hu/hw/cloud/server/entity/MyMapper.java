/**
 * 
 */
package hu.hw.cloud.server.entity;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.Address;
import hu.hw.cloud.shared.dto.common.AccountDto;

/**
 * @author robi
 *
 */
public class MyMapper extends ModelMapper {
	private static final Logger logger = LoggerFactory.getLogger(MyMapper.class);

	public class AccountRefMap extends PropertyMap<AccountDto, Account> {

		protected void configure() {
			logger.info("AccountRefMap");
			map().setAddress(MyMapper.super.map(source.getAddress(), Address.class));
			map().setId(source.getId());
			map().setName(source.getName());
			map().setVersion(source.getVersion());
			map().setWebSafeKey(source.getWebSafeKey());
		}
	};

	public MyMapper() {

		Converter<Ref<Account>, AccountDto> toAccountDto = new AbstractConverter<Ref<Account>, AccountDto>() {
			@Override
			protected AccountDto convert(Ref<Account> source) {
				logger.info("toAccountDto.convert()");
				Account account = source.get();
				AccountDto accountDto = new AccountDto();
				accountDto.setId(account.getId());
				accountDto.setName(account.getName());
				accountDto.setVersion(account.getVersion());
				accountDto.setWebSafeKey(account.getWebSafeKey());
				return accountDto;
			}
		};

//		addConverter(toAccountDto);

		Converter<AccountDto, Ref<Account>> toAccountRef = new AbstractConverter<AccountDto, Ref<Account>>() {
			@Override
			protected Ref<Account> convert(AccountDto source) {
				logger.info("toAccountRef.convert()->source=" + source);
				Account account = map(source, Account.class);
				return Ref.create(account);
			}
		};

//		addConverter(toAccountRef);
		
//		addMappings(new AccountRefMap());
		
//		createTypeMap(AppUserDto.class, AppUser.class);

//		validate();
	}

	@Override
	public <D> D map(Object source, Class<D> destinationType) {

		logger.info("map()->source=" + source);

		return super.map(source, destinationType);
	}

}
