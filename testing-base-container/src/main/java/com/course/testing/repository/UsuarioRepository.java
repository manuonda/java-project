package com.course.testing.repository;

import com.course.testing.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Permite realizar la busqueda de empleado por
     * el parametro de email
     *
     * @param email
     * @return
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * JPQL wiht Index
     *
     * @param firstName
     * @param lastName
     * @return
     */
    @Query(value = "select e from Usuario e where e.firstName = ?1 and e.lastName = ?2")
    Optional<Usuario> findByJPQLIndex(String firstName, String lastName);

    /**
     * JQPL utilizando anotacion @param
     *
     * @param lastName
     * @param firstName
     * @return
     */
    @Query(value = "select e from Usuario e where e.firstName =:firstName and e.lastName =:lastName")
    Optional<Usuario> findByJPQLParam(@Param("firstName") String firstName, @Param("lastName") String lastName);

    /**
     * Sql Native Query Indexacion
     */
    @Query(value = "select * from tbl_usuario e where e.first_name =?1 and e.last_name = ?2", nativeQuery = true)
    Optional<Usuario> findBySqlNativeIndex(String firstName, String lastName);

    /**
     * Sql Native Query con Parametros
     */
    @Query(value = "SELECT * FROM tbl_usuario e where e.first_name =:firstName and e.last_name =:lastName",
            nativeQuery = true)
    Optional<Usuario> findBySqlNativeParametros(@Param("firstName") String firstName,
                                                @Param("lastName") String lastName);


}
