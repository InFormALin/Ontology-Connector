/**
 *
 */
package edu.kit.kastel.informalin.ontology;

import java.util.Optional;

import org.apache.jena.ontology.AnnotationProperty;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 * @author Jan Keim
 *
 */
interface OntologyPropertyAdapter {

    /**
     * Returns an {@link Optional} containing a property with the given name. If no property is found, the
     * {@link Optional} is empty.
     *
     * @param propertyName name of the property to be returned
     * @return {@link Optional} containing a property with the given name. If no such property is found, the
     *         {@link Optional} is empty.
     */
    Optional<OntProperty> getProperty(String propertyName);

    /**
     * Same as {@link #getProperty(String)}, but the property has to be within the given prefix-namespace.
     *
     * @param propertyName name of the property to be returned
     * @param prefix       Prefix (namespace) the property should be in
     * @return {@link Optional} containing a property with the given name and prefix. If no such property is found, the
     *         {@link Optional} is empty.
     */
    Optional<OntProperty> getProperty(String propertyName, String prefix);

    /**
     * Returns an {@link Optional} containing a property with the given uri. If no such property is found, the
     * {@link Optional} is empty.
     *
     * @param propertyIri Iri of the property
     * @return {@link Optional} containing a property with the given iri. If no such property is found, the
     *         {@link Optional} is empty.
     */
    Optional<OntProperty> getPropertyByIri(String propertyIri);

    /**
     * Same as {@link #getProperty(String)} but returns a (typed) {@link DatatypeProperty}.
     *
     * @param dataPropertyName name of the property to be returned
     * @return {@link Optional} containing a {@link DatatypeProperty} with the given name. If no such property is found,
     *         the {@link Optional} is empty.
     */
    Optional<DatatypeProperty> getDataProperty(String dataPropertyName);

    /**
     * Same as {@link #getProperty(String)} but returns a (typed) {@link ObjectProperty}.
     *
     * @param dataPropertyName name of the property to be returned
     * @return {@link Optional} containing a {@link ObjectProperty} with the given name. If no such property is found,
     *         the {@link Optional} is empty.
     */
    Optional<ObjectProperty> getObjectProperty(String objectPropertyName);

    /**
     * Same as {@link #getProperty(String)} but returns a (typed) {@link AnnotationProperty}.
     *
     * @param dataPropertyName name of the property to be returned
     * @return {@link Optional} containing a {@link AnnotationProperty} with the given name. If no such property is
     *         found, the {@link Optional} is empty.
     */
    Optional<AnnotationProperty> getAnnotationProperty(String annotationPropertyName);

    /**
     * Adds a {@link OntProperty} and returns the created {@link OntProperty}. If a {@link OntProperty} with that URI
     * already existed, returns that one
     *
     * @param name Name of the property
     * @return the created or pre-existing OntProperty
     */
    OntProperty addProperty(String name);

    /**
     * Adds a {@link DatatypeProperty} and returns the created {@link DatatypeProperty}. If a {@link DatatypeProperty}
     * with that URI already existed, returns that one
     *
     * @param name Name of the property
     * @return the created or pre-existing DatatypeProperty
     */
    DatatypeProperty addDataProperty(String name);

    /**
     * Adds a {@link ObjectProperty} and returns the created {@link ObjectProperty}. If a {@link ObjectProperty} with
     * that URI already existed, returns that one
     *
     * @param name Name of the property
     * @return the created or pre-existing ObjectProperty
     */
    ObjectProperty addObjectProperty(String name);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, String value);

    /**
     * Sets a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be set
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    void setPropertyToIndividual(Individual individual, OntProperty property, String value);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     * @param language   language of the property value
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, String value, String language);

    /**
     * Sets a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be set
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     * @param language   language of the property value
     */
    void setPropertyToIndividual(Individual individual, OntProperty property, String value, String language);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, RDFNode value);

    /**
     * Sets a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be set
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    void setPropertyToIndividual(Individual individual, OntProperty property, RDFNode value);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, int value);

    /**
     * Sets a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be set
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    void setPropertyToIndividual(Individual individual, OntProperty property, int value);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     * @param type       Type of the value
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, Object value, String type);

    /**
     * Sets a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be set
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     * @param type       Type of the value
     */
    void setPropertyToIndividual(Individual individual, OntProperty property, Object value, String type);

    /**
     * Returns a {@link RDFNode} that represents the value of the given {@link OntProperty} for the given
     * {@link Individual}. The {@link RDFNode} can be used to probe and transform into a corresponding (expected)
     * value-type.
     *
     * @param individual Individual that has the property
     * @param property   Property of which the value should be retrieved
     * @return Value of the given Property for the given Individual
     */
    RDFNode getPropertyValue(Individual individual, OntProperty property);

    /**
     * Returns an {@link Optional} that contains the value of the given {@link OntProperty} for the given
     * {@link Individual}. The returned {@link Optional} is empty, if the there could not be returned a string for the
     * given property.
     *
     * @param individual Individual that has the property
     * @param property   Property of which the value should be retrieved
     * @return {@link Optional} containing the String value. Empty, if no String value could be retrieved
     */
    Optional<String> getPropertyStringValue(Individual individual, OntProperty property);

    /**
     * Returns an {@link Optional} that contains the value of the given {@link OntProperty} for the given
     * {@link Individual}. The returned {@link Optional} is empty, if the there could not be returned a string for the
     * given property.
     *
     * @param individual Individual that has the property
     * @param property   Property of which the value should be retrieved
     * @return {@link Optional} containing the Integer value. Empty, if no Integer value could be retrieved
     */
    Optional<Integer> getPropertyIntValue(Individual individual, OntProperty property);

    /**
     * Removes all statements that have the given resource and property.
     *
     * @param resource Resource
     * @param property property
     */
    void removeAllOfProperty(Resource resource, OntProperty property);

    /**
     * Adds a Property with a value to a given Individual.
     *
     * @param individual Individual the property should be added to
     * @param property   Property that should be added
     * @param value      Value that should be set for that property
     */
    Resource addPropertyToIndividual(Individual individual, OntProperty property, boolean value);

}
