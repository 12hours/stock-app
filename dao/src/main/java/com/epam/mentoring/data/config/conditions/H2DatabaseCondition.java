package com.epam.mentoring.data.config.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class H2DatabaseCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty("app.database.type").equalsIgnoreCase("H2");
	}
}
