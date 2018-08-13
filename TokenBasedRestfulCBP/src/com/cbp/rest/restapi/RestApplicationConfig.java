package com.cbp.rest.restapi;

import org.glassfish.jersey.server.ResourceConfig;

import com.cbp.rest.filter.AuthenticationFilter;

/**
 *  set the filter applications manually and not via web.xml
 */
public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig() {
        packages( "com.cbp.rest.filter" );
		register( AuthenticationFilter.class );
	}
}
