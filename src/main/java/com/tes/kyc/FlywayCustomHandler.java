package com.tes.kyc;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

public class FlywayCustomHandler implements FlywayMigrationStrategy
{

	@Override
	public void migrate(Flyway flyway)
	{
		flyway.repair();
		flyway.migrate();
	}

}
