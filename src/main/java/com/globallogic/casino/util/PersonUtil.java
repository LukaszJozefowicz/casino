package com.globallogic.casino.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.entity.h2.Person;
import com.globallogic.casino.repository.AddressRepository;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@UtilityClass
public class PersonUtil {

    private static ObjectMapper objectMapper;
    private static final String PATCH_MESSAGE = "%s [id: %d] %s was changed to %s";
    private static final String REMOVE_MESSAGE = "%s [id: %d] was removed successfully";

    public static void setObjectMapper(ObjectMapper objectMapperToSet) {
        objectMapper = objectMapperToSet;
    }

    public static <T extends Person, R extends JpaRepository<T, Long>> String removePerson(Long personId, R repository) {
        T personToRemove = repository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, personId));
        repository.delete(personToRemove);
        return String.format(REMOVE_MESSAGE, personToRemove.getClass().getSimpleName(), personId);
    }

    public static <T extends Person> void setAddressIfAlreadyExists(T person, AddressRepository addressRepository) {
        addressRepository.findByCityAndStreetAndHouseNumberAndFlatNumberAndPostCode(
                        person.getAddress().getCity(),
                        person.getAddress().getStreet(),
                        person.getAddress().getHouseNumber(),
                        person.getAddress().getFlatNumber(),
                        person.getAddress().getPostCode())
                .ifPresent(person::setAddress);
    }

    @SneakyThrows
    public static <T extends Person, R extends JpaRepository<T, Long>> String patchPerson(
            Long personId,
            Class<? extends Person> personClass,
            R repository,
            String nodeValueToReplace,
            JsonNode valueNodeToPatch) {

        T personToPatch = repository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(personClass, personId));

        JsonPatch jsonPatch = new JsonPatch(List.of(
                new ReplaceOperation(
                        new JsonPointer("/" + nodeValueToReplace),
                        valueNodeToPatch)
        ));
        JsonNode patchedPersonNode = jsonPatch.apply(objectMapper.convertValue(personToPatch, JsonNode.class));
        T patchedPerson = (T) objectMapper.treeToValue(patchedPersonNode, personClass);
        repository.save(patchedPerson);

        return String.format(PATCH_MESSAGE,
                personClass.getSimpleName(),
                personId,
                nodeValueToReplace,
                valueNodeToPatch.asText());
    }
}
