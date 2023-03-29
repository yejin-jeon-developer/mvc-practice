package org.example;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

/**
 * @Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    @Test
    void controllerScan() {
        Reflections reflections = new Reflections("org.example"); // org.example 에 있는 모든 클래스에 리플랙션 사용

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith())
    }
}
