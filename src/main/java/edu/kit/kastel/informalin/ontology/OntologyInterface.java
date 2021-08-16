package edu.kit.kastel.informalin.ontology;

import java.util.List;
import java.util.Optional;

import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Interface for adapters that connects your code to an ontology. Provides various methods to decorate the usage of
 * Apache Jena. Although many methods in this class return the objects that are of some type from Apache Jena, you
 * should not directly operate on these classes. The {@link OntologyConnector} also acts as a access controller to make
 * sure that concurrent access does not create invalid states.
 *
 *
 * @author Jan Keim
 *
 */
public interface OntologyInterface extends OntologyClassAdapter, OntologyIndividualAdapter, OntologyListAdapter, OntologyPropertyAdapter {

    /**
     * Validates the ontology. A logger will put out warnings iff there are conflicts.
     *
     * @return <code>true</code> if the ontology is valid and has no conflicts, <code>false</code> if there are
     *         conflicts
     */
    boolean validateOntology();

    /**
     * Adds/Sets a namespace prefix
     *
     * @param prefix the new prefix that should be able to use
     * @param uri    the URI that the prefix should be resolved to
     */
    void setNsPrefix(String prefix, String uri);

    /**
     * Save the ontology to a given file (path). This method uses the RDF/XML language.
     *
     * @param file String containing the path of the file the ontology should be saved to
     * @return true if saving was successful, otherwise false is returned
     */
    boolean save(String file);

    /**
     * Save the ontology to a given file (path). This method uses the N3 language to save.
     *
     * @param file     String containing the path of the file the ontology should be saved to
     * @param language The language the file should be written in
     * @return true if saving was successful, otherwise false is returned
     */
    boolean save(String file, Lang language);

    /**
     * Add an Ontology based on its IRI
     *
     * @param importIRI the IRI of the ontology that should be imported
     */
    void addOntologyImport(String importIRI);

    /**
     * Check if the given iri is imported into the ontology model.
     *
     * @param importIri Iri that should be checked
     * @return True if imported, else False
     */
    boolean hasImport(String importIri);

    /**
     * Creates the uri out of a given prefix and suffix by concatenating them and expanding the prefix.
     *
     * @param prefix prefix that should be used
     * @param suffix suffix that should be used
     * @return uri after expansion.
     */
    String createUri(String prefix, String suffix);

    /**
     * List statements that have the given {@link OntProperty} as property and the given {@link RDFNode} as object.
     * Returns the first non-null subject of the found statements.
     *
     * @param property Property used to look for
     * @param object   object that should be contained
     * @return Optional containing the first non-null subject. Empty Optional, if none is found
     */
    Optional<Resource> getFirstSubjectOf(OntProperty property, RDFNode object);

    /**
     * List statements that have the given {@link OntProperty} as property and the given {@link RDFNode} as object.
     * Returns the extracted subjects of the statements.
     *
     * @param property Property used to look for
     * @param object   object that should be contained
     * @return List of extracted subjects
     */
    List<Resource> getSubjectsOf(OntProperty property, OntResource object);

    /**
     * List statements that have the given {@link OntProperty} as property and the given {@link OntResource} as subject.
     * Returns the first non-null subject of the found statements.
     *
     * @param subject  Subject that should be contained
     * @param property Property used to look for
     * @return Optional containing the first non-null object. Empty Optional, if none is found
     */
    Optional<RDFNode> getFirstObjectOf(OntResource subject, OntProperty property);

    /**
     * List statements that have the given {@link OntProperty} as property and the given {@link OntResource} as subject.
     * Returns the extracted objects of the statements.
     *
     * @param subject  Subject that should be contained
     * @param property Property used to look for
     * @return List of extracted objects
     */
    ImmutableList<RDFNode> getObjectsOf(OntResource subject, OntProperty property);

    /**
     * Transforms a given Node (that is a subtype of RDFNode) into the given target type. If it cannot be transformed,
     * returns an empty Optional.
     *
     * @param <S>        source type, must extend Resource
     * @param <T>        target type, must extend Resource
     * @param from       resource that should be transformed
     * @param targetType class of the target type
     * @return Optional containing the transformed resource. If transformation was unsuccessful, the Optional is empty.
     */
    <S extends RDFNode, T extends Resource> Optional<T> transformType(S from, Class<T> targetType);

    /**
     * Transforms a given Node (that is a subtype of RDFNode) into the given target type. If it cannot be transformed,
     * returns <code>null</code>.
     *
     * @param <S>        source type, must extend Resource
     * @param <T>        target type, must extend Resource
     * @param from       resource that should be transformed
     * @param targetType class of the target type
     * @return The transformed resource. If transformation was unsuccessful, returns null.
     */
    <S extends RDFNode, T extends Resource> T transformTypeNullable(S from, Class<T> targetType);

    /**
     * See {@link OntResource#getLocalName()}
     *
     * @param resource Resource
     * @return The localname of this property within its namespace.
     */
    String getLocalName(OntResource resource);

    /**
     * Returns a label for the given resource. For more details, see {@link OntResource#getLabel(String)}. The provided
     * language is set to <code>null</code>
     *
     * @param resource the resource
     * @return a label for the given resource or null if none is found
     */
    String getLabel(OntResource resource);

    /**
     * See {@link OntResource#getLabel(String)}
     *
     * @param resource the resource
     * @param lang     the language attribute
     * @return a label for the given resource or null if none is found
     */
    String getLabel(OntResource resource, String lang);

    /**
     * Generates a random URI using the default prefix
     *
     * @return random URI
     */
    String generateRandomURI();

    /**
     * Generates a random URI using the given prefix.
     *
     * @param prefix Prefix that should be used for namespace
     * @return random URI with the given prefix
     */
    String generateRandomURI(String prefix);

}
