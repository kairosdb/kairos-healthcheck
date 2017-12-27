package org.kairosdb.plugin.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import org.kairosdb.core.datastore.DataPointGroup;
import org.kairosdb.core.datastore.DatastoreQuery;
import org.kairosdb.core.datastore.KairosDatastore;
import org.kairosdb.core.datastore.QueryMetric;
import org.kairosdb.core.exception.DatastoreException;
import org.kairosdb.core.health.HealthStatus;

import javax.inject.Named;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class DatastoreQueryHealthCheck extends HealthCheck implements HealthStatus
{
	static final String NAME = "Datastore-Query";
	private final KairosDatastore m_datastore;
	private final String m_healthMetric;

	@Inject
	public DatastoreQueryHealthCheck(KairosDatastore datastore,
			@Named("kairosdb.health.health_metric") String healthMetric)
	{
		m_datastore = checkNotNull(datastore);
		m_healthMetric = checkNotNull(healthMetric);
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	protected HealthCheck.Result check() throws Exception
	{
		try
		{
			DatastoreQuery query = m_datastore.createQuery(
					new QueryMetric(System.currentTimeMillis() - (10 * 60 * 1000),
							0, m_healthMetric));

			query.execute();
			return HealthCheck.Result.healthy();
		}
		catch (DatastoreException e)
		{
			return HealthCheck.Result.unhealthy(e);
		}
	}
}
