package com.epam.mentoring.service.jpa.config;

import org.osgi.framework.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

public class EntityManagerFactoryWrapper implements BundleActivator {

    private static Logger log = LoggerFactory.getLogger(EntityManagerFactoryWrapper.class);

    private static EntityManagerFactoryWrapper ourInstance;

    private EntityManagerFactory emf;

    public EntityManagerFactoryWrapper() {
        ourInstance = this;
    }

    public static EntityManagerFactoryWrapper getInstance() {
        return ourInstance;
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            log.debug("Creating EntityManagerFactory...");
            Bundle thisBundle = FrameworkUtil.getBundle(EntityManagerFactoryWrapper.class);
            BundleContext context = thisBundle.getBundleContext();

            ServiceReference serviceReference = context.getServiceReference(PersistenceProvider.class.getName());
            PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService(serviceReference);
            emf = persistenceProvider.createEntityManagerFactory("PU", null);
            log.debug("EntityManagerFactory created: " + emf.toString());
        }
        return emf.createEntityManager();
    }

    @Override
    public void start(BundleContext context) throws Exception {

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (emf != null) {
            log.debug("Closing EntityManagerFactory");
            try {
                emf.close();
                if (!emf.isOpen()) log.debug("EntityManagerFactory successfully closed");
            } catch (Exception e) {
                log.error("Can not close EntityManagerFactory");
                e.printStackTrace();
            }
        }
    }

}
