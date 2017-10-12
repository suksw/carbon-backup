package org.wso2.carbon.apimgt.core.api;

import org.wso2.carbon.apimgt.core.exception.ServiceDiscoveryException;
import org.wso2.carbon.apimgt.core.models.Endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This interface allows discovering services in a cluster using a set of cms specific parameters
 * while filtering by namespace and/or criteria as needed.
 */
public abstract class ServiceDiscoverer {

    protected HashMap<String, String> cmsSpecificParameters;
    private String namespaceFilter;
    private HashMap<String, String> criteriaFilter;

    protected int serviceEndpointIndex;
    protected List<Endpoint> servicesList;


    public void init(HashMap<String, String> cmsSpecificParameters) throws ServiceDiscoveryException {
        this.cmsSpecificParameters = cmsSpecificParameters;
        this.namespaceFilter = this.cmsSpecificParameters.get("namespace");
        String criteriaString = this.cmsSpecificParameters.get("criteria");
        if (criteriaString != null) {
            String[] criteriaArray = criteriaString.split(",");
            HashMap<String, String> criteriaMap = new HashMap<>();
            for (String pair : criteriaArray) {
                String[] entry = pair.split("=");
                criteriaMap.put(entry[0].trim(), entry[1].trim());
            }
            this.criteriaFilter = criteriaMap;
        }
        servicesList = new ArrayList<>();
        serviceEndpointIndex = 0;
    }

    /**
     * To get list of endpoints without any filtering.
     *
     * @return List of Endpoints
     * @throws ServiceDiscoveryException If an error occurs while listing
     */
    public abstract List<Endpoint> listServices() throws ServiceDiscoveryException;

    /**
     * To get list of endpoints, with a specific namespace.
     *
     * @param namespace     Namespace of the expected endpoints
     * @return List of Endpoints with the specified namespace
     * @throws ServiceDiscoveryException If an error occurs while listing
     */
    public abstract List<Endpoint> listServices(String namespace) throws ServiceDiscoveryException;

    /**
     * To get list of endpoints, with a specific criteria.
     *
     * @param criteria    A criteria the endpoints should be filtered by
     * @return List of Endpoints with the specified criteria
     * @throws ServiceDiscoveryException If an error occurs while listing
     */
    public abstract List<Endpoint> listServices(HashMap<String, String> criteria) throws ServiceDiscoveryException;

    /**
     * To get list of endpoints, with a specific namespace and a criteria.
     *
     * @param namespace   Namespace of the expected endpoints
     * @param criteria    A criteria the endpoints should be filtered by
     * @return List of Endpoints with the specified namespace and criteria
     * @throws ServiceDiscoveryException If an error occurs while listing
     */
    public abstract List<Endpoint> listServices(String namespace, HashMap<String, String> criteria)
            throws ServiceDiscoveryException;

    protected Endpoint createEndpoint(String id, String name, String endpointConfig, Long maxTps,
                                    String type, String endpointSecurity, String applicableLevel) {
        Endpoint.Builder endpointBuilder = new Endpoint.Builder();
        endpointBuilder.id(id);
        endpointBuilder.name(name);
        endpointBuilder.endpointConfig(endpointConfig);
        endpointBuilder.maxTps(maxTps);
        endpointBuilder.type(type);
        endpointBuilder.security(endpointSecurity);
        endpointBuilder.applicableLevel(applicableLevel);

        serviceEndpointIndex++;
        return endpointBuilder.build();
    }


    public String getNamespaceFilter() {
        return namespaceFilter;
    }

    public HashMap<String, String> getCriteriaFilter() {
        return criteriaFilter;
    }

}
