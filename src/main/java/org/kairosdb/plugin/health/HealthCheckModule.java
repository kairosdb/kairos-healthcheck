/*
 * Copyright 2013 Proofpoint Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kairosdb.plugin.health;


import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.kairosdb.core.health.HealthCheckResource;
import org.kairosdb.core.health.HealthCheckService;
import org.kairosdb.core.health.HealthCheckServiceImpl;
import org.kairosdb.core.health.ThreadDeadlockHealthStatus;

public class HealthCheckModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(HealthCheckService.class).to(HealthCheckServiceImpl.class).in(javax.inject.Singleton.class);
		bind(ThreadDeadlockHealthStatus.class).in(javax.inject.Singleton.class);
		bind(DatastoreQueryHealthCheck.class).in(javax.inject.Singleton.class);

		// Bind REST resource
		bind(HealthCheckResource.class).in(Scopes.SINGLETON);
	}
}
