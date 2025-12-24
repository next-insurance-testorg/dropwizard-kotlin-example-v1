package com.github.ohbaron.dropwizardkotlinexamplev1

import com.google.common.io.Resources
import com.github.ohbaron.dropwizardkotlinexamplev1.filter.DiagnosticContextFilter
import com.github.ohbaron.dropwizardkotlinexamplev1.healthcheck.DefaultHealthCheck
import com.github.ohbaron.dropwizardkotlinexamplev1.resource.BuildInfoResource
import com.github.ohbaron.dropwizardkotlinexamplev1.resource.RootResource
import io.dropwizard.core.Application
import io.dropwizard.core.setup.Environment


class App : Application<AppConfig>() {

    override fun run(config: AppConfig, env: Environment) {
        env.jersey().register(RootResource(config.appName))
        env.jersey().register(DiagnosticContextFilter())
        env.healthChecks().register("default", DefaultHealthCheck())

        val resource = BuildInfoResource(
            Resources.toString(Resources.getResource("build-info.json"), Charsets.UTF_8)
        )
        env.jersey().register(resource)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(*args)
        }
    }

}

