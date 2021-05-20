package config

import com.google.inject.AbstractModule

import javax.inject.Singleton

@Singleton
class GuiceModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[AppConfig]) to classOf[ApplicationConfig]
  }
}
