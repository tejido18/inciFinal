package repositories;

import domain.Agent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Damian on 06/02/2017.
 * Repository interface from the <a href="https://spring.io/guides/gs/accessing-data-mongodb/">MongoDB Spring API</a>
 */

@Repository
public interface AgentsRepository extends MongoRepository<Agent, ObjectId>{

    Agent findByUsername(String username);
    Agent findByUsernameAndPassword(String username, String password);
}
