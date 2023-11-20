package es.gestionescolar.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

	@Bean
    public UserDetailsManager usersCustom(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username,password,estatus from usuarios u where username=?");
        users.setAuthoritiesByUsernameQuery("select u.username,p.perfil from usuarioperfil up "
                + "inner join usuarios u on u.id = up.idUsuario "
                + "inner join perfiles p on p.id = up.idPerfil "
                + "where u.username=?");
        return users;
    }
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests()
	            // Los recursos estáticos no requieren autenticación
	            .requestMatchers("/bootstrap/**", "/tinymce/**").permitAll()
	            // Las vistas públicas no requieren autenticación
	            .requestMatchers("/", "/signup").permitAll()
	            .requestMatchers("/alumnos/**", "/asignaturas/**", "/cursos/**", "/notas/**", "/profesores/**", "/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
	            .anyRequest().authenticated()
	            .and().formLogin().loginPage("/login").permitAll()
	            .defaultSuccessUrl("/",true);
	    return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
}
