/**
 *
 */
package edu.kit.kastel.informalin.ontology;

import java.util.Optional;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Resource;

/**
 * @author Jan Keim
 *
 */
interface OntologyClassAdapter {

    /**
     * Looks for an {@link OntClass} that contains the given name as either label or uses the name as (local) Iri.
     *
     * @param className Label or Iri of the wanted class
     * @return Optional containing the given class. Returns an empty optional, if the class could not be found
     */
    Optional<OntClass> getClass(String className);

    /**
     * Looks for a class with the given name and the given prefix. The name can be either a local iri or a given label.
     * If it is a label, this method will only return classes that can be found within the namespace of the given prefix
     *
     * @param className label or iri of the class that should be returned
     * @param prefix    Prefix of the namespace the class should be contained in.
     * @return {@link Optional} containing the wanted class if it was found. Else, returns an empty {@link Optional}
     */
    Optional<OntClass> getClass(String className, String prefix);

    /**
     * Returns an {@link Optional} containing the {@link OntClass} that corresponds to the given uri. The uri might be
     * in prefix-notation (e.g., "owl:Thing"). If no class is found, the returned {@link Optional} will be empty
     *
     * @param iri the iri of the class (can be prefix notation or simple uri notation)
     * @return {@link Optional} containing the {@link OntClass} that corresponds to the given iri. Empty Optional if no
     *         class exists.
     */
    Optional<OntClass> getClassByIri(String iri);

    /**
     * Adds a class with the given name to the ontology. If the class exists already, returns the existing class.
     *
     * @param className Name of the class that should be created
     * @return created class
     */
    OntClass addClass(String className);

    /**
     * Adds a class with the given Iri and returns this class. If a class with that Iri already exists, returns the
     * existing class.
     *
     * @param iri Iri of the class that should be added
     * @return {@link OntClass} with the given Iri
     */
    OntClass addClassByIri(String iri);

    /**
     * Add superclass relation between the given arguments.
     *
     * @param subClass   class that should be subclass of the other given class/resource
     * @param superClass class/resource that should be superclass of the other given class
     */
    void addSuperClass(OntClass subClass, Resource superClass);

    /**
     * Sets exclusive superclassing for the given sub-class to the given super-class. Overwrites previous super-classes.
     *
     * @param subClass   class that should be subclass of the other given class/resource
     * @param superClass class/resource that should be superclass of the other given class
     */
    void addSuperClassExclusive(OntClass subClass, Resource superClass);

    /**
     * Add superclass relation between the given arguments.
     *
     * @param className      name of the class that should be subclass of the other given class/resource
     * @param superClassName name of the class that should be superclass of the other given class
     */
    OntClass addSuperClass(String className, String superClassName);

    /**
     * Add superclass relation between the given arguments.
     *
     * @param className  name of the class that should be subclass of the other given class/resource
     * @param superClass class that should be superclass of the other given class
     */
    OntClass addSubClass(String className, OntClass superClass);

    /**
     * Add superclass relation between the given arguments.
     *
     * @param subClass   class that should be subclass of the other given class/resource
     * @param superClass class that should be superclass of the other given class
     */
    void addSubClass(OntClass subClass, OntClass superClass);

    /**
     * Checks whether there is a sub/super-class relation between the given classes (order is important!)
     *
     * @param clazz      sub-class
     * @param superClass super-class
     * @return True if sub-class and super-class are related correspondingly
     */
    boolean classIsSubClassOf(OntClass clazz, OntClass superClass);

    /**
     * Removes sub/super-classing relation between given classes.
     *
     * @param clazz      previous sub-class
     * @param superClass previous super-class
     */
    void removeSubClassing(OntClass clazz, OntClass superClass);

    /**
     * Returns whether there is a class in the ontology with the given name. See also: {@link #getClass(String)}.
     *
     * @param className class to look for
     * @return True if there is a class with the given name. Else, False
     */
    boolean containsClass(String className);

    /**
     * Returns whether there is a class in the ontology with the given name and the given prefix. See also:
     * {@link #getClass(String, String)}.
     *
     * @param className class to look for
     * @param prefix    prefix of the class
     * @return True if there is a class with the given name and prefix. Else, False
     */
    boolean containsClass(String className, String prefix);

    /**
     * See {@link Individual#hasOntClass(String)}.
     *
     * @param individual Individual to check
     * @param uri        Uri to check
     * @return True if this individual has the given class as one of its types.
     */
    boolean hasOntClass(Individual individual, String uri);

    boolean hasOntClass(Individual individual, String prefix, String localname);

}
