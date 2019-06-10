package com.lemon;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lemon
 */
public class HelloWorldApplicationTest {
    @Rule
    public final OutputCapture output = new OutputCapture();

    private String profiles;

    @Before
    public void before() {
        this.profiles = System.getProperty("spring.profiles.active");
        System.out.println("==> "+this.profiles);
    }

    @After
    public void after() {
        if (this.profiles != null) {
            System.setProperty("spring.profiles.active", this.profiles);
        } else {
            System.clearProperty("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultProfile() throws Exception {
        System.setProperty("spring.profiles.active", "dev");
//        HelloWorldApplication.main(new String[]{ "--spring.profiles.active=dev" });
        assertThat(this.output.toString()).isEqualTo("dev");
    }
}
