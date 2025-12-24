package com.github.ohbaron.dropwizardkotlinexamplev1.healthcheck


import com.codahale.metrics.health.HealthCheck

class DefaultHealthCheck : HealthCheck() {
    override fun check(): HealthCheck.Result {
        return HealthCheck.Result.healthy()
    }
}
