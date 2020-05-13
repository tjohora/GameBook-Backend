/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author TJ
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Rest.CommentsResource.class);
        resources.add(Rest.FlaggedCommentResource.class);
        resources.add(Rest.FlaggedPostResource.class);
        resources.add(Rest.NewCrossOriginResourceSharingFilter.class);
        resources.add(Rest.PostResource.class);
        resources.add(Rest.RatingResource.class);
        resources.add(Rest.UserResource.class);
    }
    
}
