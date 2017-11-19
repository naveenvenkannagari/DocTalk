package com.sa.task.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Created by temp on 10/11/17.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
