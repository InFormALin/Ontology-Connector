/**
 *
 */
package edu.kit.kastel.informalin.ontology;

import java.util.List;
import java.util.Optional;

import org.apache.jena.ontology.Individual;

/**
 * @author Jan Keim
 *
 */
interface OntologyListAdapter {

    /**
     * Adds an empty list. If a list with the provided label exists, then clears the list and returns it.
     *
     * @param label Label of the list that should be added
     * @return List that is empty having the specified label. Overwrites/deletes preexisting lists with same label.
     */
    OrderedOntologyList addEmptyList(String label);

    /**
     * Similar to {@link #addEmptyList(String)} but also adds the provided members. If a list with the provided label
     * exists, then clears the list and adds the provided members.
     *
     * @param label   Label of the list that should be added
     * @param members Individuals that should be added
     * @return List that contains the provided members and that has the specified label. Overwrites/deletes preexisting
     *         lists with same label.
     */
    OrderedOntologyList addList(String label, List<Individual> members);

    /**
     * Returns an {@link Optional} that contains a {@link OrderedOntologyList} if a list with the specified name/label
     * exists
     *
     * @param name Name/label of the list
     * @return Optional containing the list. Empty Optional, if no list with that name was found.
     */
    Optional<OrderedOntologyList> getList(String name);

    /**
     * Returns an {@link Optional} that contains a {@link OrderedOntologyList} if a list with the specified uri exists.
     *
     * @param uri Uri/Iri of the List
     * @return Optional containing the list with that uri. Empty Optional, if no list with that uri was found.
     */
    Optional<OrderedOntologyList> getListByIri(String uri);

    /**
     * Transforms the given individual into an {@link OrderedOntologyList}. If the given individual has an invalid class
     * or the list cannot be created for any other reason, returns an empty {@link Optional}.
     *
     * @param individual Individual that should be transformed into an {@link OrderedOntologyList}
     * @return Optional containing the list; empty Optional in case anything went wrong.
     */
    Optional<OrderedOntologyList> transformIntoOrderedOntologyList(Individual individual);

}
