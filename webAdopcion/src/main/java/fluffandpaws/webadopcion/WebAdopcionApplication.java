package fluffandpaws.webadopcion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableCaching//habilita la funcionalidad de la caché
@SpringBootApplication
public class WebAdopcionApplication {

	private static final Log LOG = LogFactory.getLog(WebAdopcionApplication.class);//nos permite imprimir mensajes de información en este caso
	public static void main(String[] args) {
		SpringApplication.run(WebAdopcionApplication.class, args);
	}

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


}
