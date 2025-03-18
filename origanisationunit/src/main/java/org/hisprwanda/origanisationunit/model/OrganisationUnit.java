/**
 * 
 */
package org.hisprwanda.origanisationunit.model;


import lombok.Data;

import java.util.List;

@Data
public class OrganisationUnit {
    private String id;
    private String name;
    private String level;
    private List<OrganisationUnit> children;
}
