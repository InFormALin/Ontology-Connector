package edu.kit.kastel.informalin.ontology;

import java.util.List;
import java.util.Optional;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.RDFNode;
import org.eclipse.collections.api.list.ImmutableList;

interface OntologyIndividualAdapter {

    /**
     * Returns an {@link Optional} that contains a named individual with the given name. If no individual with that name
     * exists, returns empty {@link Optional}.
     *
     * @param name name of the individual
     * @return Optional with the individual if it exists. Otherwise, empty Optional.
     */
    Optional<Individual> getIndividual(String name);

    /**
     * Returns an {@link Optional} that contains a named individual with the given uri. If no individual with that uri
     * exists, returns empty {@link Optional}.
     *
     * @param iri iri of the individual
     * @return Optional with the individual if it exists. Otherwise, empty Optional.
     */
    Optional<Individual> getIndividualByIri(String iri);

    /**
     * Returns the Individuals that have a class that corresponds to the given class name
     *
     * @param className Name of the class
     * @return List of Individuals for the given class (name)
     */
    List<Individual> getIndividualsOfClass(String className);

    /**
     * Returns List of individuals of the given class.
     *
     * @param clazz Class of the individuals that should be returned
     * @return List of individuals with the given class.
     */
    List<Individual> getIndividualsOfClass(OntClass clazz);

    /**
     * Similar to {@link #getIndividualsOfClass(String)}, but also checks for inferred instances.
     *
     * @param className name of the class to retrieve individuals from
     * @return List of Individuals for the given class (name), including inferred ones
     */
    ImmutableList<Individual> getInferredIndividualsOfClass(String className);

    /**
     * Adds an individual with the given name to the default (prefix) namespace.
     *
     * @param name Name of the individual
     * @return the Individual with the given name
     */
    Individual addIndividual(String name);

    /**
     * Removes an individual with the given name in the default (prefix) namespace from the ontology.
     *
     * @param name Name of the individual
     */
    void removeIndividual(String name);

    /**
     * Removes an individual with the given uri from the ontology.
     *
     * @param name Name of the individual
     */
    void removeIndividualByUri(String uri);

    /**
     * Removes a given individual from the ontology.
     *
     * @param individual the individual
     */
    void removeIndividual(Individual individual);

    /**
     * Adds an Individual to the given class. If the Individual does not exist, creates the individual as well.
     *
     * @param name  name of the individual that should be added
     * @param clazz Class the individual should be added to
     * @return the individual corresponding to the name. If it did not exist before, it is the newly created individual
     */
    Individual addIndividualToClass(String name, OntClass clazz);

    /**
     * Sets the class of an Individual. If the Individual does not exist, creates the individual as well.
     *
     * @param name  name of the individual that should be added
     * @param clazz Class the individual should be exclusively added to
     * @return the individual corresponding to the name. If it did not exist before, it is the newly created individual
     */
    Individual setIndividualClass(String name, OntClass clazz);

    /**
     * Transforms a given Node (that is a subtype of RDFNode) into an {@link Individual}. If it cannot be transformed,
     * returns <code>null</code>.
     *
     * @param node Node that should be transformed
     * @return The transformed Individual. If transformation was unsuccessful, returns null.
     */
    Optional<Individual> transformIntoIndividual(RDFNode node);
}
