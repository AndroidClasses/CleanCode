package com.cleaner.config;


import com.cleaner.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ConfigurationRepositoryModule.class, ApplicationModule.class})
public interface ConfigurationRepositoryComponent {

    ConfigurationRepository getConfigRepository();
}
