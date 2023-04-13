package fluffandpaws.webadopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CacheController {

    @Autowired
    private CacheManager animalesCacheManager;

    @Autowired
    private CacheManager mensajesCacheManager;

    @Autowired
    private CacheManager protectorasCacheManager;

    @Autowired
    private CacheManager usuariosCacheManager;


    @GetMapping("/cache/animales")//en la cache de animalespero
    public Map<Object, Object> getAnimalesFromCache() {
        Cache animalesCache = animalesCacheManager.getCache("animales");
        ConcurrentMapCache cache = (ConcurrentMapCache) animalesCache.getNativeCache();
        return cache.getNativeCache();


    }
    @GetMapping("/cache/protectoras")//en la cache de animalespero
    public Map<Object, Object> getProtectorasFromCache() {
        Cache protectorasCache = animalesCacheManager.getCache("protectoras");
        ConcurrentMapCache cache = (ConcurrentMapCache) protectorasCache.getNativeCache();
        return cache.getNativeCache();


    }
    @GetMapping("/cache/mensajes")//en la cache de mensajes
    public Map<Object, Object> getMensajesFromCache() {
        Cache mensajesCache = mensajesCacheManager.getCache("mensajes");
        ConcurrentMapCache cache = (ConcurrentMapCache) mensajesCache.getNativeCache();
        return cache.getNativeCache();


    }
    @GetMapping("/cache/usuarios")//en la cache de usuarios
    public Map<Object, Object> getUsuariosFromCache() {
        Cache usuariosCache = usuariosCacheManager.getCache("usuarios");
        ConcurrentMapCache cache = (ConcurrentMapCache) usuariosCache.getNativeCache();
        return cache.getNativeCache();


    }

}
