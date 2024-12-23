package io.positivinh.playground.spring.autoconfigure

import io.positivinh.playground.spring.autoconfigure.domain.PlaygroundBean
import io.positivinh.playground.spring.autoconfigure.properties.PlaygroundAutoConfigurationProperties
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.assertj.AssertableApplicationContext
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class PlaygroundSpringAutoConfigureTest {

    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(PlaygroundSpringAutoConfigure::class.java))
        .withInitializer(ConfigDataApplicationContextInitializer())


    @Test
    fun loadAutoConfiguration() {

        contextRunner
            .run { context: AssertableApplicationContext ->

                assertThat(context).hasSingleBean(PlaygroundBean::class.java)
                val bean: PlaygroundBean = context.getBean("playgroundBean") as PlaygroundBean
                assertThat(context).getBean("playgroundBean", PlaygroundBean::class.java)
                    .isSameAs(context.getBean(PlaygroundBean::class.java))
                assertThat(bean.name).isEqualTo("autoconfigured")
                assertThat(bean.property).isEqualTo("from properties")
            }
    }

    @Test
    fun loadAutoConfigurationWithCustomProperties() {

        contextRunner
            .withPropertyValues("playground.bean.property=from test properties")
            .run { context: AssertableApplicationContext ->

                assertThat(context).hasSingleBean(PlaygroundBean::class.java)
                val bean: PlaygroundBean = context.getBean("playgroundBean") as PlaygroundBean
                assertThat(context).getBean("playgroundBean", PlaygroundBean::class.java)
                    .isSameAs(context.getBean(PlaygroundBean::class.java))
                assertThat(bean.name).isEqualTo("autoconfigured")
                assertThat(bean.property).isEqualTo("from test properties")
            }
    }

    @Test
    fun loadAutoConfigurationOverriddenByUserConfiguration() {

        contextRunner
            .withUserConfiguration(UserConfiguration::class.java)
            .run { context: AssertableApplicationContext ->

                assertThat(context).hasSingleBean(PlaygroundBean::class.java)
                val bean: PlaygroundBean = context.getBean("playgroundBean") as PlaygroundBean
                assertThat(context).getBean("playgroundBean", PlaygroundBean::class.java)
                    .isSameAs(context.getBean(PlaygroundBean::class.java))
                assertThat(bean.name).isEqualTo("mine")
                assertThat(bean.property).isEqualTo("from properties")
            }
    }

    @Configuration(proxyBeanMethods = false)
    internal class UserConfiguration {

        @Bean
        fun playgroundBean(playgroundBeanConfigurationProperties: PlaygroundAutoConfigurationProperties): PlaygroundBean {
            return PlaygroundBean("mine", playgroundBeanConfigurationProperties.property)
        }

    }
}
