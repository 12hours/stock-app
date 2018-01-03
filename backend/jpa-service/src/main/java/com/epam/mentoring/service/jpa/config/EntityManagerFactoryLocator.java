package com.epam.mentoring.service.jpa.config;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

public class EntityManagerFactoryLocator {

    private static Logger log = LoggerFactory.getLogger(EntityManagerFactoryLocator.class.getName());

    public static EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory emf;
        log.debug("Creating EntityManagerFactory...");
        Bundle thisBundle = FrameworkUtil.getBundle(EntityManagerFactoryLocator.class);
        BundleContext context = thisBundle.getBundleContext();

        ServiceReference serviceReference = context.getServiceReference(PersistenceProvider.class.getName());
        PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService(serviceReference);
        emf = persistenceProvider.createEntityManagerFactory("PU", null);
        log.debug("EntityManagerFactory created: " + emf.toString());
        return emf;
    }

}
