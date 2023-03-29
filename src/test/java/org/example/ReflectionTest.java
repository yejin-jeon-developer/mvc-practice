package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        /*
         Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
         */

        /* 리팩토링 */
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields : [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructor : [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared method : [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        Class<User> clazz = User.class;

        User user = new User("yjjeon", "전예진");
        Class<? extends User> clazz2 = user.getClass();

        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz : [{}]", clazz);
        logger.debug("clazz2 : [{}]", clazz2);
        logger.debug("clazz3 : [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz == clazz3).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example"); // org.example 에 있는 모든 클래스에 리플랙션 사용

        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
