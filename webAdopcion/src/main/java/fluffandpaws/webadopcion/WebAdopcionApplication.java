package fluffandpaws.webadopcion;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import java.util.Collections;

@EnableCaching//habilita la funcionalidad de la caché
@SpringBootApplication
@EnableHazelcastHttpSession//para hacer el inicio de sesion con hazelcast
public class WebAdopcionApplication {

	private static final Log LOG = LogFactory.getLog(WebAdopcionApplication.class);//nos permite imprimir mensajes de información en este caso
	public static void main(String[] args) {
		SpringApplication.run(WebAdopcionApplication.class, args);
	}
/////Parte para Caché///////////////////////////////////
	@Primary
	@Bean
	public CacheManager defaultCacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public CacheManager animalesCacheManager() {//administrar la caché de animales
		LOG.info("Activando caché para animales");
		return new ConcurrentMapCacheManager("animales");
	}

	@Bean
	public CacheManager protectorasCacheManager() {
		LOG.info("Activando caché para protectoras");
		return new ConcurrentMapCacheManager("protectoras");
	}

	@Bean
	public CacheManager mensajesCacheManager() {
		LOG.info("Activando caché para mensajes");
		return new ConcurrentMapCacheManager("mensajes");
	}
	@Bean
	public CacheManager usuariosCacheManager(){
		LOG.info("Activando caché para usuarios");
		return new ConcurrentMapCacheManager("usuarios");

	}

	/////////////////Parte para Hazelcast asi se puede monitorizar lo de la caché/////////////////////////////7

	@Bean
	public Config config() {//el método que ña contiene es un bean por lo que debe de ser administrado por el contenedor de spring

		Config config = new Config();//clase de hazelcast que se usa para configurar y personalizar una isntancia hazelcats

		JoinConfig joinConfig = config.getNetworkConfig().getJoin();

		joinConfig.getMulticastConfig().setEnabled(false);//desactivamos multicast
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));//permite la comunicacion entre dispositivos a través de una red, hazelcast solo se conectará a ese nodo

		return config;
	}


}
