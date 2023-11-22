package com.fastcampus.mini9.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fastcampus.mini9.config.security.exception.AjaxAuthenticationEntryPoint;
import com.fastcampus.mini9.config.security.filter.AjaxAuthenticationFilter;
import com.fastcampus.mini9.config.security.filter.AjaxAuthenticationFilterConfigurer;
import com.fastcampus.mini9.config.security.filter.JwtAuthenticationFilter;
import com.fastcampus.mini9.config.security.handler.AjaxAuthenticationFailureHandler;
import com.fastcampus.mini9.config.security.handler.AjaxAuthenticationSuccessHandler;
import com.fastcampus.mini9.config.security.provider.AjaxAuthenticationProvider;
import com.fastcampus.mini9.config.security.provider.JwtProvider;
import com.fastcampus.mini9.config.security.service.AjaxUserDetailService;
import com.fastcampus.mini9.config.security.service.RefreshTokenService;
import com.fastcampus.mini9.config.security.token.AuthenticationDetails;
import com.fastcampus.mini9.config.security.token.UserPrincipal;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${remote-server.front.url}")
	private String frontUrl;
	@Value("${remote-server.gateway.url}")
	private String gatewayUrl;
	private String frontUrlLocal = "http://localhost:3000";

	private static final String loginProcUrl = "/api/login";

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private MemberRepository memberRepository;

	private static final String[] SWAGGER_PAGE = {
		"/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
		"/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic(HttpBasicConfigurer::disable)
			.formLogin(FormLoginConfigurer::disable)
			.csrf(CsrfConfigurer::disable)
			.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
				.configurationSource(corsConfigurationSource()));

		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(SWAGGER_PAGE).permitAll()
				.requestMatchers("/error/**").permitAll()
//				.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/products")).permitAll()
				.requestMatchers("/api/login").permitAll()
				.requestMatchers("/api/sign-up").permitAll()
				.anyRequest().authenticated());
		http
			.apply(
				new AjaxAuthenticationFilterConfigurer(new AjaxAuthenticationFilter(loginProcUrl), loginProcUrl))
			.setAuthenticationManager(authenticationManager())
			.successHandlerAjax(new AjaxAuthenticationSuccessHandler(jwtProvider, refreshTokenService))
			.failureHandlerAjax(new AjaxAuthenticationFailureHandler())
			.setAuthenticationDetailsSource(authenticationDetailsSource())
			.loginProcessingUrl(loginProcUrl);

		http
			.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), authenticationDetailsSource()),
				AnonymousAuthenticationFilter.class);

		http
			.anonymous((anonymous) -> anonymous
				.principal(new UserPrincipal(null, "anonymous")));

		http
			.exceptionHandling((exceptionHandling) -> exceptionHandling
				.authenticationEntryPoint(new AjaxAuthenticationEntryPoint()));

		http
			.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
				.frameOptions(FrameOptionsConfig::sameOrigin));

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.addAllowedOriginPattern("*");
		//        corsConfiguration.addAllowedOrigin(frontUrl);
		//        corsConfiguration.addAllowedOrigin(gatewayUrl);
		//        corsConfiguration.addAllowedOrigin(frontUrlLocal);
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}

	@Bean
	public SecurityContextRepository securityContextRepository() {
		return new NullSecurityContextRepository();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationDetailsSource<HttpServletRequest,
		WebAuthenticationDetails> authenticationDetailsSource() {
		return context -> new AuthenticationDetails(context);
	}

	@Bean
	public AjaxAuthenticationProvider authenticationProvider(AuthenticationManagerBuilder auth,
		JwtProvider jwtProvider) {
		AjaxAuthenticationProvider ajaxAuthenticationProvider = new AjaxAuthenticationProvider(
			ajaxUserDetailService(), passwordEncoder());
		auth.authenticationProvider(ajaxAuthenticationProvider).authenticationProvider(jwtProvider);
		return ajaxAuthenticationProvider;
	}

	@Bean
	public AjaxUserDetailService ajaxUserDetailService() {
		return new AjaxUserDetailService(memberRepository);
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
