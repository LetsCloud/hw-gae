package hu.hw.cloud.server.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;

/**
 * Egy standard web alkalmazásban a nem autentikált kliens védett erőforrás
 * lekérése automatikusan kiváltja az autentikációs folyamatot. Ez a login
 * oldalra átirányítással valósul meg, a felhasználó bejelentkezési adatainak
 * megadása céljából.
 * <p>
 * Azonban a REST web szolgáltatások esetében erre nincs szükség, mert az
 * autentikáció korrekt URI megadásával lehetséges. Eltérő esetben a kérés
 * sikertelen 401 UNAUTHORIZED státusszal.
 * <p>
 * A Spring Security az Entry Point koncepcióval oldja meg az utentikációs
 * folyamat automatikus kiváltását, amely kötelező része a konfigurációnak és
 * http elem entry-point-ref atribútumával állítható be. Mivel ezen funkciónak
 * nincs értelme a REST web szolgáltatás esetében, egy új saját entry point
 * definiálására van szükség, amely 401-es kódot ad vissza amikor meghívásra
 * kerül.
 * 
 * @author CR
 *
 */
//@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class.getName());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		LOGGER.info("commence");

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}
}
