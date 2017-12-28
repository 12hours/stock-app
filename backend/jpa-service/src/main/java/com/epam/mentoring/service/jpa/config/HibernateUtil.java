package com.epam.mentoring.service.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Brett Meyer
 */

public class HibernateUtil {

    private EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    private EntityManagerFactory getEntityManagerFactory() {
        if ( emf == null ) {
            Bundle thisBundle = FrameworkUtil.getBundle( HibernateUtil.class );
            // Could get this by wiring up OsgiTestBundleActivator as well.
            BundleContext context = thisBundle.getBundleContext();

            ServiceReference serviceReference = context.getServiceReference( PersistenceProvider.class.getName() );
            PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService( serviceReference );

            emf = persistenceProvider.createEntityManagerFactory( "PU", null );
        }
        return emf;
    }
}